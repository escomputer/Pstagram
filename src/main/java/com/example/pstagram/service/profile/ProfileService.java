package com.example.pstagram.service.profile;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.profile.ProfileResponseDto;
import com.example.pstagram.dto.profile.UpdateProfileRequestDto;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.repository.profile.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;

	public ProfileResponseDto test(String email, String password, String nickname, String bio) {

		User user = new User(email, password, nickname, bio); // User 엔티티 생성

		User savedUser = profileRepository.save(user); // 데이터베이스에 저장

		// 생성된 사용자 정보를 SignUpUserResponseDto 로 변환하여 반환
		return new ProfileResponseDto(
			savedUser.getId(),
			savedUser.getEmail(),
			null,
			savedUser.getNickname(),
			savedUser.getBio(),
			savedUser.getCreatedAt(),
			null
		);

	}

	//유저 id로 유저 조회
	public ViewProfileResponseDto findById(Long id) {

		Optional<User> optionalUser = profileRepository.findById(id); // ID로 User 엔티티 조회

		User findProfile = optionalUser.get(); // User 엔티티 가져오기 (Optional 에서)

		return new ViewProfileResponseDto(
			findProfile.getId(),
			findProfile.getNickname(),
			findProfile.getBio()
		);

	}

	//현재 주어진 id에 해당하는 유저가 password를 통해 인증하여 수정할 수 있음
	//sesson을 통해 정보를 불러 오도록 수정 예정
	@Transactional
	public void update(Long id, UpdateProfileRequestDto requestDto) {
		User user = profileRepository.findById(id) // ID로 User 엔티티 조회
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id)); // 없으면 예외 발생

		User findUser = profileRepository.findByOrIdElseTrow(id); // ID로 User 엔티티 조회 (없으면 예외 발생)

		if (!user.getPassword().equals(requestDto.getPassword())) { // 비밀번호 일치 여부 확인
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."); // 일치하지 않으면 401 예외 발생
		}

		user.updateProfile(requestDto.getNickname(), requestDto.getBio());// User 엔티티 업데이트 (nickname, bio 수정)
	}
}
