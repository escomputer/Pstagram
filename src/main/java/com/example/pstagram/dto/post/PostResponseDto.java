package com.example.pstagram.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 게시물 응답 DTO
 * - 게시물 상세 조회, 목록 조회 응답 시 사용
 */
@Getter
@AllArgsConstructor
public class PostResponseDto {

	private Long id;                // 게시물 ID
	private String content;         // 게시물 내용
	private String nickname;        // 작성자 닉네임
	private LocalDateTime createdAt; // 작성 시간
}
