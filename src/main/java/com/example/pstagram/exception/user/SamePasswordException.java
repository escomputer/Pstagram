package com.example.pstagram.exception.user;

/**
 * 현재 비밀번호와 동일한 비밀번호로 변경하려는 경우 발생
 */
public class SamePasswordException extends RuntimeException {
	public SamePasswordException(String message) {
		super(message);
	}
}

