package com.example.tasker.domain.task;

import com.example.tasker.domain.task.dto.*;
import com.example.tasker.domain.task.service.TaskProvider;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.dto.BaseException;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.tasker.global.dto.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskProvider taskProvider;
    private final TaskService taskService;
    private final JwtService jwtService;

    @PostMapping("/home/{date}")
    public ApplicationResponse<PostTaskRes> createTask(@RequestBody @Valid PostTaskReq postTaskReq, @PathVariable("date") String date) {

        Long userId = jwtService.getUserId();

        return ApplicationResponse.create(taskService.createTask(userId, postTaskReq, date));
    }


    @GetMapping("/home/{date}")
    public ApplicationResponse<List<GetTasksRes>> getTasksByDate(@PathVariable("date") String date) {
        Long userId = jwtService.getUserId();

        taskProvider.getTasksByDate(userId, date);

        return ApplicationResponse.ok(taskProvider.getTasksByDate(userId, date));
    }

    @PatchMapping("/home/{date}")
    public ApplicationResponse<Long> deleteTask(@PathVariable("date") String date, @RequestParam Long taskId){

        Long userId = jwtService.getUserId();
        taskService.deleteTask(userId, taskId);

        return ApplicationResponse.ok(taskId);
    }

    //TODO
    // service 구현
    @PatchMapping("/home/{date}/{task_id}")
    public ApplicationResponse<PatchTaskDetailRes> editTaskDetail(@RequestBody @Valid PatchTaskDetailReq patchTaskDetailReq, @PathVariable("date") String date, @PathVariable("task_id") Long taskId) throws BaseException {

        Long userId = jwtService.getUserId();

        return ApplicationResponse.create(taskService.editTaskDetail(userId, patchTaskDetailReq, date, taskId));
    }



}
