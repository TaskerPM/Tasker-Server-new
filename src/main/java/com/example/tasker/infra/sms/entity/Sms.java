package com.example.tasker.infra.sms.entity;

import com.example.tasker.domain.user.entity.UserRefreshToken;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sms extends BaseTimeEntity {

    @Id
    @Column(name = "sms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "phone_cnt")
    private Integer cnt = 0;


}
