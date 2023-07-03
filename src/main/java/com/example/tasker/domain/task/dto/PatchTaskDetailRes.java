package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import lombok.*;

import java.util.ArrayList;
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
    private List<GetNoteRes> notesContent;

    public static PatchTaskDetailRes of(Task task){
        List<GetNoteRes> getNoteRes = new ArrayList<>();
        task.getNoteList().forEach(note -> {
            getNoteRes.add(GetNoteRes.builder()
                    .noteId(note.getNoteId())
                    .notesContent(note.getContent())
                    .build());
        });
        return PatchTaskDetailRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .categoryName(task.getCategory().getName())
                .categoryColorBack(task.getCategory().getColor().getColorBack())
                .categoryColorText(task.getCategory().getColor().getColorText())
                .time_start(task.getTimeStart())
                .time_end(task.getTimeEnd())
                .notesContent(getNoteRes)
                .build();
    }
}
