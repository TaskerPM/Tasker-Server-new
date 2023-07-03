package com.example.tasker.domain.category.dto;

import com.example.tasker.domain.task.entity.Task;
import io.swagger.annotations.ApiModel;
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
    private Long taskId;
    private String title;
    private String date;

    public static GatheringRes of(Task task){
        return GatheringRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .date(task.getDate())
                .build();
    }
}
