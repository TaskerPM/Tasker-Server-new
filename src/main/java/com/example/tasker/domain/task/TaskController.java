package com.example.tasker.domain.task;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.service.TaskProvider;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskProvider taskProvider;
    private final TaskService taskService;
    private final JwtService jwtService;


//    @GetMapping("/home")
//    ApplicationResponse<GetTasksRes> getTasksByDate(@RequestParam String date) {
//
//        return null;
//    }


}
