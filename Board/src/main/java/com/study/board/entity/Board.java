package com.study.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 해당클래스의 멤버변수의 모든 getter 메소드를 생성해주는 lombok기능
// @Setter가 없는 이유는 테이블 그 자체가 컬럼이기 때문에 setter를 쓰게 될경우 어느 시점에서 값이 변하는지 모르기 때문에 Entity클래스에서는 절대로 set메소드가 존재하면 안됨
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본생성자를 생성해준다 동일한 패키지에서만 객체를 생성해준다 (accessLevel.PROTECTED)
@Entity 
// 해당 클래스가 테이블과 매핑되는 JPA의 Entity클래스임을 의미
// 기본적으로 클래스명을 테이블명으로 매핑 user_role = userRole이라고 하고 클래스명이 다를경우 @Table(name = "user_role")
public class Board {

	@Id // PK임을 선언 Entity에서는 PK를 Long으로 선언 데이터양이 많지 않을때는 Integer도 사용해도 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성전략 어노테이션 INDENTITY = ID값을 NULL로 하면 DB에서 알아서 INCREMENT 해준다
	private Long id; // (PK) 
	
	private String title; // 제목
	
	private String content; // 내용
	
	private String writer; // 작성자
	
	private int hits; // 조회 수
	
	private char deleteYn; // 삭제 여부
	
	private LocalDateTime createdDate = LocalDateTime.now();// 생성일
	
	private LocalDateTime modifiedDate; // 수정일
	
	@Builder // 생성자를 대신해주는 어노테이션
	public Board(String title, String content, String writer, int hits, char deleteYn) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hits = hits;
		this.deleteYn = deleteYn;
	}
	
	// 게시글 수정
	public void update(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.modifiedDate = LocalDateTime.now();
	}
	
}
