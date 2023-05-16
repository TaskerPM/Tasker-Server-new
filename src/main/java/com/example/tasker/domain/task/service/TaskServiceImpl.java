package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
}
