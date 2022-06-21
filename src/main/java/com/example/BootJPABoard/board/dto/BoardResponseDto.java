package com.example.BootJPABoard.board.dto;

import com.example.BootJPABoard.board.entity.Board;

import java.time.LocalDateTime;

public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hits;
    private char deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getWriter() {return writer;}
    public int getHits() {return hits;}
    public char getDeleteYn() {return deleteYn;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public LocalDateTime getModifiedDate() {return modifiedDate;}

    public BoardResponseDto(Board entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.writer= entity.getWriter();
        this.hits=entity.getHits();
        this.deleteYn= entity.getDeleteYn();
        this.createdDate=entity.getCreatedDate();
        this.modifiedDate=entity.getModifiedDate();
    }
}
// 응답도 마찬가지로 Entity클래스가 사용되서는 안되기 때문에 클래스를 분리
// 응답 객체 생성은 필수적으로 Entity클래스를 필요로 하며
// 커밋
