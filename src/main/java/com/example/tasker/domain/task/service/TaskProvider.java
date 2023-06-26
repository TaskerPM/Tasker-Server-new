package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.entity.Category;
import com.example.tasker.domain.task.entity.Color;

import java.util.List;

public interface TaskProvider {
    List<GetTasksRes> getTasksByDate(Long userId, String date);

    Category getCategoryByTaskId(Long taskId);

    Color getColorByCategoryId(Long categoryId);

}
