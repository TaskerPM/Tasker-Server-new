package com.example.tasker.infra.sms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "문자인증 값 반환을 위한 객체")
public class SmsSendResponseCustom {
    private String value;
    private Integer cnt;
}
