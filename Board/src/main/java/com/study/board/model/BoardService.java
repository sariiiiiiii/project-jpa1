package com.study.board.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 롬복에서 제공해주는 annotation으로 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어줌
public class BoardService {
	
	// 보통 @Autowired로 빈(Bean)을 주입받는 식으로 사용했는데 스프링에서는 생성자로 빈을 주입하는 방식을 권장한다고 함
	
	private final BoardRepository boardRepository; // JPA interface
	
	
	/*
	 * 게시글 생성
	 */
	@Transactional // JPA를 사용한다면 Service 클래스에서 필수적으로 사용해야 하는 어노테이션
	// 메소드 레벨에 선언하게 되며, 메소드의 실행, 종료, 예외를 기준으로 각각 실행(Begin), 종료(commit), 예외(rollback)를 자동으로 처리해줌
	public Long save(final BoardRequestDto params) {
		
		Board entity = boardRepository.save(params.toEntity());
		// Entity클래스는 절대로 요청(request)에 사용되어서는 안되기 때문에 BoardRequestDto.toEntity() 메소드를 이용해서 boardRepository.save() 메소드 실행
		// save() 메소드가 실행된 후 entity객체에는 생성된 게시글 정보가 담기며, 
		
		return entity.getId(); // 메소드가 종료되면 생성된 게시글의 id(PK)가 리턴된다
	}
	
	
	/*
	 * 게시글 리스트 조회
	 */
	public List<BoardResponseDto> findAll(){
		
		Sort sort = Sort.by(Direction.DESC, "id", "createdData"); // sort 객체는 ORDER BY ID DESC, CREATED_DATE DESC를 의미
		List<Board> list = boardRepository.findAll(sort);
		
		return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
		// 쉽게 얘기하자면 list 변수에는 게시글 Entity가 담겨 있고, 각각의 Entity를 BoardResponseDto 타입으로 변경(생성)해서 리턴
		
	}
	
	
	/*
	 * 게시글 수정
	 */
	// https://congsong.tistory.com/55?category=749196
	@Transactional
	public Long update(Long id, BoardRequestDto params) {
		
		Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		
		entity.update(params.getTitle(), params.getContent(), params.getWriter());
		// entity 클래스에 update 메소드는 update 쿼리를 실행하는 로직이 없다
		// 하지만, 메소드의 실행이 종료(commit)되면 update 쿼리가 자동으로 실행된다
		// JPA에는 영속성 컨텍스트라는 개념이 있다
		
		return id;
	}
	

}
