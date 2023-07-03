package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskRes {

    private Long taskId;
    private String title;
    private String date;
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
