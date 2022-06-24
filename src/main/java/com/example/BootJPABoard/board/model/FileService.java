package com.example.BootJPABoard.board.model;

import com.example.BootJPABoard.board.dto.FileRequestDto;
import com.example.BootJPABoard.board.dto.FileResponseDto;
import com.example.BootJPABoard.board.entity.File;
import com.example.BootJPABoard.board.entity.FileRepository;
import com.example.BootJPABoard.exception.CustomException;
import com.example.BootJPABoard.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public Long save(final FileRequestDto fileInfo) {
        File entity = fileRepository.save(fileInfo.toEntity());
        return entity.getId();
    }

    public List<FileResponseDto> findByNo(final Long boardId) {
        List<File> list = fileRepository.findAllByBoardId(boardId);
        //System.out.println(list);
        return list.stream().map(FileResponseDto::new).collect(Collectors.toList());
    }


}
