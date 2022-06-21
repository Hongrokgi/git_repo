package com.example.BootJPABoard.board.controller;

import com.example.BootJPABoard.board.dto.BoardRequestDto;
import com.example.BootJPABoard.board.dto.BoardResponseDto;
import com.example.BootJPABoard.board.model.BoardService;
import com.example.BootJPABoard.exception.CustomException;
import com.example.BootJPABoard.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    /*
    *   게시글 생성
    */
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params) {
        return boardService.save(params);
    }
    /*
    *  게시글 수정
    */
    @PatchMapping("/boards/{id}")
    public Long update(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }
    /* 게시글 삭제 */
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }
    /*
    *  게시글 리스트 조회
    */
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll() {
        return boardService.findAll();
    }

    /* 게시글 상세 정보 조회 */
    @GetMapping("/boards/{id}")
    public BoardResponseDto findById(@PathVariable final Long id) {
        return boardService.findById(id);
    }
}
