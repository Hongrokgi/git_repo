package com.example.BootJPABoard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /*
    *   Developer Custom Exception
    */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("handleCustomException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }
    /*
    *   HTTP 405 Exception
    */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException : {}",e.getMessage());
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }
    /*
    *   HTTP 500 Exception
    */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException : {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
    // 앞에서 다루었던 GlobalExceptionHandler
    // 기존에 작성한 RuntimeException에 대한 예외처리 메서드는 로직에서 제외되고
    // 개발자가 직접 정의한 CutomException과 HTTP 405, HTTP 500에 대한 Handler가 추가
    // ResponseEntity<ErrorResponse>
    // ResponseEntity<T>는 HTTP Request에 대한 응답 데이터를 포함하는 클래스로, <Type>에 해당하는
    // 데이터와 HTTP 상태코드를 함께 리턴할 수 있음
    // 우리는 예외가 발생했을 때, ErrorResponse 형식으로 예외 정보를 Reponse 로 내려주게 됩니다.
}
