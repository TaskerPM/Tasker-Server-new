package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.entity.TaskCategory;
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
    private String date;
    private String time_start;
    private String time_end;
    private List<TaskCategory> taskCategories;
    private Integer status;

}
