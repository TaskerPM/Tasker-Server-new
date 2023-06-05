package com.example.tasker.global.jwt.exception;

public class ExpireAccessException extends JwtException{
    public ExpireAccessException(){
        super(JwtExceptionList.EXPIRE_ACCESS_TOKEN.getCODE(),
                JwtExceptionList.EXPIRE_ACCESS_TOKEN.getHttpStatus(),
                JwtExceptionList.EXPIRE_ACCESS_TOKEN.getMESSAGE()
        );
    }
}
