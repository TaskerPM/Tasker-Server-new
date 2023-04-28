package com.example.tasker.domain.user.service;

import com.example.tasker.domain.user.dto.UserIdRes;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.repository.UserRepository;
import com.example.tasker.global.encryption.SHA256Util;
import com.example.tasker.global.jwt.service.JwtService;
import com.example.tasker.infra.sms.dto.SmsSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRefreshTokenService userRefreshTokenService;
    private final JwtService jwtService;
    private final SHA256Util sha256Util;


    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
    }

    @Override
    public UserIdRes login(SmsSendRequest smsSendRequest, HttpServletResponse response) {
        String phoneNumber = smsSendRequest.getPhoneNum();
        String sha = sha256Util.encrypt(phoneNumber);
        User user = userRepository.findByPhoneNumberSha(sha).orElseGet(User::new);
        user.setPhoneNumberSha(sha);
        userRepository.save(SmsSendRequest.toEntity(user, smsSendRequest));
        String refreshJwt = jwtService.createRefreshJwt(user.getPhoneNumber());
        userRefreshTokenService.insertRefreshToken(refreshJwt, user);
        response.setHeader("access_token", jwtService.createAccessJwt(user.getPhoneNumber()));
        response.setHeader("refresh_token", refreshJwt);
        return UserIdRes.from(user);
    }


    @Override
    public UserIdRes loginToken(HttpServletResponse response) {
        // 리프레시 토큰 만료 된 것(갱신 혹은 시간)
        userRefreshTokenService.findByRefreshToken(jwtService.resolveRefreshToken());
        response.setHeader("access_token", jwtService.createAccessJwt(jwtService.loginByRefresh()));
        return UserIdRes.from(userRepository.findByPhoneNumber(jwtService.loginByRefresh()).orElseThrow(NotFoundUserException::new));
    }

}

