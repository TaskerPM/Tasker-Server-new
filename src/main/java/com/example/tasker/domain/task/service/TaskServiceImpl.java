package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public void deleteTask(Long userId, Long taskId) {

        taskRepository.deleteByUserUserIdAndTaskId(userId, taskId);

    }

}
