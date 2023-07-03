package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.*;
import com.example.tasker.global.dto.BaseException;

import java.util.List;

public interface TaskService {

    PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date);

    void deleteTask(Long userId, Long taskId) throws BaseException;

    PatchTaskDetailRes editTaskDetail(Long userId, PatchTaskDetailReq patchTaskDetailReq, Long taskId) throws BaseException;

    List<GetTasksRes> getTasksByDate(Long userId, String date);

    String checkTask(Long userId, Long taskId);

    String deleteNote(Long userId, Long noteId);
}
