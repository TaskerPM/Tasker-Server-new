package com.example.tasker.domain.task;

import com.example.tasker.domain.task.service.TaskProvider;
import com.example.tasker.domain.task.service.TaskService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskProvider taskProvider;
    private final TaskService taskService;



}
