package com.study.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자 생성
public enum ErrorCode {
	
	
	/*
	 * 400 BAD_REQUEST : 잘못된 요청
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
	
	
	
	/*
	 * 404 NOT_FOUND : 리소스를 찾을 수 없음
	 */
	POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다"),
	
	
	
	/*
	 * 405 METHOD_NOT_ALLOWED : 허용되지 않은 RequestMethod 호출
	 */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 메소드입니다"),
	
	
	
	/*
	 * 500 INTERNAL_SERVER_ERROR : 내부 서버 오류
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다"),
	
	
	;
	
	
	private final HttpStatus status; // Http 상태 코드를 상수로 선언해둔 HttpStatus 타입의 멤버로, 예외에 대한 상태 코드(Status)와 이름(error)을 처리하는데 사용
	private final String message; // 예외에 대한 응답 메세지(message)를 처리하는데 사용
	

}
