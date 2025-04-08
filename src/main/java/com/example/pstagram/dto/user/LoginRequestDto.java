package com.example.pstagram.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 사용자 로그인 요청 정보를 담는 DTO 클래스
 *
 * - 이메일과 비밀번호는 모두 필수 값입니다
 */

@Getter
public class LoginRequestDto {

	/**
	 * 로그인할 사용자 이메일
	 */

	@Email(message = "이메일 형식이 아닙니다.")
	@NotBlank(message = "이메일은 필수입니다.")
	private String email;

	/**
	 * 로그인할 사용자 비밀번호
	 */

	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;
}
