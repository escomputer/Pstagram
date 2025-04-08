package com.example.pstagram.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ProfileResponseDto {
	private final long id;
	private final String email;
	private final String password;
	private final String nickname;
	private final String bio;
	private final boolean is_deleted;
	private final LocalDateTime created_at;
	private final LocalDateTime updated_at;

	public ProfileResponseDto(long id, String email, String password, String nickname, String bio,
		boolean is_deleted,
		LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.bio = bio;
		this.is_deleted = is_deleted;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}


