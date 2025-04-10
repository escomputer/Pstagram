package com.example.pstagram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.pstagram.exception.comment.CommentListEmptyException;
import com.example.pstagram.exception.comment.CommentNotFoundException;
import com.example.pstagram.exception.comment.EmptyCommentContentException;
import com.example.pstagram.exception.comment.EmptyUpdateContentException;
import com.example.pstagram.exception.post.PostNotFoundException;
import com.example.pstagram.exception.comment.UnauthorizedCommentAccessException;
import com.example.pstagram.exception.user.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyCommentContentException.class)
	public ResponseEntity<String> handleEmptyCommentContent(EmptyCommentContentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<String> handlePostNotFound(PostNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<String> handleCommentNotFound(CommentNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(UnauthorizedCommentAccessException.class)
	public ResponseEntity<String> handleUnauthorized(UnauthorizedCommentAccessException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}

	@ExceptionHandler(CommentListEmptyException.class)
	public ResponseEntity<String> handleEmptyCommentList(CommentListEmptyException ex) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
	}

	@ExceptionHandler(EmptyUpdateContentException.class)
	public ResponseEntity<String> handleEmptyUpdate(EmptyUpdateContentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
