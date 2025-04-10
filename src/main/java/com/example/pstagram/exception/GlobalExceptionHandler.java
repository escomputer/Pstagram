package com.example.pstagram.exception;

import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.dto.common.ApiResponse;
import com.example.pstagram.exception.post.PostNotFoundException;
import com.example.pstagram.exception.post.UnauthorizedPostAccessException;
import com.example.pstagram.exception.user.AlreadyDeletedUserException;
import com.example.pstagram.exception.user.EmailAlreadyExistsException;
import com.example.pstagram.exception.user.EmailNotFoundException;
import com.example.pstagram.exception.user.InvalidPasswordException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 프로젝트 전역에서 발생하는 예외를 처리하는 핸들러
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final MessageUtil messageUtil;

	/**
	 * 이메일 중복 예외 처리
	 */
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<Void>> handleEmailExists(EmailAlreadyExistsException ex) {
		String message = messageUtil.getMessage("user.email.exists");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(message, null));
	}

	/**
	 * 이메일 없음 예외 처리
	 */
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleEmailNotFound(EmailNotFoundException ex) {
		String message = messageUtil.getMessage("user.email.not-found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(message, null));
	}

	/**
	 * 비밀번호 불일치 예외 처리
	 */
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalidPassword(InvalidPasswordException ex) {
		String message = messageUtil.getMessage("user.password.invalid");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(message, null));
	}

	/**
	 * 회원탈퇴 예외 처리
	 */
	@ExceptionHandler(AlreadyDeletedUserException.class)
	public ResponseEntity<ApiResponse<Void>> handleAlreadyDeletedUserException(AlreadyDeletedUserException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(new ApiResponse<>(ex.getMessage(), null));
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostNotFound(PostNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(e.getMessage(), null));
	}

	@ExceptionHandler(UnauthorizedPostAccessException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostUnauthorized(UnauthorizedPostAccessException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			.body(new ApiResponse<>(e.getMessage(), null));
	}

}
