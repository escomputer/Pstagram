package com.example.pstagram.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 응답을 감싸는 공통 응답 포맷입니다.
 *
 * @param <T> 실제 응답 데이터의 타입
 */
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

	/**
	 * 사용자에게 보여줄 응답 메시지
	 */
	private String message;

	/**
	 * 응답 데이터 (nullable)
	 */
	private T data;
}
