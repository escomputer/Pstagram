package com.example.pstagram.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 게시물 목록 페이징 응답 DTO
 * - 게시물 리스트 + 페이징 정보 포함
 */
@Getter
@AllArgsConstructor
public class PostListResponseDto {

	private List<PostResponseDto> posts; // 게시물 리스트
	private int currentPage;             // 현재 페이지
	private int totalPages;              // 전체 페이지 수
	private long totalElements;          // 전체 게시물 수
}
