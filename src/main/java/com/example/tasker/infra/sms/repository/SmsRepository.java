package com.example.tasker.infra.sms.repository;

import com.example.tasker.infra.sms.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    Optional<Sms> findSmsByPhoneNumber(String phoneNumber);
}
