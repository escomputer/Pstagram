package com.example.pstagram.exception.comment;


public class EmptyUpdateContentException extends RuntimeException{
	public EmptyUpdateContentException() {
		super("수정할 댓글 내용이 없습니다.");
	}
}
