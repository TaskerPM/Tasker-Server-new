package com.example.tasker.domain.user.controller;

import com.example.tasker.domain.user.dto.UserIdRes;
import com.example.tasker.domain.user.service.UserService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.dto.ErrorResponse;
import com.example.tasker.infra.sms.dto.SmsSendRequest;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Api(tags = "User API", value = "유저 관련 API")
public class UserController {

    private final UserService userService;


    /**
     * author 혜도
     * 로그인 회원가입 API - 호출 마다 jasypt 암호화, 이를 이용하여 해싱함수 처리 후 jwt 발급, sha256 jasypt 암호화 후 저장
     * jwt 갱신 문제 - 기존 리프레쉬 수정, redis 삭제
     */
    @Operation(summary = "사용자 회원가입 로그인", description = "jwt 새로 발급, Header input & output : 없음")
    @PostMapping("/login-signup")
    public ApplicationResponse<UserIdRes> login(@Valid @RequestBody SmsSendRequest smsSendRequest, HttpServletResponse response) {
        return ApplicationResponse.ok(userService.login(smsSendRequest, response));
    }

    /**
     * author 혜도
     * 자동 로그인 API
     */
    @Operation(summary = "사용자 자동 로그인", description = "리프레시 토큰 이용 - 만료 시 재로그인 유도(로그인 창) - 성공시 엑세스 갱신, Header input: 리프레쉬 토큰 output: 엑세스 토큰 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "REFRESH-TOKEN이 만료되었습니다.(J2003)")
    })
    @GetMapping("/loginToken")
    public ApplicationResponse<UserIdRes> loginToken(HttpServletResponse response) {
        return ApplicationResponse.create(userService.loginToken(response));
    }

    /**
     * author 혜도
     * 전화번호 반환 API
     */
    @Operation(summary = "사용자 전화번호 반환", description = "암호화 된 전화번호를 복호화 하여 반환합니다, Header input & output : 엑세스 토큰만 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004) \n JWT 토큰이 비어있습니다.(J2001)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/num/{userId}")
    public ApplicationResponse<SmsSendRequest> getUserNum(@Parameter(description = "유저 id", in = ParameterIn.PATH) @PathVariable("userId") long userId) {
        return ApplicationResponse.ok(SmsSendRequest.from(userService.getUserByUserId(userId)));

    }


//        @PostMapping("/login")

//    /**
//     * 사용자 정보 조회
//     * @param
//     * @return
//     */




//    /**
//     * 사용자 프로필 수정
//     */





//    /**
//     * 회원 탈퇴
//     * @return ResponseEntity<MessageResponse>
//     */





}