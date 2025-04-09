package com.example.pstagram.dto.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {

	private String content;

	public CommentRequestDto(String content) {
		this.content = content;
	}
}
