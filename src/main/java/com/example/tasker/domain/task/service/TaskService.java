package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.PatchTaskDetailReq;
import com.example.tasker.domain.task.dto.PatchTaskDetailRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.global.dto.BaseException;

public interface TaskService {
    PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date);

    void deleteTask(Long userId, Long taskId) throws BaseException;

    PatchTaskDetailRes editTaskDetail(Long userId, PatchTaskDetailReq patchTaskDetailReq, String date, Long taskId) throws BaseException;
}
