package com.example.BootJPABoard.board.dto;

import com.example.BootJPABoard.board.entity.Board;
import lombok.Getter;

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

    private Long fileId;

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getWriter() {return writer;}
    public int getHits() {return hits;}
    public char getDeleteYn() {return deleteYn;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public LocalDateTime getModifiedDate() {return modifiedDate;}

    public Long getFileId() {return fileId;}

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

