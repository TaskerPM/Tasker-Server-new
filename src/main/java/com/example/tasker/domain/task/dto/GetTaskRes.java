package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ApiModel(description = "Task 하나의 정보를 담은 객체")
public class GetTaskRes {

    private String title;
    private Long categoryId;
    private String categoryName;
    private String categoryColorBack;
    private String categoryColorText;
    private String timeStart;
    private String timeEnd;
    private List<String> notesContent;

    public static GetTaskRes of(Task task){
        List<String> noteContent = new ArrayList<>();
        task.getNoteList().forEach(note -> {
            noteContent.add(note.getContent());
        });
        return GetTaskRes.builder()
                .title(task.getTitle())
                .categoryId(task.getCategory().getCategoryId())
                .categoryName(task.getCategory().getName())
                .categoryColorBack(task.getCategory().getColor().getColorBack())
                .categoryColorText(task.getCategory().getColor().getColorText())
                .timeStart(task.getTimeStart())
                .timeEnd(task.getTimeEnd())
                .notesContent(noteContent)
                .build();
    }
}
