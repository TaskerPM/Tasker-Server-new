package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;

import java.util.List;

public interface TaskProvider {
    List<GetTasksRes> getTasksByDate(Long userId, String date);

}
