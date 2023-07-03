package com.example.tasker.domain.category.dto;

import com.example.tasker.domain.category.entity.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "카테고리 조회를 위한 객체")
@Builder
public class ReadCategoryRes {
    private Long categoryId;
    private String name;
    private String colorBack;
    private String colorText;

    public static ReadCategoryRes of(Category category){
        return ReadCategoryRes.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .colorBack(category.getColor().getColorBack())
                .colorText(category.getColor().getColorText())
                .build();
    }
}
