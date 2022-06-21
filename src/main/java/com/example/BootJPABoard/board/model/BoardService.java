package com.example.BootJPABoard.board.model;

import com.example.BootJPABoard.board.dto.BoardRequestDto;
import com.example.BootJPABoard.board.dto.BoardResponseDto;
import com.example.BootJPABoard.board.entity.Board;
import com.example.BootJPABoard.board.entity.BoardRepository;
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

    /*게시글 생성*/
    //Transactional JPA를 사용한다면 서비스(Service) 클래스에서 필수적으로 사용되어야 하는 어노테이션, 일반적으로 메서드 레벨에서 선언
    //메서드의 실행,종료,예외를 기준으로 각각 실행(begin),종료(Commit)예외(Rollback)를 자동으로처리
    @Transactional
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    //save가 실행되면 새로운 게시글 형성 Entity는 절대로 요청에 사용되어서는 안되므로, BoardRequestDto의 toEntity메서드를 사용해서
    //boardRepository의 save메서드를 실행
    //save()메서드가 실행된 후 entity객체에는 생성된 게시글 정보가 담기며, 메서드가 종료되면 생성된 게시글의 id(PK)리턴
    /*게시글 리스트 조회*/
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }
    //findAll(): sort를 객체로 전달해서 전체 게시글을 조회
    //sort객체는 ORDER BY id DESC, created_date DESC을 의미합니다. 마지막 리턴문을 보면 Java의 Stream API를 이용
    //list변수에는 게시글 Entity가 담겨있고 각각의 Entity를 BoardResponseDto 타입으로 변경(생성)해서 리턴해준다고 보면된다.
    //만약 Stream을 사용하지 않는다면
    // 35 36 동일, List<BoardResponseDto> boardList = new ArrayList<>();
    // for(Board entity : list) {
    //      boardList.add(new BoardResponseDto(entity));
    //  }
    // return boardList;

    /*게시글 수정*/
    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {
        Board entity=boardRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }
    //update()
    //해당 메서드에는 update를 실행하는 로직이 없다. 하지만 해당메서드의 실행이 종료(commit)이 되면 update쿼리가 자동실행
    //JPA에는 영속성 컨텍스트라는 개념이 있는데, 영속성 컨텍스트란 Entity를 영구히 저장하는 환경이라는 뜻으로
    //애플리케이션과 데이터베이스사이에서 객체를 보관하는 가상의 영역
    //JPA의 Entity Manager라는 녀석은 Entity가 생성되거나 Entity를 조회하는 시점에 영속성 컨텍스트에 Entity를 보관 및 관리
    //Entity를 조회하면 해당 Entity는 영속성 컨텍스트에 보관(포함)될 테고, 영속성 컨텍스트에 포함된 Entity 객체의 값이 변경되면
    //트랜잭션이 종료(commit)되는 시점에 update쿼리를 실행. 이렇게 자동으로 쿼리가 실행되는 개념을 더티체킹(Dirty Checking)
    //"영속성 컨텍스트에 의해 더티체킹이 가능해진다고 이해하면됨

    //findById(id).orElseThrow(() -> ..)
    //JPA Repository의 findById()는 Java8에서 도입된 Optional 클래스를 리턴
    //Optional -> 반복적인 Null처리를 피하기 위해 사용되는 클래스
    //orElseThrow()는 Optional 클래스에 포함된 메소드로 Entity 조회와 예외처리를 단 한 줄로 처리할 수 있는 것
    //만약에 풀어서 쓴다고 하면
    //Board entity =  boardRepository.findById(id).orElse(null);
    // if(entity == null) {
    // throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    //}
    // entity.update(params.getTitle(), params.getContent(), params.getWriter());
    //return id; 라는 코드가 될텐데 가독성 측면에서는 좋지만, 코드라인을 기준으로 보면 그렇지 않을 수 있음
}
