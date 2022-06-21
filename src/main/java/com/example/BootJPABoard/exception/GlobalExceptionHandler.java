package com.example.BootJPABoard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(final RuntimeException e) {
        log.error("handleRuntimeException : {}", e.getMessage());
        return e.getMessage();
    }
    //Spring 은 예외처리를 위해  @ControllerAdvice 와 @ExceptionHandler 등의 기능을 지원
    //@ControllerAdivce 는 컨트롤 전역에서 발생하는 예외를 잡아 Throw 해주고
    //@ExceptionHandler 는 특정 클래스에서 발생하는 예외를 잡아 Throw 합니다
    //일반적으로 @ExceptionHandler는 @ControllerAdvice가 선언된 클래스에 포함된 메서드에 선언
    //이번 예제는 페이지에 대한 예외처리는 의미가 없으므로 @RestControllerAdvice선언 해당 어노테이션은
    //@ControllerAdivce에 @ResponseBody가 적용된 의미로 받아들이면된다.

    //@Slf4j 롬복에서 제공해주는 기능으로, 해당 어노테이션이 선언된 클래스에 자동으로 로그 객체를 생성
    //log.error(), log.debug()와 같이 로깅관련메서드 사용가능

    //@ExceptionHandler(RuntimeException.class)
    //속성으로 RuntimeException.class 지정 // BoardApiController에서 RuntimeException Throw..
    //GlobalExceptionHandler는 handleRuntimeException메서드 실행
}
