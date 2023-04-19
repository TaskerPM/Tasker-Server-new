package com.example.tasker.domain.user.controller;

import com.example.tasker.domain.user.dto.UserIdRes;
import com.example.tasker.domain.user.dto.UserLoginRes;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.exception.PhoneNumDuplicateException;
import com.example.tasker.domain.user.service.UserService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.exception.ExpireRefreshException;
import com.example.tasker.global.jwt.exception.NotFoundJwtException;
import com.example.tasker.global.jwt.service.JwtService;
import com.example.tasker.infra.sms.dto.SmsSendRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Api(tags = "User API")
public class UserController {

    private final UserService userService;

    /**
     * author 혜도
     * 회원 가입 API
     * 번호 보안 - 번호 jasypt 이용하여 저장
     */
    @Operation(summary = "사용자 회원가입", description = "인증된 번호로 회원가입을 진행합니다.")
    @ApiResponses({
            @ApiResponse(code = 409, message = "해당 전화번호는 이미 존재하는 전화번호입니다.(U0003)", response = PhoneNumDuplicateException.class),
    })
    @PostMapping("/join")
    public ApplicationResponse<String> signupUser(@Valid @RequestBody SmsSendRequest smsSendRequest) {
        userService.join(smsSendRequest);
        return ApplicationResponse.create("회원가입이 완료되었습니다.");
    }

    /**
     * author 혜도
     * 로그인 API - jwt 발급은 암호화 된 번호를 해싱함수 처리 후 발급
     */
    @Operation(summary = "사용자 로그인", description = "jwt 발급, Header input & output : 없음")
    @ApiResponse(code = 400, message = "해당 유저를 찾을 수 없습니다.(U0004)", response = NotFoundUserException.class)
    @GetMapping("/login")
    public ApplicationResponse<UserIdRes> login(@RequestParam("phoneNum") String phoneNum, HttpServletResponse response) {
        return ApplicationResponse.ok(userService.login(phoneNum, response));
    }

    /**
     * author 혜도
     * 자동 로그인 API
     */
    @Operation(summary = "사용자 자동 로그인", description = "리프레시 토큰 이용 - 만료 시 재로그인 유도(로그인 창) - 성공시 엑세스 갱신, Header input & output : 리프레쉬 토큰 & 엑세스 토큰 ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "해당 유저를 찾을 수 없습니다.(U0004)", response = NotFoundUserException.class),
            @ApiResponse(code = 403 , message = "REFRESH-TOKEN이 만료되었습니다.(J2003)", response = ExpireRefreshException.class)
    })    @GetMapping("/loginToken")
    public ApplicationResponse<UserIdRes> loginToken(HttpServletResponse response) {
        return ApplicationResponse.ok(userService.loginToken(response));
    }

    /**
     * author 혜도
     * 전화번호 반환 API
     */
    @Operation(summary = "사용자 전화번호 반환", description = "암호화 된 전화번호를 복호화 하여 반환합니다, Header input & output : 엑세스 토큰만 ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "해당 유저를 찾을 수 없습니다.(U0004)", response = NotFoundUserException.class),
            @ApiResponse(code = 400, message = "JWT 토큰이 비어있습니다.(J2001)", response = NotFoundJwtException.class)})
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