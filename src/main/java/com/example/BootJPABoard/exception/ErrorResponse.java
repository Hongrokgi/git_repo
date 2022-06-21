package com.example.BootJPABoard.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status  = errorCode.getStatus().value();
        this.error   = errorCode.getStatus().name();
        this.code    = errorCode.name();
        this.message = errorCode.getMessage();
    }
    //404 Error Response와 유사한 형태를 가진, 예외 응답을 처리할 Response클래스입니다.
    //해당 클래스는 ErrorCode를 통한 객체 생성만을 허용합니다.
}
