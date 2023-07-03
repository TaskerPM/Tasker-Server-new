package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

    public static GetTasksRes of(Task task){
        return GetTasksRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .time_start(task.getTimeStart())
                .time_end(task.getTimeEnd())
                .categoryName(task.getCategory().getName())
                .categoryColorBack(task.getCategory().getColor().getColorBack())
                .categoryColorText(task.getCategory().getColor().getColorText())
                .isCompeleted(task.getStatus())
                .build();
    }
}
