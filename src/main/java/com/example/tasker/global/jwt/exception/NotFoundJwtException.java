package com.example.tasker.global.jwt.exception;


public class NotFoundJwtException extends JwtException{

    public NotFoundJwtException() {
        super(JwtExceptionList.NOT_FOUND_JWT.getCODE(),
                JwtExceptionList.NOT_FOUND_JWT.getHttpStatus(),
                JwtExceptionList.NOT_FOUND_JWT.getMESSAGE()
        );
    }

}
