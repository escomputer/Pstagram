package com.example.pstagram.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.pstagram.domain.Base;
import com.example.pstagram.domain.user.User;

/**
 * 사용자가 작성한 게시물을 저장하는 엔티티
 * - 작성자(User)와 연관
 * - 본문 내용(content), 작성/수정 시각 포함
 * - 정렬 기준: createdAt DESC (최신순)
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends Base {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Builder
	public Post(User user, String content) {
		this.user = user;
		this.content = content;
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
