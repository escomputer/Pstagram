package com.example.pstagram.exception.user;

/**
 * 로그인 시, 비밀번호가 일치하지 않을 경우 발생하는 예외
 */

public class InvalidPasswordException extends RuntimeException {

	/**
	 * 예외 메시지를 포함한 생성자
	 *
	 * @param message 예외 설명 메시지
	 */

	public InvalidPasswordException(String message) {
		super(message);
	}
}