package com.example.tasker.domain.category.dto;

import com.example.tasker.domain.task.entity.Task;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "모아보기를 보여주는 객체입니다.")
public class GatheringRes {
    // task
    @Schema(description = "테스커 Id", defaultValue = "2")
    private Long taskId;
    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    private String title;
    @Schema(description = "날짜", defaultValue = "20230801")
    private String date;

    public static GatheringRes of(Task task){
        return GatheringRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .date(task.getDate())
                .build();
    }
}
