package com.example.pstagram.service.user;

import com.example.pstagram.config.PasswordEncoder;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.user.SignUpRequestDto;
import com.example.pstagram.dto.user.UserResponseDto;
import com.example.pstagram.repository.user.UserRepository;
import com.example.pstagram.exception.user.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 회원가입 관련 비즈니스 로직을 처리하는 서비스 클래스
 */

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원가입 로직을 처리
	 *
	 * @param requestDto 클라이언트로부터 전달받은 회원가입 정보
	 * @return 가입된 사용자 정보를 담은 응답 DTO
	 * @throws EmailAlreadyExistsException 이메일이 이미 존재하는 경우
	 */

	@Transactional
	public UserResponseDto signup(SignUpRequestDto requestDto) {
		// 이메일 중복 검사
		if (userRepository.existsByEmail(requestDto.getEmail())) {
			throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
		}

		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

		// User 엔티티 생성
		User user = User.builder()
			.email(requestDto.getEmail())
			.password(encodedPassword)
			.nickname(requestDto.getNickname())
			.bio(null) // 가입 시 bio는 null로 시작 !
			.build();

		// 저장
		userRepository.save(user);

		// 응답 DTO 반환
		return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname());
	}
}