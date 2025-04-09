package com.example.pstagram.exception.user;

/**
 * 이미 사용 중인 이메일로 회원가입을 시도할 경우 발생하는 예외
 */

public class EmailAlreadyExistsException extends RuntimeException {

	/**
	 * 예외 메시지를 포함한 생성자
	 *
	 * @param message 예외 설명 메시지
	 */

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
