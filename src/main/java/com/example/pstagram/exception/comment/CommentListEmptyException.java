package com.example.pstagram.exception.comment;


public class CommentListEmptyException extends RuntimeException{
	public CommentListEmptyException() {
		super("댓글이 존재하지 않습니다.");
	}
}
