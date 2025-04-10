package com.example.pstagram.controller.post;

import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.dto.common.ApiResponse;
import com.example.pstagram.dto.post.PostListResponseDto;
import com.example.pstagram.dto.post.PostRequestDto;
import com.example.pstagram.dto.post.PostResponseDto;
import com.example.pstagram.service.post.PostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 게시물 관련 요청을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;
	private final MessageUtil messageUtil;



	/**
	 * 게시물 등록 API
	 *
	 * @param requestDto 게시물 내용
	 * @param session 로그인한 사용자 세션
	 * @return 생성된 게시물 응답 DTO
	 */
	@PostMapping
	public ApiResponse<PostResponseDto> createPost(
		@Valid @RequestBody PostRequestDto requestDto,
		HttpSession session
	) {
		PostResponseDto response = postService.createPost(requestDto, session);
		return new ApiResponse<>("게시물이 성공적으로 등록되었습니다.", response);
	}

	/**
	 * 게시물 목록 조회 (최신순 정렬 + 페이징)
	 *
	 * @param page 페이지 번호 (기본: 0)
	 * @param size 페이지 크기 (기본: 10)
	 * @return 게시물 목록 + 페이징 정보
	 */
	@GetMapping
	public ApiResponse<PostListResponseDto> getPosts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		PostListResponseDto response = postService.getPostsSortedByCreatedAt(page, size);
		return new ApiResponse<>("게시물 목록 조회 성공", response);
	}

	/**
	 * 게시물 수정 API
	 *
	 * @param postId 수정할 게시물 ID
	 * @param requestDto 수정할 내용
	 * @param session 로그인한 사용자 세션
	 */
	@PutMapping("/{postId}")
	public ApiResponse<PostResponseDto> updatePost(
		@PathVariable Long postId,
		@Valid @RequestBody PostRequestDto requestDto,
		HttpSession session
	) {
		PostResponseDto response = postService.updatePost(postId, requestDto, session);
		return new ApiResponse<>("게시물이 성공적으로 수정되었습니다.", response);
	}

	/**
	 * 게시물 삭제 API
	 *
	 * @param postId 삭제할 게시물 ID
	 * @param session 로그인한 사용자 세션
	 * @return 삭제 성공 메시지
	 */
	@DeleteMapping("/{postId}")
	public ApiResponse<Void> deletePost(
		@PathVariable Long postId,
		HttpSession session
	) {
		postService.deletePost(postId, session);
		String message = messageUtil.getMessage("post.deleted");
		return new ApiResponse<>(message, null);
	}

}
