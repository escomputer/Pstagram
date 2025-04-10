package com.example.pstagram.exception.comment;

public class EmptyCommentContentException extends RuntimeException{
	/**
	 * 댓글 내용이 비어있는 경우 예외
	 */

	public EmptyCommentContentException() {
		super("댓글 내용이 비어있습니다.");
	}
}
