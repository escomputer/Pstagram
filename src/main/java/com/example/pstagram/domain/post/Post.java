package com.example.pstagram.domain.post;

import com.example.pstagram.domain.user.User;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 사용자가 작성한 게시물을 저장하는 엔티티
 * - 작성자(User)와 연관
 * - 본문 내용(content), 작성/수정 시각 포함
 * - 정렬 기준: createdAt DESC (최신순)
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

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
