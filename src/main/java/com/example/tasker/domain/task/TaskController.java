package com.example.tasker.domain.task;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskService taskService;
    private final JwtService jwtService;

    @PostMapping("/list/{date}")
    @Operation(summary = "(Post) 테스크 생성", description = "테스크 생성 API 입니다.")
    public ApplicationResponse<PostTaskRes> createTask(@RequestBody @Valid PostTaskReq postTaskReq,
                                                       @PathVariable("date") String date) {
        Long userId = jwtService.getUserId();
        return ApplicationResponse.create(taskService.createTask(userId, postTaskReq, date));
    }

    @PostMapping("/list/{task_id}/category")
    @Operation(summary = "(Post) 테스크에 카테고리 추가", description = "테스크에 카테고리 추가 API 입니다.")
    public ApplicationResponse<String> addCategory(@PathVariable Long task_id,
                                                   @RequestParam("categoryId") Long categoryId) {
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(taskService.addCategory(userId, task_id, categoryId));
    }

    @DeleteMapping("/list/{task_id}")
    @Operation(summary = "(Delete) 테스크 삭제", description = "테스크 삭제 API 입니다.")
    public ApplicationResponse<String> deleteTask(@PathVariable Long task_id){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(taskService.deleteTask(userId, task_id));
    }

    @GetMapping("/list/{date}")
    @Operation(summary = "(Get) 테스크 보기", description = "날짜에 해당하는 테스크 보기 API 입니다.")
    public ApplicationResponse<List<GetTasksRes>> getTasksByDate(@PathVariable("date") String date) {
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(taskService.getTasksByDate(userId, date));
    }

}
