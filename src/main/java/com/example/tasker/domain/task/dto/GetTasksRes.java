package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.task.entity.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTasksRes {

    @Schema(description = "테스커 Id", defaultValue = "2")
    private Long taskId;
    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    private String title;
    @Schema(description = "시작 시간", defaultValue = "1310")
    private String timeStart;
    @Schema(description = "끝난 시간", defaultValue = "2000")
    private String timeEnd;
    @Schema(description = "카테고리 이름", defaultValue = "스터디")
    private String categoryName;
    @Schema(description = "카테고리 배경 색깔")
    private String categoryColorBack;
    @Schema(description = "카테고리 텍스트 색깔")
    private String categoryColorText;
    @Schema(description = "테스커 상태 (0 : 미완료, 1 : 완료)", defaultValue = "0")
    private Integer status;

    @Builder
    public GetTasksRes(Long taskId, String title, String timeStart, String timeEnd, Integer status) {
        this.taskId = taskId;
        this.title = title;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.status = status;
    }

    public void updateCategory(Category category){
        this.categoryName = category.getName();
        this.categoryColorBack = category.getColor().getColorBack();
        this.categoryColorText = category.getColor().getColorText();
    }

    public static GetTasksRes of(Task task){
        GetTasksRes getTasksRes = GetTasksRes.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .timeStart(task.getTimeStart())
                .timeEnd(task.getTimeEnd())
                .status(task.getStatus())
                .build();
        if(task.getCategory() != null){
            getTasksRes.updateCategory(task.getCategory());
        }
        return getTasksRes;
    }

}
