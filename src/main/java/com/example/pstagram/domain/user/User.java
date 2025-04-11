package com.example.pstagram.domain.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.pstagram.domain.Base;

/**
 * 사용자 정보를 저장하는 엔티티
 * - 이메일, 비밀번호, 닉네임, 소개글 등 회원 정보 관리
 * - Soft Delete 지원: isDeleted + deletedAt
 */

@Entity
@Table(name = "users") // 테이블 이름 충돌 방지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Base {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nickname;

	private String bio;

	private LocalDateTime deletedAt; // 기본값 없이 null로!

	@Builder
	public User(String email, String password, String nickname, String bio) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.bio = bio;
	}

	public void updateProfile(String nickname, String bio) {
		this.nickname = nickname;
		this.bio = bio;
	}

	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}

}
