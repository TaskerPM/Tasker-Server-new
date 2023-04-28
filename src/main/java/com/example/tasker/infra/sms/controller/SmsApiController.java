package com.example.tasker.infra.sms.controller;

import com.example.tasker.global.dto.ErrorResponse;
import com.example.tasker.infra.sms.dto.SmsSendRequest;
import com.example.tasker.infra.sms.dto.SmsSendResponse;
import com.example.tasker.infra.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Api(tags = "SMS API", value = "문자 관련 API")
public class SmsApiController {

    private final SmsService smsService;

    @Operation(summary = "문자발송", description = "문자를 발송합니다.")
    @ApiResponse(responseCode = "400", description = "전화번호를 확인하여 주세요.(Size, Digits)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping("/send")
    public ResponseEntity<SmsSendResponse> smsSend(@Valid @RequestBody SmsSendRequest smsSendRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {

        String value = smsService.sendSms(smsSendRequest);


        return ResponseEntity.ok(SmsSendResponse.builder()
                .value(value)
                .message("발송이 완료되었습니다.")
                .build());
    }




}
