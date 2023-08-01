package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.task.entity.Task;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ApiModel(description = "Task 하나의 정보를 담은 객체")
public class GetTaskRes {

    @Schema(description = "테스커 Id", defaultValue = "2")
    private Long taskId;
    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    private String title;
    @Schema(description = "시작 시간", defaultValue = "1310")
    private String timeStart;
    @Schema(description = "끝난 시간", defaultValue = "2000")
    private String timeEnd;

    @Schema(description = "카테고리 Id", defaultValue = "1")
    private Long categoryId;
    @Schema(description = "카테고리 이름", defaultValue = "UX스터디")
    private String categoryName;
    @Schema(description = "카테고리 배경 색깔", defaultValue = "#FEDFDF")
    private String categoryColorBack;
    @Schema(description = "카테고리 텍스트 색깔", defaultValue = "#D87A7A")
    private String categoryColorText;

    @Schema(description = "노트 리스트")
    private List<String> notesContent;

    @Builder
    public GetTaskRes(Long taskId, String title, String timeStart, String timeEnd, List<String> notesContent) {
        this.taskId = taskId;
        this.title = title;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.notesContent = notesContent;
    }

    public void updateCategory(Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getName();
        this.categoryColorBack = category.getColor().getColorBack();
        this.categoryColorText = category.getColor().getColorText();
    }

    public static GetTaskRes of(Task task){
        List<String> noteContent = new ArrayList<>();
        task.getNoteList().forEach(note -> {
            noteContent.add(note.getContent());
        });

        GetTaskRes getTaskRes = GetTaskRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .timeStart(task.getTimeStart())
                .timeEnd(task.getTimeEnd())
                .notesContent(noteContent)
                .build();
        if(task.getCategory() != null){
            getTaskRes.updateCategory(task.getCategory());
        }
        return getTaskRes;
    }
}
