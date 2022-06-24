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
