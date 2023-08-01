package com.example.tasker.domain.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutTaskDetailReq {

    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    private String title;
    @Schema(description = "카테고리 Id", defaultValue = "1")
    private Long categoryId;
    @Schema(description = "시작 시간", defaultValue = "1310")
    private String timeStart;
    @Schema(description = "끝난 시간", defaultValue = "2000")
    private String timeEnd;
    @Schema(description = "노트 리스트")
    private List<String> notesContent;

}
