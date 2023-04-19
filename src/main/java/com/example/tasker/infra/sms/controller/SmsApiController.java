package com.example.tasker.infra.sms.controller;

import com.example.tasker.infra.sms.dto.SmsSendRequest;
import com.example.tasker.infra.sms.dto.SmsSendResponse;
import com.example.tasker.infra.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("v1/sms")
@RequiredArgsConstructor
@Api(tags = "SMS API")
public class SmsApiController {

    private final SmsService smsService;

    @ApiOperation(value = "문자발송", notes = "문자를 발송합니다.")
    @PostMapping("/send")
    public ResponseEntity<SmsSendResponse> smsSend(@Valid @RequestBody SmsSendRequest smsSendRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {

        String value = smsService.sendSms(smsSendRequest);

        return ResponseEntity.ok(SmsSendResponse.builder()
                .value(value)
                .build());
    }




}
