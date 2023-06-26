package com.example.tasker.domain.category.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "카테고리 조회를 위한 객체")
public class ReadCategoryRes {
    private String name;
    private String colorBack;
    private String colorText;
}
