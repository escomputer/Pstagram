package com.example.pstagram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.dto.common.ApiResponse;
import com.example.pstagram.exception.friend.DuplicateFriendRequestException;
import com.example.pstagram.exception.friend.FriendNotFoundException;
import com.example.pstagram.exception.friend.FriendRequestNotFoundException;
import com.example.pstagram.exception.friend.SelfRequestException;
import com.example.pstagram.exception.friend.UnauthorizedException;
import com.example.pstagram.exception.friend.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private MessageUtil messageUtil;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleUserNotFound(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	@ExceptionHandler(DuplicateFriendRequestException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateRequest(DuplicateFriendRequestException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(
				new ApiResponse<>(HttpStatus.CONFLICT.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

	@ExceptionHandler(FriendRequestNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleFriendRequestNotFound(FriendRequestNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	@ExceptionHandler(FriendNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleFriendNotFound(FriendNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(
				new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

	@ExceptionHandler(SelfRequestException.class)
	public ResponseEntity<ApiResponse<Void>> handleSelfRequest(SelfRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(
				new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

}
