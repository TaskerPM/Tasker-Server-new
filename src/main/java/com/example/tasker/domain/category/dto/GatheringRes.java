package com.example.tasker.domain.category.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "모아보기")
public class GatheringRes {

    // 날짜
    private String date;
    // task
}
