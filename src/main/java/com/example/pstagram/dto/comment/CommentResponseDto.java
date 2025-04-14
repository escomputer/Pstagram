package com.example.pstagram.dto.comment;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CommentResponseDto {
	private final Long postId;
	private final Long commentId;
	private final String userName;
	private final String content;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public CommentResponseDto(Long postId, Long commentId, String userName, String content, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.postId = postId;
		this.commentId = commentId;
		this.userName = userName;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
