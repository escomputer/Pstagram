package com.example.pstagram.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 회원 탈퇴 요청 정보를 담는 DTO 클래스
 *
 * - 이메일, 현재 비밀번호는 모두 필수입니다
 */
@Getter
public class DeleteUserRequestDto {

	/**
	 * 탈퇴할 사용자 이메일
	 */
	@Email(message = "이메일 형식이 아닙니다.")
	@NotBlank(message = "이메일은 필수입니다.")
	private final String email;

	/**
	 * 본인 확인용 현재 비밀번호
	 */
	@NotBlank(message = "현재 비밀번호는 필수입니다.")
	private final String currentPassword;

	public DeleteUserRequestDto(String email, String currentPassword) {
		this.email = email;
		this.currentPassword = currentPassword;
	}

	// fix : 불변 객체
}
