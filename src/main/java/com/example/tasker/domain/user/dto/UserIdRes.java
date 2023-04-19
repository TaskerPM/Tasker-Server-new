package com.example.tasker.domain.user.dto;

import com.example.tasker.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "자동 로그인 응답을 위한 객체")
public class UserIdRes {

    private Long userId;

    public static UserIdRes from (User user) {
        return UserIdRes.builder()
                .userId(user.getUserId())
                .build();
    }

}
