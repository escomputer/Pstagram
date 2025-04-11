package com.example.pstagram.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;

/**
 * 비밀번호 변경 요청을 처리하는 DTO 클래스입니다.
 *
 * - 현재 비밀번호, 새 비밀번호를 입력받고 유효성 검사를 수행합니다.
 */

@Getter
public class UpdatePasswordRequestDto {

	/**
	 * 현재 비밀번호 (필수)
	 */
	@NotBlank(message = "user.password.required")
	private final String currentPassword;

	/**
	 * 새 비밀번호
	 * - 8자 이상, 영문/숫자/특수문자 포함
	 */
	@NotBlank(message = "user.password.required")
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
		message = "user.password.invalid-format"
	)
	private final String newPassword;

	public UpdatePasswordRequestDto(String currentPassword, String newPassword) {
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}
}
