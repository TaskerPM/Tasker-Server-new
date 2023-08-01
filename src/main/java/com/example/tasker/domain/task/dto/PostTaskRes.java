package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskRes {

    @Schema(description = "테스커 Id", defaultValue = "2")
    private Long taskId;

    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    private String title;

    @Schema(description = "날짜", defaultValue = "20230801")
    private String date;

    @Schema(description = "테스커 상태 (0 : 미완료, 1 : 완료)", defaultValue = "0")
    private Integer status;

    public static PostTaskRes of(Task task){
        return PostTaskRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .date(task.getDate())
                .status(task.getStatus())
                .build();
    }
}
