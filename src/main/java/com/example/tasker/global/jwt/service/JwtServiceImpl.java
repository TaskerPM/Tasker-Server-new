package com.example.tasker.global.jwt.service;

import com.example.tasker.domain.user.repository.UserRefreshTokenRepository;
import com.example.tasker.domain.user.repository.UserRepository;
import com.example.tasker.global.jwt.exception.ExpireRefreshException;
import com.example.tasker.global.jwt.exception.NotFoundJwtException;
import com.example.tasker.global.jwt.secret.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserRepository userRepository;
    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 2 * 1000L;   // 2시간
    private final long REFRESH_TOKEN_VALID_TIME  = 60 * 60 * 24 * 7 * 1000L;   // 1 달
    @Value("${spring.jwt.access-key}")
    public String JWT_ACCESS_SECRET_KEY;
    @Value("${spring.jwt.refresh-key}")
    public String JWT_REFRESH_SECRET_KEY;

    private final String BLACKLIST_KEY = "blacklist:tokens";
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public String createAccessJwt(String numId) {
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setHeaderParam("type", "jwt")
                .claim("numId", numId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SecretKey.JWT_ACCESS_SECRET_KEY)
                .compact();
        return accessToken;
    }

    public void addToBlacklist(String token) {
        redisTemplate.opsForValue().set(token, true, ACCESS_TOKEN_VALID_TIME, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String token) {
        Object result = redisTemplate.opsForValue().get(token);
        return result != null && (boolean) result;
    }


    @Override
    public String createRefreshJwt(String numId) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setHeaderParam("type", "jwt")
                .claim("numId", numId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SecretKey.JWT_REFRESH_SECRET_KEY)
                .compact();
    }

    private boolean validateRefreshToken(String refreshToken) {
        try{
            Jwts.parser()
                    .setSigningKey(JWT_REFRESH_SECRET_KEY)
                    .parseClaimsJws(refreshToken); // 파싱 및 검증, 실패 시 에러
            return true;
        }catch (Exception e3){
            return false;
        }
    }

    private boolean validateAccessToken(String accessToken) {
        try{
            Jwts.parser()
                    .setSigningKey(JWT_ACCESS_SECRET_KEY)
                    .parseClaimsJws(accessToken); // 파싱 및 검증, 실패 시 에러
            return true;
        }catch (Exception e3){
            return false;
        }
    }

    public String resolveAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String accessToken = request.getHeader("X-ACCESS-TOKEN");
        if (accessToken == null || accessToken.isBlank()) {
            throw new NotFoundJwtException();
        }
        return accessToken;
    }
    @Override
    public String resolveRefreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String refreshToken = request.getHeader("X-REFRESH-TOKEN");
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new NotFoundJwtException();
        }
        return refreshToken;
    }

    @Override
    // 자동 로그인 시 - 리프레시 토큰 이용 - 유저정보 반환
    public String loginByRefresh() {
        String refreshToken = this.resolveRefreshToken();
        // 엑세스 토큰 재발급 - 리프레쉬 토큰 만료 시 에러
        // 리프레시 토큰 검증
        if (!this.validateRefreshToken(refreshToken)) {
            throw new ExpireRefreshException();
        }
        return this.getNumId(refreshToken);
    }

    @Override
    // api 호출 시 - 엑세스 토큰 이용 - jwt 반환
    public String callApi() {
        String accessToken = this.resolveAccessToken();
        if (!this.validateAccessToken(accessToken)) {
            accessToken = this.createAccessJwt(this.getNumIdAccess(accessToken));
        }
        return accessToken;
    }

    // 리프레시 토큰으로 numId 반환
    private String getNumId(String refreshToken) {
        if (!this.validateRefreshToken(refreshToken)) {
            throw new ExpireRefreshException();
        }
        return Jwts.parser()
                .setSigningKey(JWT_REFRESH_SECRET_KEY)
                .parseClaimsJws(refreshToken).getBody().get("numId",String.class);
    }

    // 엑세스  토큰으로 numId 반환
    private String getNumIdAccess(String accessToken) {
        return Jwts.parser()
                .setSigningKey(JWT_ACCESS_SECRET_KEY)
                .parseClaimsJws(accessToken).getBody().get("numId",String.class);
    }



}