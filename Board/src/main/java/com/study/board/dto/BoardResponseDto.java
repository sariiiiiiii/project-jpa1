package com.study.board.dto;

import java.time.LocalDateTime;

import com.study.board.entity.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {
	
	// 요청(request) dto 클래스와 마찬가지로 응답(response)도 Entity클래스가 사용되어서는 안되기 때문에 분리를 해야 한다
	// 응답(response) 객체 생성은 필수적으로 Entity클래스를 필수로 한다
	
	private Long id; // PK
	
	private String title; // 제목
	
	private String content; // 내용
	
	private String writer; // 작성자
	
	private int hits; // 조회 수
	
	private char deleteYn; // 삭제 여부
	
	private LocalDateTime createdDate; // 생성일
	
	private LocalDateTime modifiedDate; // 수정일
	
	
	public BoardResponseDto(Board entity) {
		
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.writer = entity.getWriter();
		this.hits = entity.getHits();
		this.deleteYn = entity.getDeleteYn();
		this.createdDate = entity.getCreatedDate();
		this.modifiedDate = entity.getModifiedDate();
		
	}

}
