package com.example.pstagram.exception.post;

/**
 * 게시물이 존재하지 않을 때 발생하는 예외
 */
public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String message) {
		super(message);
	}
}
