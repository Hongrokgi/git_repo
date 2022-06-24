package com.example.BootJPABoard.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File> {

    List<File> findAllByBoardId(Long boardId);
}
