package com.example.BootJPABoard.board;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.BootJPABoard.board.entity.Board;
import com.example.BootJPABoard.board.entity.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // 기존의 Spring Legacy Project와 달리 스프링 부트는 해당 어노테이션만 선언하면 테스팅 가능
public class BoardTests {
    @Autowired
    BoardRepository boardRepository; //스프링 컨테이너에 등록된 BoardRepository 객체(Bean)를 주입받습니다.

    @Test
    void save() { // 게시글 저장에 이용되는 params는 앞에서 이야기한 빌더(builder)패턴을 통해 생성된 객체입니다. 생성자와는 달리
                  // 빌더 패턴을 이용하면 어떤 멤버에 어떤 값을 세팅하는지 직관적으로 확인이 가능.
                  // ex) 생성자를 통해 객체생성 Board entity = new Board("1번 게시글 제목","1번 게시글 내용","도뎡이",'0','N')
                  // 생성자의 경우 순서에 영향을 받지만, 빌더패턴은 순서에 관계없이 객체를 생성할 수 있음. 가독성 측면에서 뛰어남
        //1. 게시글 파라미터 생성
//        Board params = Board.builder()
//                .title("1번 게시글 제목")
//                .content("1번 게시글 내용")
//                .writer("도뎡이")
//                .hits(0)
//                .deleteYn('N')
//                .build();
//        //2. 게시글 저장
//        boardRepository.save(params);
//        //3. 1번 게시글 정보조회
//        Board entity = boardRepository.findById((long)1).get();
//        System.out.println(entity.getTitle());
//        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
//        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
//        assertThat(entity.getWriter()).isEqualTo("도뎡이");
    }
    @Test
    void findAll() {
        //1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();
        //2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll(); //findAll : boardRepository의 count()와 findAll()메서드를 이용해서 전체 게시글
    }                                                   //수와 전체 게시글 리스트를 조회하는 쿼리를 실행
    @Test
    void delete() {
        //1. 게시글 조회
        Board entity = boardRepository.findById((long)1).get();
        //2. 게시글 삭제
        boardRepository.delete(entity);  //findById()를 통해 엔티티를 조회. findById()도 마찬가지로 JPA에서 기본으로 제공해주는 메서드로
    }                                    //엔티티의 PK를 기준으로 데이터를 조회한 다음 delete()메서드를 실행해서 1번 게시글 삭제
}                                        //참고로 findBYId의 리턴타입은 Optional<T>이라는 클래스인데, Optional은 반복적인 NUll처리를 피하기
                                         //위해 자바8에서 최초 도입된 클래스