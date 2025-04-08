package com.example.pstagram.controller.user;

import com.example.pstagram.dto.user.LoginRequestDto;
import com.example.pstagram.dto.user.SignUpRequestDto;
import com.example.pstagram.dto.user.UserResponseDto;
import com.example.pstagram.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 관련 API 요청을 처리하는 컨트롤러
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")

public class UserController {

	private final UserService userService;

	/**
	 * 회원가입 API
	 *
	 * @param requestDto 클라이언트가 전송한 회원가입 정보
	 * @return 가입된 사용자 정보 응답
	 */

	@PostMapping("/signup")
	public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody SignUpRequestDto requestDto) {
		UserResponseDto responseDto = userService.signup(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	/**
	 * 로그인 API
	 *
	 * @param requestDto 로그인 요청 정보
	 * @param session    HttpSession 객체 (자동 주입)
	 * @return 로그인 성공 시 사용자 정보 반환
	 */
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto,
		HttpSession session) {
		UserResponseDto user = userService.login(requestDto);

		// 세션에 사용자 ID 저장
		session.setAttribute("userId", user.getId());

		return ResponseEntity.ok(user);
	}

}