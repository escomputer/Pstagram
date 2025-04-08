package com.example.pstagram.exception.user;

/**
 * 로그인 시, 입력한 이메일에 해당하는 사용자가 존재하지 않을 경우 발생하는 예외
 */

public class EmailNotFoundException extends RuntimeException {

	/**
	 * 예외 메시지를 포함한 생성자
	 *
	 * @param message 예외 설명 메시지
	 */

	public EmailNotFoundException(String message) {
		super(message);
	}
}

