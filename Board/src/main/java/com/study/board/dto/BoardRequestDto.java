package com.study.board.dto;

import com.study.board.entity.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
	// 게시글의 생성과 수정을 처리할 요청(request) DTO 클래스
	// Entity 클래스와 요청 DTO 클래스는 유사한 구조
	// Entity 클래스는 Table 또는 record 역할을 하는 데이터베이스 그 자체로 생각할 수 있고, 절대로 요청(request)이나 응답(response)에 사용되어서는 안되기 때문에
	// 반드시 request, response 클래스를 따로 생성해주어야 한다.

	private String title; // 제목
	
	private String content; // 내용
	
	private String writer; // 작성자
	
	private char deleteYn; // 삭제 여부
	
	public Board toEntity() {
		
		return Board.builder().title(title).content(content).writer(writer).hits(0).deleteYn(deleteYn).build();
	}
	
}
