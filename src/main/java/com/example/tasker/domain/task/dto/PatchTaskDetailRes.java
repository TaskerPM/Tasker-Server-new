package com.example.tasker.domain.task.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchTaskDetailRes {

    private Long taskId;
    private String title;
    private String categoryName;
    private String categoryColorBack;
    private String categoryColorText;
    private String time_start;
    private String time_end;
    private List<String> notesContent;

}
