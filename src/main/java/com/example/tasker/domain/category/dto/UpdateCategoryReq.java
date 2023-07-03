package com.example.tasker.domain.category.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "카테고리 수정을 위한 객체")
public class UpdateCategoryReq {
    private String name;
    private String colorBack;
    private String colorText;
}
