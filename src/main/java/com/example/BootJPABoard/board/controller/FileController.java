package com.example.BootJPABoard.board.controller;

import com.example.BootJPABoard.board.dto.FileResponseDto;
import com.example.BootJPABoard.board.entity.File;
import com.example.BootJPABoard.board.model.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/files/{id}")
    public List<FileResponseDto> findAll(@PathVariable final Long id) {
        var boardId = id;
        return fileService.findByNo(boardId);
    }
}
