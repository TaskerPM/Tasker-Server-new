package com.example.tasker.domain.user.repository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserRefreshTokenId(Long userRefreshTokenId);
    Optional<UserRefreshToken> findByUserRefreshToken(String userRefreshToken);
    Optional<UserRefreshToken> findByUser(User user);



}