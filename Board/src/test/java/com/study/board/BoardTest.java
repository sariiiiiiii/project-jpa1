package com.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;


@SpringBootTest // 기존의 스프링 레가시 프로젝트와 달리 스프링 부트는 어노테이션만 작성하면 테스트가 가능
public class BoardTest {
	
	@Autowired
	private BoardRepository boardRepository; // 스프링 컨테이너에 등록된 boardRepository 객체를 주입받음
	
	@Test
	void save() { 
	// 저장에 이용되는 params는 Board 클래스의 @Build 패턴(생성자를 대신해주는)을 통해 생성된 객체이다
	// 생성자와는 달리 빌더패턴을 이용하면 어떤 멤버의 어떤 값을 세팅하는지 직관적으로 확인이 가능
		
		// 1. 게시글 파라미터 생성
		Board params = Board.builder().title("1번 게시글 제목").content("1번 게시글 내용").writer("사리").hits(0).deleteYn('N').build();
		
		// 2. 게시글 저장
        boardRepository.save(params);
		
		// finById의 리턴타입은 Optional<T>라는 클래스인데 반복적인 NULL처리를 피하기 위한 도입된 클래스
		// Optional 인스턴스는 모든 타입의 참조 변수를 참조할 수 있다
		// Optional 객체를 사용하면 NullPointException 예외를 제공되는 메소드로 간단히 회피할 수 있다
		// 즉, 복잡한 조건문 없이도 Null값으로 인해 발생하는 예외를 처리할 수 있게 된다
		// 3. 1번 게시글 정보 조회
		Board entity = boardRepository.findById((long)1).get();
		assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
		assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
		assertThat(entity.getWriter()).isEqualTo("사리");
		
	}
	
	@Test
	void findAll() {
		
		// 1. 전체 게시글 수 조회
		long boardCount = boardRepository.count();
		
		// 2. 전체 게시글 조회
		List<Board> boardList = boardRepository.findAll();
		
	}
	
	
	@Test
	void delete() {
		
		// 1. 게시글 조회
		Board entity = boardRepository.findById((long)1).get();
		
		// 2. 게시글 삭제
		boardRepository.delete(entity);;
		
	}
	
}
