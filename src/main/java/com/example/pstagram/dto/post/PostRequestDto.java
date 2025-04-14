package com.example.pstagram.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 게시물 등록 및 수정 요청 DTO
 * - 게시물 본문(content)을 클라이언트로부터 전달받음
 */
@Getter
public class PostRequestDto {

	@NotBlank(message = "게시물 내용은 비어 있을 수 없습니다.")
	private String content;
}
