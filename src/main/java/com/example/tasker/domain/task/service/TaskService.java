package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;

import java.util.List;

public interface TaskService {
    PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date);
    String addCategory(Long userId, Long taskId, Long categoryId);
    String deleteTask(Long userId, Long taskId);

    List<GetTasksRes> getTasksByDate(Long userId, String date);
}
