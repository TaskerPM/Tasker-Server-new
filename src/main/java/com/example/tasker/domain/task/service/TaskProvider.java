package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;

public interface TaskProvider {
    GetTasksRes getTasksByDate(Long userId, String date);

}
