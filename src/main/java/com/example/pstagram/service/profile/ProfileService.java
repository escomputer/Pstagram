package com.example.pstagram.service.profile;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.repository.profile.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	private final ProfileRepository profileRepository;

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
