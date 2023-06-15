package com.example.tasker.domain.task.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskRes {

    private Long taskId;
    private String title;
    private String date;
    private Integer status;

}
