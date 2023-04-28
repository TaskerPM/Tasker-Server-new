package com.example.tasker.infra.sms.dto;

import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.config.jasypt.JasyptConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "전화번호 요청 객체")
public class SmsSendRequest {


    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Digits(integer = 13, fraction = 0, message = "전화번호를 확인하여 주세요.")
    @Size(min = 11, max = 13, message = "전화번호를 확인하여 주세요.")
    @ApiModelProperty(notes = "전화번호를 입력해 주세요.")
    private String phoneNum;

    public static SmsSendRequest from(User user) {
        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(JasyptConfig.class);
        StringEncryptor stringEncryptor = beanFactory.getBean("jasyptEncyptor", StringEncryptor.class);
        return SmsSendRequest.builder()
                .phoneNum(stringEncryptor.decrypt(user.getPhoneNumber()))
                .build();
    }

    public static User toEntity(User user, SmsSendRequest smsSendRequest) {
        final BeanFactory beanFactory = new AnnotationConfigApplicationContext(JasyptConfig.class);
        StringEncryptor stringEncryptor = beanFactory.getBean("jasyptEncyptor", StringEncryptor.class);
        user.setPhoneNumber(stringEncryptor.encrypt(smsSendRequest.getPhoneNum()));
        return user;

    }



    }
