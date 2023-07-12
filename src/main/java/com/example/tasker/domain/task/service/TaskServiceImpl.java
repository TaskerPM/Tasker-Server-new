package com.example.tasker.domain.task.service;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.exception.NotFoundCategoryException;
import com.example.tasker.domain.category.repository.CategoryRepository;
import com.example.tasker.domain.task.dto.*;
import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.exception.*;
import com.example.tasker.domain.task.repository.NoteRepository;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.repository.UserRepository;
import com.example.tasker.global.dto.BaseException;
import com.example.tasker.global.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.tasker.global.dto.BaseResponseStatus.INVALID_USER_JWT;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date) {
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new); // 유저 찾기
        Task save = taskRepository.save(postTaskReq.toEntity(user, date));
        return PostTaskRes.of(save);
    }

    @Override
    @Transactional
    public void deleteTask(Long userId, Long taskId) throws BaseException {

        Optional<User> user = userRepository.findByUserId(userId);
        Task taskFindById = taskRepository.findById(taskId).get();

        //유저 일치 확인
        if (!taskFindById.getUser().equals(user.get())) {
            throw new BaseException(INVALID_USER_JWT);
        }

        taskRepository.deleteByUserUserIdAndTaskId(userId, taskId);
    }

    @Override
    @Transactional
    public PatchTaskDetailRes editTaskDetail(Long userId, PatchTaskDetailReq patchTaskDetailReq, Long taskId) throws BaseException {
        Optional<User> user = userRepository.findByUserId(userId);
        Task task = taskRepository.findById(taskId).get();

        //유저 일치 확인
        if (!task.getUser().equals(user.get())) {
            throw new BaseException(INVALID_USER_JWT);
        }

        if(patchTaskDetailReq.getCategoryId() != null && patchTaskDetailReq.getCategoryId() != 0){
            Category category = categoryRepository.findById(patchTaskDetailReq.getCategoryId())
                    .orElseThrow(NotFoundCategoryException::new);
            task.updateCategory(category);
        }

        if(patchTaskDetailReq.getTitle() != null && !patchTaskDetailReq.getTitle().equals(task.getTitle())){
            task.updateTitle(patchTaskDetailReq.getTitle());
        }

        if(patchTaskDetailReq.getTimeStart() != null && !patchTaskDetailReq.getTimeStart().equals(task.getTimeStart())){
            // case0. 종료 시간이 없을 때
            if(patchTaskDetailReq.getTimeEnd() == null && patchTaskDetailReq.getTimeEnd().equals(task.getTimeEnd())){
                throw new TimeError4Exception();
            }

            int startTime = Integer.parseInt(patchTaskDetailReq.getTimeStart());
            int endTime = Integer.parseInt(patchTaskDetailReq.getTimeEnd());

            // case1. 시작 시간과 종료시간이 같음
            if(startTime == endTime){
                throw new TimeError2Exception();
            }
            // case2. 시작 시간이 종료 시간보다 이를 때
            if(startTime > endTime){
                throw new TimeError3Exception();
            }

            // case3. 다른 task와 시간이 겹침
            Task findStartTime = taskRepository.findByDateAndTimeStart(task.getDate(), patchTaskDetailReq.getTimeStart());
            Task findEndTime = taskRepository.findByDateAndTimeEnd(task.getDate(),patchTaskDetailReq.getTimeStart());
            if(findStartTime != null){
                throw new TimeError1Exception();
            }
            if(findEndTime != null){
                throw new TimeError1Exception();
            }

            task.updateTime(patchTaskDetailReq.getTimeStart(), patchTaskDetailReq.getTimeEnd());
        }

        if (!patchTaskDetailReq.getNotesContent().isEmpty()) {
            HashSet<String> noteContentSet = new HashSet<>();
            task.getNoteList().forEach(note -> {
                noteContentSet.add(note.getContent());
            });

            patchTaskDetailReq.getNotesContent().forEach(note->{
                if(!noteContentSet.contains(note)){
                    noteRepository.save(Note.builder()
                            .content(note)
                            .task(task)
                            .build());
                    noteContentSet.remove(note);
                }
            });

            // 포함되어 있지 않은 note는 DB에서 삭제
            if(!noteContentSet.isEmpty()){
                noteContentSet.forEach(note->{
                    Note getNote = noteRepository.findByContentAndTask(note, task).orElseThrow(NotFoundNoteException::new);;
                    noteRepository.delete(getNote);
                });
            }

        }

        Task save = taskRepository.save(task);
        return PatchTaskDetailRes.of(save);
    }

    @Override
    public List<GetTasksRes> getTasksByDate(Long userId, String date) {
        // 유저 확인
        userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        List<Task> tasks = taskRepository.findByUserUserIdAndDate(userId, date);
        List<GetTasksRes> getTasksResList = new ArrayList<>();
        tasks.forEach(task -> {
            getTasksResList.add(GetTasksRes.of(task));
        });
        return getTasksResList;
    }

    @Override
    @Transactional
    public void checkTask(Long userId, Long taskId){
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        Task task = taskRepository.findByTaskIdAndUser(taskId, user).orElseThrow(NotFoundTaskException::new);
        Integer status = 0;
        if(task.getStatus() == 0){
            status = 1;
        }
        task.updateStatus(status);
        taskRepository.save(task);
    }

    @Override
    public GetTaskRes readTask(Long userId, Long taskId) {
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        Task task = taskRepository.findByTaskIdAndUser(taskId, user).orElseThrow(NotFoundTaskException::new);
        return GetTaskRes.of(task);
    }

}
