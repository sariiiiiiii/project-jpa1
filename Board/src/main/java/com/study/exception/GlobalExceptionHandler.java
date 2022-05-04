package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
// 스프링에서 예외처리를 위해 @ControllerAdvice와 @ExceptionHandler등의 기능을 지원해주고 있다
// @ControllerAdvice는 컨트롤러 전역에서 발생할 수 있는 예외를 throw 처리 해주고 @ExceptionHandler는 특정 클래스에서 발생하는 에외를 잡아 throw 해준다
// @ControllerAdvice > @ExceptionHandler
@Slf4j
// 롬복에서 제공해주는 해당 어노테이션이 선언된 클래스에 자동으로 로그 객체를 생성할 수 있는 기능
// log.error(), log.debug(), log.info .....
public class GlobalExceptionHandler {
	
	
	// ResponseEntity는 HttpRequest에 대한 응답 데이터를 포함하는 클래스로, <Type>에 해당하는 데이터와 HTTP 상태 코드를 함께 리턴할 수 있다
	// 우리는 예외가 발생했을 때, ErrorResponse 형식으로 예외 정보를 Response로 내려주게 된다.
	
	
	/*
	 * Developer Custom Exception
	 */
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e){
		
		log.error("handlerCustomException : {}" + e.getErrorCode());
		
		return ResponseEntity.status(e.getErrorCode().getStatus().value()).body(new ErrorResponse(e.getErrorCode()));
		
		
	}
	
	/*
     * HTTP 405 Exception
     */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handlerHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e){
		
		log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
		
		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value()).body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
		
	}
	
	
	/*
     * HTTP 405 Exception
     */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(final Exception e){
		
		log.error("handleException : {}", e.getMessage());
		
		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value()).body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
		
	}

}
