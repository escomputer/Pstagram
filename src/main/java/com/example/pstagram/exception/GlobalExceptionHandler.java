package com.example.pstagram.exception;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.dto.common.ApiResponse;
import com.example.pstagram.exception.comment.CommentListEmptyException;
import com.example.pstagram.exception.comment.CommentNotFoundException;
import com.example.pstagram.exception.comment.EmptyCommentContentException;
import com.example.pstagram.exception.comment.EmptyUpdateContentException;
import com.example.pstagram.exception.comment.UnauthorizedCommentAccessException;
import com.example.pstagram.exception.friend.DuplicateFriendRequestException;
import com.example.pstagram.exception.friend.FriendNotFoundException;
import com.example.pstagram.exception.friend.FriendRequestNotFoundException;
import com.example.pstagram.exception.friend.SelfRequestException;
import com.example.pstagram.exception.friend.UnauthorizedException;
import com.example.pstagram.exception.friend.UserNotFoundException;
import com.example.pstagram.exception.post.PostNotFoundException;
import com.example.pstagram.exception.user.AlreadyDeletedUserException;
import com.example.pstagram.exception.user.EmailAlreadyExistsException;
import com.example.pstagram.exception.user.EmailNotFoundException;
import com.example.pstagram.exception.user.InvalidPasswordException;

@ControllerAdvice
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
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(409, message, null));
	}

	/**
	 * 이메일 없음 예외 처리
	 */
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleEmailNotFound(EmailNotFoundException ex) {
		String message = messageUtil.getMessage("user.email.not-found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, message, null));
	}

	@ExceptionHandler(EmptyCommentContentException.class)
	public ResponseEntity<String> handleEmptyCommentContent(EmptyCommentContentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleUserNotFound(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	/**
	 * 비밀번호 불일치 예외 처리
	 */
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalidPassword(InvalidPasswordException ex) {
		String message = messageUtil.getMessage("user.password.invalid");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(400, message, null));

	}

	@ExceptionHandler(DuplicateFriendRequestException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateRequest(DuplicateFriendRequestException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(new ApiResponse<>(409, messageUtil.getMessage(ex.getCode().getMessageKey()), null));
	}

	/**
	 * 회원탈퇴 예외 처리
	 */
	@ExceptionHandler(AlreadyDeletedUserException.class)
	public ResponseEntity<ApiResponse<Void>> handleAlreadyDeletedUserException(AlreadyDeletedUserException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
			.body(
				new ApiResponse<>(HttpStatus.CONFLICT.value(),
					messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<String> handlePostNotFound(PostNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		return new ResponseEntity<>(
			"@Valid failed: " + ex.getBindingResult().getFieldError().getDefaultMessage(),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FriendRequestNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleFriendRequestNotFound(FriendRequestNotFoundException
		ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
				messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<String> handleCommentNotFound(CommentNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(FriendNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleFriendNotFound(FriendNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
				messageUtil.getMessage(ex.getCode().getMessageKey()),
				null));
	}

	@ExceptionHandler(UnauthorizedCommentAccessException.class)
	public ResponseEntity<String> handleUnauthorized(UnauthorizedCommentAccessException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(
				new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
					messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

	@ExceptionHandler(CommentListEmptyException.class)
	public ResponseEntity<String> handleEmptyCommentList(CommentListEmptyException ex) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
	}

	@ExceptionHandler(SelfRequestException.class)
	public ResponseEntity<ApiResponse<Void>> handleSelfRequest(SelfRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(
				new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
					messageUtil.getMessage(ex.getCode().getMessageKey()),
					null));
	}

	@ExceptionHandler(EmptyUpdateContentException.class)
	public ResponseEntity<String> handleEmptyUpdate(EmptyUpdateContentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
