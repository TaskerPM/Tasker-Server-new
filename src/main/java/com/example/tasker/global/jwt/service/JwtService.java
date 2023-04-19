package com.example.tasker.global.jwt.service;

import javax.validation.Valid;

public interface JwtService {
    String createAccessJwt(@Valid String UserId);
    String createRefreshJwt(@Valid String UserId);

    String loginByRefresh();

    String callApi();


}
