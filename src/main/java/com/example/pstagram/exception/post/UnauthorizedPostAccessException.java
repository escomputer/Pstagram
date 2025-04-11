package com.example.pstagram.exception.post;

/**
 * 게시물 작성자가 아닌 사용자가 접근할 때 발생하는 예외
 */
public class UnauthorizedPostAccessException extends RuntimeException {
	public UnauthorizedPostAccessException(String message) {
		super(message);
	}
}
