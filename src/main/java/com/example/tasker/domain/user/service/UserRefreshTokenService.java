package com.example.tasker.domain.user.service;


import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.entity.UserRefreshToken;

public interface UserRefreshTokenService {

    UserRefreshToken insertRefreshToken(String refreshJwt, User user);
    UserRefreshToken findByUser(User user);
    UserRefreshToken findByRefreshToken(String userRefreshToken);

}
