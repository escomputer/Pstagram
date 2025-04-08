package com.example.pstagram.exception.user;

/**
 * 인증되지 않은 사용자가 접근 시 발생하는 예외입니다.
 */

public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
		super(message);
	}
}
