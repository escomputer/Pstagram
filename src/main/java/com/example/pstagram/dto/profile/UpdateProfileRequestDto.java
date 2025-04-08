package com.example.pstagram.dto.profile;

import lombok.Getter;

@Getter
public class UpdateProfileRequestDto {
	private final String nickname;
	private final String bio;

	public UpdateProfileRequestDto(String nickname, String bio) {
		this.nickname = nickname;
		this.bio = bio;
	}
}
