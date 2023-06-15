package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskProviderImpl implements TaskProvider{

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public GetTasksRes getTasksByDate(Long userId, String date) {

        List<Task> tasks = taskRepository.findByUserUserIdAndDate(userId, date);

        return GetTasksRes.builder()
                .tasks(tasks)
                .build();
    }
}
