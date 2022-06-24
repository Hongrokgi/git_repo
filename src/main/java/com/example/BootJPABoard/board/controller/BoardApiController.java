package com.example.BootJPABoard.board.controller;

import com.example.BootJPABoard.board.dto.BoardRequestDto;
import com.example.BootJPABoard.board.dto.BoardResponseDto;
import com.example.BootJPABoard.board.dto.FileRequestDto;
import com.example.BootJPABoard.board.dto.FileResponseDto;
import com.example.BootJPABoard.board.model.BoardService;

import com.example.BootJPABoard.board.model.FileService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private BoardRequestDto boardRequestDto;

    private final FileService fileService;

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    /* 오늘 날짜 */
    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    /* 업로드 경로*/
    private final String uploadPath = Paths.get("C:","test","upload",today).toString();

    /*
    *   게시글 생성
    */
    @PostMapping(value = "/boards")
    public Long save(@RequestPart(value = "uploadFile", required = false) MultipartFile[] files
                    ,@RequestParam(value = "title") String title
                    ,@RequestParam(value = "writer") String writer
                    ,@RequestParam(value = "content") String content) {
        File dir = new File(uploadPath);
        if(dir.exists() == false) {
            dir.mkdirs();
        }
        //우선 게시글 생성
        BoardRequestDto params = new BoardRequestDto();
        params.setTitle(title);
        params.setWriter(writer);
        params.setContent(content);
        var boardId = boardService.save(params);
        if(files != null) {
            /* 1. 파일업로드먼저 진행해야지 */
            for(MultipartFile multipartFile : files) {
                try{
                    /*파일 확장자*/
                    final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                    /*서버에 저장할 파일명(랜덤 문자열 + 확장자)*/
                    final String savename =  getRandomString() + "." + extension;

                    File target = new File(uploadPath, savename);
                    multipartFile.transferTo(target);

                    final int size = (int)multipartFile.getSize();
                    final String origname = multipartFile.getOriginalFilename();
                    char deleteYn = 'N';
                    FileRequestDto fileInfo = new FileRequestDto();
                    fileInfo.setOrigname(origname);
                    fileInfo.setSavename(savename);
                    fileInfo.setSize(size);
                    fileInfo.setDeleteYn(deleteYn);
                    fileInfo.setBoardId(boardId);
                    var successYn = fileService.save(fileInfo);
                    if(successYn > 0) {
                        System.out.println("성공");
                    }
                }catch (Exception e) {
                }
            }
        }else {
            System.out.println("파일없습니당");
        }
        return null;
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
    public List<BoardResponseDto> findAll(@RequestParam final char deleteYn) {
        return boardService.findAllByDeleteYn(deleteYn);
    }

    /* 게시글 상세 정보 조회 */

    @GetMapping("/boardinfo")
    public String findById(@RequestParam final Long id, Model model) {
        BoardResponseDto board = boardService.findById(id);
        var boardId = id;
        List<FileResponseDto> list =fileService.findByNo(boardId);
        System.out.println(list);
        model.addAttribute("board",board);
        if(!list.isEmpty()) {
            model.addAttribute("list",list);
        }


        return "board/view";
    }
}
