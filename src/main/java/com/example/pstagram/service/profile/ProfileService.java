package com.example.pstagram.service.profile;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.profile.ProfileResponseDto;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.repository.profile.ProfileRepository;

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

	public ViewProfileResponseDto findById(Long id) {

		Optional<User> optionalUser = profileRepository.findById(id); // ID로 User 엔티티 조회

		User findProfile = optionalUser.get(); // User 엔티티 가져오기 (Optional 에서)

		return new ViewProfileResponseDto(
			findProfile.getId(),
			findProfile.getNickname(),
			findProfile.getBio()
		);

	}
}
