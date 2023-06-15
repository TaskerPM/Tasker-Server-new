package com.example.tasker.domain.task;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.service.TaskProvider;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskProvider taskProvider;
    private final TaskService taskService;
    private final JwtService jwtService;

    @PostMapping("/home/list/{date}")
    public ApplicationResponse<PostTaskRes> createTask(@RequestBody @Valid PostTaskReq postTaskReq, @PathVariable("date") String date) {

        Long userId = jwtService.getUserId();

        return ApplicationResponse.create(taskService.createTask(userId, postTaskReq, date));
    }

    @GetMapping("/home/list/{date}")
    public ApplicationResponse<GetTasksRes> getTasksByDate(@PathVariable("date") String date) {
        Long userId = jwtService.getUserId();

        taskProvider.getTasksByDate(userId, date);

        return ApplicationResponse.ok(taskProvider.getTasksByDate(userId, date));
    }

    @PatchMapping("/home/list/{date}")
    public ApplicationResponse<Long> deleteTask(@PathVariable("date") String date, @RequestParam Long taskId){

        Long userId = jwtService.getUserId();
        taskService.deleteTask(userId, taskId);

        return ApplicationResponse.ok(taskId);
    }







}
