package com.example.BootJPABoard.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity // 해당 클래스가 테이블과 매핑되는 JPA의 엔티티(Entity)클래스임을 의미. 기본적으로 클래스명(Camel Case)을 테이블명(Snake Case)으로 매핑
//예를 들어 user_role이라는 테이블은 UserRole이라는 이름의 클래스로 네이밍하면됨. 혹시 클래스와 테이블명이 다를 수 밖에 없는 경우에는
//@Table(name="user_role")과 같이 name속성을 이용하여 처리해주면됨
@NoArgsConstructor(access = AccessLevel.PROTECTED) //해당 클래스의 기본생성자를 생성 access속성을 이용해. 동일패키지 내의 클래스에서만 접근가능
public class Board {
    @Id // 해당 멤버가 엔티티의 PK임을 의미 보통 MySql DB는 bigint타입으로 엔티티에서는 Long타입으로 선언, 데이터양이 많지 않은 경우에는 Integer로 사용해도 무방
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성전략 MySql은 자동증가(Auto_Increment)를 지원하는 DB, PK 자동증가를 지원하는 DB는 해당 어노테이션 사용
    private Long id;                                    //오라클과 같이 시퀀스를 사용하는 DB는 GenerationType.SEQUENCE를 이용, GenertaionType.Auto는 db에서 제공하는 PK생성전략을 가진다.
    private String title;
    private String content;
    private String writer;
    private int hits; // 조회 수
    private char deleteYn;
    private LocalDateTime createdDate = LocalDateTime.now(); // 생성일
    private LocalDateTime modifiedDate; // 수정일

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getWriter() {return writer;}
    public int getHits() {return hits;}
    public char getDeleteYn() {return deleteYn;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public LocalDateTime getModifiedDate() {return modifiedDate;}

    @Builder //Lombok이 제공해주는 기능으로, 생성자 대신 이용하는 패턴
    public Board(String title, String content, String writer, int hits, char deleteYn) {
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.hits=hits;
        this.deleteYn=deleteYn;
    }
    public void update(String title, String content, String writer) {
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.modifiedDate= LocalDateTime.now();
    }
    /*조회 수 증가*/
    public void increaseHits() {
        this.hits++;
    }
    /*게시글 삭제*/
}
