package com.example.tasker.domain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class GetTasksRes {

    private Long taskId;
    private String title;
    private String time_start;
    private String time_end;
    private String categoryName;
    private String categoryColorBack;
    private String categoryColorText;
    private Integer isCompeleted; //0 미완료 1 완료

}
