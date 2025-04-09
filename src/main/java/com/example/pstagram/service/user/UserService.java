package com.example.pstagram.service.user;

import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.config.PasswordEncoder;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.user.DeleteUserRequestDto;
import com.example.pstagram.dto.user.LoginRequestDto;
import com.example.pstagram.dto.user.SignUpRequestDto;
import com.example.pstagram.dto.user.UserResponseDto;
import com.example.pstagram.exception.user.EmailNotFoundException;
import com.example.pstagram.exception.user.InvalidPasswordException;
import com.example.pstagram.exception.user.EmailAlreadyExistsException;
import com.example.pstagram.exception.user.AlreadyDeletedUserException;
import com.example.pstagram.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 인증 및 회원 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final MessageUtil messageUtil;

	/**
	 * 회원가입 처리
	 *
	 * @param requestDto 클라이언트로부터 받은 회원가입 요청 정보
	 * @return 가입된 사용자 응답 DTO
	 */
	@Transactional
	public UserResponseDto signup(SignUpRequestDto requestDto) {
		if (userRepository.existsByEmail(requestDto.getEmail())) {
			throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
		}

		String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

		User user = User.builder()
			.email(requestDto.getEmail())
			.password(encodedPassword)
			.nickname(requestDto.getNickname())
			.bio(null)
			.build();

		userRepository.save(user);

		return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname());
	}

	/**
	 * 로그인 처리
	 *
	 * @param requestDto 로그인 요청 정보
	 * @return 로그인 성공 시 사용자 응답 DTO
	 */
	@Transactional(readOnly = true)
	public UserResponseDto login(LoginRequestDto requestDto) {
		User user = userRepository.findByEmail(requestDto.getEmail())
			.orElseThrow(() -> new EmailNotFoundException("존재하지 않는 이메일입니다."));

		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
		}

		return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname());
	}

	/**
	 * 회원 탈퇴 처리
	 *
	 * @param requestDto 탈퇴 요청 정보 (이메일 + 현재 비밀번호)
	 */
	@Transactional
	public void deleteUser(DeleteUserRequestDto requestDto) {
		User user = userRepository.findByEmail(requestDto.getEmail())
			.orElseThrow(() -> new EmailNotFoundException(
				messageUtil.getMessage("user.email.not-found")
			));

		if (user.getDeletedAt() != null) {
			throw new AlreadyDeletedUserException(
				messageUtil.getMessage("user.already-deleted")
			);
		}

		if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
			throw new InvalidPasswordException(
				messageUtil.getMessage("user.password.invalid")
			);
		}

		// Soft delete 처리
		userRepository.softDeleteById(user.getId());
	}
}
