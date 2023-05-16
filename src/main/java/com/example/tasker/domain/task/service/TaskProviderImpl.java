package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskProviderImpl implements TaskProvider{

    private final TaskRepository taskRepository;
}
