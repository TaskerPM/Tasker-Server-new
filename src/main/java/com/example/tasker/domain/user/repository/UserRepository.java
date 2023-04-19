package com.example.tasker.domain.user.repository;

import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long userId);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumberSha(String encrypt);
}


