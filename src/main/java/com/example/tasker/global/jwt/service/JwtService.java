package com.example.tasker.global.jwt.service;

import com.example.tasker.domain.user.entity.User;

import javax.validation.Valid;

public interface JwtService {
    String createAccessJwt(@Valid String phoneId);
    String createRefreshJwt(@Valid String phoneId);

    String loginByRefresh();
    String resolveRefreshToken();

    String callApi();

    User getUser();
}
