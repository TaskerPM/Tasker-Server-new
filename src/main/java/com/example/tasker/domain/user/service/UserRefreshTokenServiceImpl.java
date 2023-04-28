package com.example.tasker.domain.user.service;

import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.entity.UserRefreshToken;
import com.example.tasker.domain.user.repository.UserRefreshTokenRepository;
import com.example.tasker.global.jwt.exception.ExpireRefreshException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserRefreshTokenServiceImpl implements UserRefreshTokenService{
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    @Transactional
    public UserRefreshToken insertRefreshToken(String refreshToken, User user) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUser(user).orElseGet(UserRefreshToken::new);
        userRefreshToken.setUser(user);
        userRefreshToken.setUserRefreshToken(refreshToken);
        return userRefreshTokenRepository.save(userRefreshToken);

    }

    @Override
    public UserRefreshToken findByUser(User user) {
        return userRefreshTokenRepository.findByUser(user).orElseGet(UserRefreshToken::new);
    }

    @Override
    public UserRefreshToken findByRefreshToken(String userRefreshToken) {
        return userRefreshTokenRepository.findByUserRefreshToken(userRefreshToken).orElseThrow(ExpireRefreshException::new);
    }


}
