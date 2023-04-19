package com.example.tasker.domain.user.dto;

import com.example.tasker.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인 응답을 위한 객체")
public class UserLoginRes {

    private Long userId;
    private String accessToken;
    private String refreshToken;

    public static UserLoginRes from (User user , String accessToken , String refreshToken) {
        return UserLoginRes.builder()
                .userId(user.getUserId())
                .accessToken(accessToken)
                .refreshToken(refreshToken).
                build();
    }

}
