package com.example.tasker.domain.category.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "카테고리 수정을 위한 객체")
public class UpdateCategoryReq {
    @Schema(description = "카테고리 이름", defaultValue = "스터디")
    private String name;
    @Schema(description = "카테고리 배경 색깔", defaultValue = "#FFF9C6")
    private String colorBack;
    @Schema(description = "카테고리 텍스트 색깔", defaultValue = "#DE9B38")
    private String colorText;
}
