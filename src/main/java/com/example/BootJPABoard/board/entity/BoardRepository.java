package com.example.BootJPABoard.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //Repository 는 MyBatis의 SQL Mapper와 유사한 퍼시스턴스 영역에 사용되는 인터페이스로 Mybatis의 Mapper를 JPA에서는
    //Repository로 부른다고 이해하면 된다.
    //참고로 Entity클래스와 Repository 인터페이스는 꼭 같은 패키지에 위치해야합니다.
    //Repository 인터페이스에서 JpaRepository 인터페이스를 상속받을 때, 엔티티 클래스의 타입(Board)과 PK에 해당하는 데이터타입(Long)을 선언하면
    //해당 엔티티클래스와 매핑되는 테이블인 board 테이블의 CRUD 기능을 사용할 수 있습니다.
}
