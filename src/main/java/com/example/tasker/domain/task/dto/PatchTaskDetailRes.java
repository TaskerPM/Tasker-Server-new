package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public static PatchTaskDetailRes of(Task task){
        return PatchTaskDetailRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .categoryName(task.getCategory().getName())
                .categoryColorBack(task.getCategory().getColor().getColorBack())
                .categoryColorText(task.getCategory().getColor().getColorText())
                .time_start(task.getTimeStart())
                .time_end(task.getTimeEnd())
                .notesContent(task.getNotes().stream().map(Note::getContent).collect(Collectors.toList()))
                .build();
    }
}
