package com.example.pstagram.domain.user;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 사용자 정보를 저장하는 엔티티
 * - 이메일, 비밀번호, 닉네임, 소개글 등 회원 정보 관리
 * - Soft Delete 지원: isDeleted + deletedAt
 */

@Entity
@Table(name = "users") // 테이블 이름 충돌 방지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

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
