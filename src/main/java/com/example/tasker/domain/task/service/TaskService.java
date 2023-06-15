package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;

public interface TaskService {
    PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date);

    void deleteTask(Long userId, Long taskId);
}
