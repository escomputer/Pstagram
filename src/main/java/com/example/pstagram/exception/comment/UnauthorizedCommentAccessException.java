package com.example.pstagram.exception.comment;

/**
 * 작성자가 일치 하지 않는 경우 예외
 */

public class UnauthorizedCommentAccessException extends RuntimeException{

	public UnauthorizedCommentAccessException() {
		super("댓글에 대한 권한이 없습니다.");
	}
}
