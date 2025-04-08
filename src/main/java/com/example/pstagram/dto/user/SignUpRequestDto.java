package com.example.pstagram.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * 회원가입 요청 정보를 담는 DTO 클래스입니다
 *
 * - 이메일, 비밀번호, 닉네임을 클라이언트로부터 입력받습니다
 * - 각 필드에 대해 형식 및 필수 여부를 유효성 검사합니다
 *
 */

@Getter
public class SignUpRequestDto {

	/**
	 * 사용자 이메일 주소
	 * - 이메일 형식이어야 하며
	 * - 빈 값일 수 없습니다.
	 */

	@Email(message = "이메일 형식이 아닙니다.")
	@NotBlank(message = "이메일은 필수입니다.")
	private final String email;

	/**
	 * 사용자 비밀번호
	 * - 8자 이상
	 * - 영문, 숫자, 특수문자를 각각 1자 이상 포함해야 합니다
	 * - 빈 값일 수 없습니다
	 */

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$",
		message = "비밀번호는 8자 이상, 영문/숫자/특수문자를 모두 포함해야 합니다."
	)
	// 정규 표현식

	private final String password;

	/**
	 * 사용자 닉네임
	 * - 빈 값일 수 없습니다
	 */

	@NotBlank(message = "닉네임은 필수입니다.")
	private final String nickname;


	public SignUpRequestDto(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

}
