package com.example.BootJPABoard.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origname;
    private String savename;
    private int size;
    private char deleteYn;
    private LocalDateTime insertTime = LocalDateTime.now();

    private Long boardId;

    public Long getId() {return id;}
    public String getOrigname() {return origname;}
    public String getSavename() {return savename;}
    public int getSize() {return size;}
    public char getDeleteYn() {return deleteYn;}
    public LocalDateTime getInsertTime() {return insertTime;}
    public Long getBoardId() {return boardId;}

    @Builder
    public File(String origname, String savename, int size, char deleteYn, Long boardId) {
        this.origname=origname;
        this.savename=savename;
        this.size=size;
        this.deleteYn=deleteYn;
        this.boardId=boardId;
    }
}
