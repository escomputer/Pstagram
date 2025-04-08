package com.example.pstagram.exception.user;

/**
 * 이미 탈퇴한 사용자일 경우 발생하는 예외 클래스
 *
 * deletedAt != null 인 사용자에게 탈퇴 요청이 들어올 경우 사용됩니다.
 */
public class AlreadyDeletedUserException extends RuntimeException {

	/**
	 * 메시지를 받아 부모 생성자에 전달하는 생성자입니다.
	 *
	 * @param message 메시지 (보통 message.properties에서 불러온 값)
	 */
	public AlreadyDeletedUserException(String message) {
		super(message);
	}
}
