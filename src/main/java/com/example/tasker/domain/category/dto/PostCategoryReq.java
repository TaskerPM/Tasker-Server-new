package com.example.tasker.domain.category.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "카테고리 생성 위한 객체")
public class PostCategoryReq {

    @Schema(description = "카테고리 이름", defaultValue = "스터디")
    private String name;
    @Schema(description = "카테고리 배경 색깔", defaultValue = "#FEDFDF")
    private String colorBack;
    @Schema(description = "카테고리 텍스트 색깔", defaultValue = "#D87A7A")
    private String colorText;
}
