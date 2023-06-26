package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.PatchTaskDetailReq;
import com.example.tasker.domain.task.dto.PatchTaskDetailRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.repository.UserRepository;
import com.example.tasker.global.dto.BaseException;
import com.example.tasker.global.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.tasker.global.dto.BaseResponseStatus.INVALID_USER_JWT;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date) {

        Optional<User> user = userRepository.findByUserId(userId);
        Task save = taskRepository.save(postTaskReq.toEntity(user.get(), date));

        return PostTaskRes.builder()
                .taskId(save.getTaskId())
                .title(save.getTitle())
                .date(date)
                .status(save.getStatus())
                .build();
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
    public PatchTaskDetailRes editTaskDetail(Long userId, PatchTaskDetailReq patchTaskDetailReq, String date, Long taskId) throws BaseException {

        Optional<User> user = userRepository.findByUserId(userId);
        Task taskFindById = taskRepository.findById(taskId).get();

        //유저 일치 확인
        if (!taskFindById.getUser().equals(user.get())) {
            throw new BaseException(INVALID_USER_JWT);
        }

        //TODO
        // task 수정사항(patchTaskDetailReq) 적용
        // 카테고리 생성 및 적용
        //  - 테스크 <--> 카테고리 관계가 n:m이 맞나..?? 다시 확인 필요
        Task task = Task.builder()
                .taskId(taskFindById.getTaskId())
                .title(patchTaskDetailReq.getTitle())
                .date(taskFindById.getDate())
                .time_start(patchTaskDetailReq.getTime_start())
                .time_end(patchTaskDetailReq.getTime_end())
                .status(taskFindById.getStatus())
                .user(user.get())
                .build();


        if(!patchTaskDetailReq.getNotesContent().isEmpty()){
            for(String s : patchTaskDetailReq.getNotesContent()){
                Note note = Note.builder()
                        .content(s)
                        .task(task)
                        .build();

                task.addNote(note);
            }
        }

        Task save = taskRepository.save(task);


        return PatchTaskDetailRes.builder()
                .taskId(save.getTaskId())
                .title(save.getTitle())
//                .categoryName()
//                .categoryColorBack()
//                .categoryColorText()
                .time_start(save.getTime_start())
                .time_end(save.getTime_end())
                .notesContent(save.getNotes().stream().map(Note::getContent).collect(Collectors.toList()))
                .build();
    }

}
