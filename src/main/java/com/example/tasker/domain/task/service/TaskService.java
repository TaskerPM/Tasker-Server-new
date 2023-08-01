package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.*;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.dto.BaseException;

import java.util.List;

public interface TaskService {

    PostTaskRes createTask(User user, PostTaskReq postTaskReq, String date);

    void deleteTask(User user, Long taskId) throws BaseException;

    PatchTaskDetailRes editTaskDetail(User user, PutTaskDetailReq putTaskDetailReq, Long taskId) throws BaseException;

    List<GetTasksRes> getTasksByDate(User user, String date);

    GetTasksRes checkTask(User user, Long taskId);

    GetTaskRes readTask(User user, Long taskId);
}
