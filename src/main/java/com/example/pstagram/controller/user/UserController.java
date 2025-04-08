package com.example.pstagram.controller.user;

import com.example.pstagram.dto.user.SignUpRequestDto;
import com.example.pstagram.dto.user.UserResponseDto;
import com.example.pstagram.service.user.UserService;
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
}