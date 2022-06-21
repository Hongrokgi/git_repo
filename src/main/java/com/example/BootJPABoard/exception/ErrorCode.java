package com.example.BootJPABoard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*
    * 400 BAD_REQUEST: 잘못된 요청
    */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    /*
    * 404 NOT_FOUND: 리소스를 찾을 수 없음
    */
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),
    /*
    * 405 METHOD_NOT_ALLOWED: 허용되지 않은 RequestMethod 호출
    */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),
    /*
    * 500 INTERNAL_SERVER_ERROR: 내부서버오류
    */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    ;
    private final HttpStatus status;
    private final String message;
    //POST_NOT_FOUND와 같이, 개발자가 직접 정의한 Custom예외를 이곳에서 쉽게 관리할 수 있음
    //예를 들어 댓글 기능에서 댓글 Entity를 찾을 수 없는 경우에는 다음과 같이 예외 추가
    //COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글 정보를 찾을 수 없습니다")
    //status HTTP 상태코드를 상수로 선언해 둔 HttpStatus 타입의 멤버로, 예외에 대한 상태코드(status)와 이름(error)을 처리하는데 사용
    //message 예외에 대한 응답메시지(message)를 처리하는데 사용되는 멤버
}
