package com.example.tasker.domain.user.service;

import com.example.tasker.domain.user.dto.UserIdRes;
import com.example.tasker.domain.user.dto.UserLoginRes;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.infra.sms.dto.SmsSendRequest;

import javax.servlet.http.HttpServletResponse;


public interface UserService {
    User getUserByUserId(Long userId);

    User join(SmsSendRequest smsSendRequest);

    UserIdRes login(String phoneNum, HttpServletResponse response);


    UserIdRes loginToken(HttpServletResponse response);
}
