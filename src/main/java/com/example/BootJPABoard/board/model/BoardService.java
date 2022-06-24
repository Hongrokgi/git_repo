package com.example.BootJPABoard.board.model;

import com.example.BootJPABoard.board.dto.BoardRequestDto;
import com.example.BootJPABoard.board.dto.BoardResponseDto;
import com.example.BootJPABoard.board.dto.FileResponseDto;
import com.example.BootJPABoard.board.entity.Board;
import com.example.BootJPABoard.board.entity.BoardRepository;
import com.example.BootJPABoard.board.entity.File;
import com.example.BootJPABoard.board.entity.FileRepository;
import com.example.BootJPABoard.exception.CustomException;
import com.example.BootJPABoard.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//롬복에서 제공해주는 어노테이션으로 클래스내에 final로 선언된 모든 멤버에 대한 생성자를 만들어준다.
//예를 들면 public BoardService(BoardRepository boardRepository) {this.boardRepository=repository}
public class BoardService {
    private final BoardRepository boardRepository; //JPA Repository 보통 @Autowired로 빈 주입을 했었는데 스프링은 생성자로 빈을 주입하는 방식 선호
    private final FileRepository fileRepository;
    /*게시글 생성*/

    @Transactional
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /*게시글 수정*/
    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {
        Board entity=boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }
    /*게시글 삭제*/
    @Transactional
    public Long delete(final Long id) {
        Board entity=boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    /*게시글 리스트 조회*/
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    /*게시글 리스트 조회(삭제 여부 기준)*/
    public List<BoardResponseDto> findAllByDeleteYn(final char deleteYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id","createdDate");
        List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    /*게시글 상세정보 조회*/
    @Transactional
    public BoardResponseDto findById(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();
        return new BoardResponseDto(entity);
    }
}
