package com.example.BootJPABoard.board.dto;

import com.example.BootJPABoard.board.entity.File;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class FileResponseDto {
    private Long id;
    private String origname;
    private String savename;
    private int size;
    private char deleteYn;
    private LocalDateTime insertTime;
    private Long boardId;

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getOrigname() {return origname;}
    public void setOrigname(String origname) {this.origname=origname;}

    public String getSavename() {return savename;}
    public void setSavename(String savename) {this.savename=savename;}

    public int getSize() {return size;}
    public void setSize(int size) {this.size=size;}

    public char getDeleteYn() {return deleteYn;}
    public void setDeleteYn(char deleteYn) {this.deleteYn=deleteYn;}

    public LocalDateTime getInsertTime() {return insertTime;}
    public void setInsertTime(LocalDateTime insertTime) {this.insertTime=insertTime;}

    public Long getBoardId() {return boardId;}
    public void setBoardId(Long boardId) {this.boardId=boardId;}

    public FileResponseDto(File entity) {
        this.id=entity.getId();
        this.origname=entity.getOrigname();
        this.savename=entity.getSavename();
        this.size=entity.getSize();
        this.deleteYn=entity.getDeleteYn();
        this.insertTime=entity.getInsertTime();
        this.boardId=entity.getBoardId();
    }
}
