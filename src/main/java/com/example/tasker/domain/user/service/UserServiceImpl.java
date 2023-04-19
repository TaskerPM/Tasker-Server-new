package com.example.tasker.domain.user.service;

import com.example.tasker.domain.user.dto.UserIdRes;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.exception.PhoneNumDuplicateException;
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
    public User join(SmsSendRequest smsSendRequest) {
        String sha = sha256Util.encrypt(smsSendRequest.getPhoneNum());
        if(userRepository.findByPhoneNumberSha(sha).isPresent())
            throw new PhoneNumDuplicateException();
        User user = new User();
        user.setPhoneNumberSha(sha);
        user.setPhoneNumber(smsSendRequest.getPhoneNum());
        return userRepository.save(SmsSendRequest.toEntity(user));
    }
    @Override
    public UserIdRes login(String phoneNum, HttpServletResponse response) {
        User user = userRepository.findByPhoneNumberSha(sha256Util.encrypt(phoneNum)).orElseThrow(NotFoundUserException::new);
        String refreshJwt = jwtService.createRefreshJwt(user.getPhoneNumber());
        userRefreshTokenService.insertRefreshToken(refreshJwt, user);
        response.setHeader("access_token", jwtService.createAccessJwt(user.getPhoneNumber()));
        response.setHeader("refresh_token", refreshJwt);
        return UserIdRes.from(user);
    }

    @Override
    public UserIdRes loginToken(HttpServletResponse response) {
        response.setHeader("access_token", jwtService.createAccessJwt(jwtService.loginByRefresh()));
        return UserIdRes.from(userRepository.findByPhoneNumber(jwtService.loginByRefresh()).orElseThrow(NotFoundUserException::new));
    }

}

