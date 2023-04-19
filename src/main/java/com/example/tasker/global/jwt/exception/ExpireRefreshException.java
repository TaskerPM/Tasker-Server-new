package com.example.tasker.global.jwt.exception;

public class ExpireRefreshException extends JwtException{
    public ExpireRefreshException(){
        super(JwtExceptionList.EXPIRE_REFRESH_TOKEN.getCODE(),
                JwtExceptionList.EXPIRE_REFRESH_TOKEN.getHttpStatus(),
                JwtExceptionList.EXPIRE_REFRESH_TOKEN.getMESSAGE()
        );
    }
}
