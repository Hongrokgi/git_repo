package com.example.BootJPABoard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    //Custom 예외 처리용 Exception 클래스 생성하기
    private final ErrorCode errorCode;
    //개발자가 ErrorCode에 직접 정의한 Custom 예외를 처리할 Exception 클래스입니다.
    //ErrorResponse 와 마찬가지로 ErrorCode 를 통한 객체 생성만을 허용
    //Unchecked Exception인 RuntimeException을 상속받는 것을 꼭 기억해야함
}
