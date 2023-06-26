package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.category.entity.Category;
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
    private Category category;
    //0 미완료 1 완료
    private Integer isCompeleted;

}
