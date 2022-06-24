package com.example.BootJPABoard.board.dto;

import com.example.BootJPABoard.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardRequestDto {
    private String title;   //제목
    private String content; //내용
    private String writer;  //작성자
    private char deleteYn;  //삭제 여부

    private Long fileId;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .deleteYn('N')
                .build();
    }
}
//Entity 클래스는 테이블(Table) 또는 레코드(Record) 역할을 하는 데이터베이스 그 자체로 생각할 수 있고,
//절대로 요청(Request)이나 응답(Response)에 사용되어서는 안되기 때문에 반드시 Response, Request 클래스를 따로 생성(구분)해야함
//toEntity() BoardTest클래스의 save 메서드의 일부를 보면 Entity 객체를 인자로 전달해서 게시글을 생성, Entity클래스는
//절대로 요청(Request)에 사용되어서는 안되므로 이러한 이유로 BoardRequestDto로 전달받은 데이터(파라미터)를 기준으로 Entity 객체를 생성