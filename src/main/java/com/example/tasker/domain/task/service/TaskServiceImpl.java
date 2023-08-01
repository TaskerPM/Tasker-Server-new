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
import com.example.tasker.global.dto.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.tasker.global.dto.BaseResponseStatus.INVALID_USER_JWT;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public PostTaskRes createTask(User user, PostTaskReq postTaskReq, String date) {
        Task save = taskRepository.save(postTaskReq.toEntity(user, date));
        return PostTaskRes.of(save);
    }

    @Override
    @Transactional
    public void deleteTask(User user, Long taskId) throws BaseException {

        Task taskFindById = taskRepository.findById(taskId).get();

        //유저 일치 확인
        if (!taskFindById.getUser().equals(user)) {
            throw new BaseException(INVALID_USER_JWT);
        }

        taskRepository.deleteByUserAndTaskId(user, taskId);
    }

    @Override
    @Transactional
    public PatchTaskDetailRes editTaskDetail(User user, PutTaskDetailReq putTaskDetailReq, Long taskId) throws BaseException {
        Task task = taskRepository.findById(taskId).get();
        boolean check = false;

        //유저 일치 확인
        if (!task.getUser().equals(user)) {
            throw new BaseException(INVALID_USER_JWT);
        }

        if(putTaskDetailReq.getCategoryId() != null && putTaskDetailReq.getCategoryId() != 0){
            Category category = categoryRepository.findById(putTaskDetailReq.getCategoryId())
                    .orElseThrow(NotFoundCategoryException::new);
            task.updateCategory(category);
            check = true;
        }

        if(putTaskDetailReq.getTitle() != null && !putTaskDetailReq.getTitle().equals(task.getTitle())){
            task.updateTitle(putTaskDetailReq.getTitle());
        }

        if(putTaskDetailReq.getTimeStart() != null && !putTaskDetailReq.getTimeStart().equals(task.getTimeStart())){
            // case0. 종료 시간이 없을 때
            if(putTaskDetailReq.getTimeEnd() == null && putTaskDetailReq.getTimeEnd().equals(task.getTimeEnd())){
                throw new TimeError4Exception();
            }

            int startTime = Integer.parseInt(putTaskDetailReq.getTimeStart());
            int endTime = Integer.parseInt(putTaskDetailReq.getTimeEnd());

            // case1. 시작 시간과 종료시간이 같음
            if(startTime == endTime){
                throw new TimeError2Exception();
            }
            // case2. 시작 시간이 종료 시간보다 이를 때
            if(startTime > endTime){
                throw new TimeError3Exception();
            }

            // case3. 다른 task와 시간이 겹침
            Task findStartTime = taskRepository.findByDateAndTimeStart(task.getDate(), putTaskDetailReq.getTimeStart());
            Task findEndTime = taskRepository.findByDateAndTimeEnd(task.getDate(), putTaskDetailReq.getTimeStart());
            if(findStartTime != null){
                throw new TimeError1Exception();
            }
            if(findEndTime != null){
                throw new TimeError1Exception();
            }

            task.updateTime(putTaskDetailReq.getTimeStart(), putTaskDetailReq.getTimeEnd());
            check = true;
        }

        if (putTaskDetailReq.getNotesContent() != null && !putTaskDetailReq.getNotesContent().isEmpty()) {
            HashSet<String> noteContentSet = new HashSet<>();
            task.getNoteList().forEach(note -> {
                noteContentSet.add(note.getContent());
            });

            putTaskDetailReq.getNotesContent().forEach(note->{
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

            check = true;
        }

        if(check){
            Task save = taskRepository.save(task);
            return PatchTaskDetailRes.of(save);
        }else{
            throw new NoModifiedTaskException();
        }
    }

    @Override
    public List<GetTasksRes> getTasksByDate(User user, String date) {
        List<Task> tasks = taskRepository.findByUserAndDate(user, date);
        List<GetTasksRes> getTasksResList = new ArrayList<>();
        tasks.forEach(task -> {
            getTasksResList.add(GetTasksRes.of(task));
        });
        return getTasksResList;
    }

    @Override
    @Transactional
    public GetTasksRes checkTask(User user, Long taskId){
        Task task = taskRepository.findByTaskIdAndUser(taskId, user).orElseThrow(NotFoundTaskException::new);
        Integer status = 0;
        if(task.getStatus() == 0){
            status = 1;
        }
        task.updateStatus(status);
        Task saveTask = taskRepository.save(task);
        return GetTasksRes.of(saveTask);
    }

    @Override
    public GetTaskRes readTask(User user, Long taskId) {
        Task task = taskRepository.findByTaskIdAndUser(taskId, user).orElseThrow(NotFoundTaskException::new);
        return GetTaskRes.of(task);
    }

}
