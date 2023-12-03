package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.*;
import com.example.tasker.global.dto.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TaskService {

    PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date, HttpServletResponse response);

    void deleteTask(Long userId, Long taskId) throws BaseException;

    PatchTaskDetailRes editTaskDetail(Long userId, PatchTaskDetailReq patchTaskDetailReq, Long taskId) throws BaseException;

    List<GetTasksRes> getTasksByDate(Long userId, String date);

    void checkTask(Long userId, Long taskId);

    GetTaskRes readTask(Long userId, Long taskId);
}
