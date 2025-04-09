package com.example.pstagram.service.profile;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.profile.ProfileResponseDto;
import com.example.pstagram.dto.profile.UpdateProfileRequestDto;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.repository.profile.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final UserRepository userRepository;

	public ProfileResponseDto test(String email, String password, String nickname, String bio) {

		User user = new User(email, password, nickname, bio); // User 엔티티 생성

		User savedUser = userRepository.save(user); // 데이터베이스에 저장

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

		Optional<User> optionalUser = userRepository.findById(id); // ID로 User 엔티티 조회

		User findProfile = optionalUser.get(); // User 엔티티 가져오기 (Optional 에서)

		return new ViewProfileResponseDto(
			findProfile.getId(),
			findProfile.getNickname(),
			findProfile.getBio()
		);

	}

	@Transactional
	public void update(Long id, UpdateProfileRequestDto requestDto) {

		User user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 없습니다. id=" + id));

		user.updateProfile(requestDto.getNickname(), requestDto.getBio()); // User 엔티티 업데이트 (nickname, bio 수정)
	}

}
