package com.example.pstagram.controller.comment;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.example.pstagram.common.ResponseCode;
import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.dto.comment.CommentRequestDto;
import com.example.pstagram.dto.comment.CommentResponseDto;
import com.example.pstagram.dto.common.ApiResponse;
import com.example.pstagram.service.comment.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}")
public class CommentController {

	private final CommentService commentService;
	private final MessageUtil messageUtil;

	@PostMapping("/comments")
	public ResponseEntity<ApiResponse<CommentResponseDto>> save(
		@PathVariable Long postId,
		@SessionAttribute(name = "userId", required = false) Long userId,
		@RequestBody CommentRequestDto commentRequestDto) {

		CommentResponseDto comment = commentService.save(userId, postId, commentRequestDto);
		String message = messageUtil.getMessage(ResponseCode.COMMENT_CREATE_SUCCESS.getMessageKey()); // 예시 메시지

		return ResponseEntity.ok(new ApiResponse<>(200, message, comment));
	}

	@GetMapping("/comments")
	public ResponseEntity<ApiResponse<List<CommentResponseDto>>> findByPost(@PathVariable Long postId) {
		List<CommentResponseDto> comments = commentService.getCommentsByPost(postId);

		return ResponseEntity.ok(new ApiResponse<>(200, null, comments));
	}

	@PutMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse<CommentResponseDto>> updateComment(
		@PathVariable Long commentId,
		@PathVariable Long postId,
		@SessionAttribute(name = "userId", required = false) Long userId,
		@RequestBody CommentRequestDto commentRequestDto) {

		CommentResponseDto updated = commentService.updateComment(commentId, userId, commentRequestDto);
		String message = messageUtil.getMessage(ResponseCode.COMMENT_UPDATE_SUCCESS.getMessageKey()); // 예시 메시지

		return ResponseEntity.ok(new ApiResponse<>(200, message, updated));
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse<Void>> deleteComment(
		@PathVariable Long commentId,
		@PathVariable Long postId,
		@SessionAttribute(name = "userId", required = false) Long userId) {

		commentService.deleteComment(userId, commentId);
		String message = messageUtil.getMessage(
			ResponseCode.COMMENT_DELETE_SUCCESS.getMessageKey()); // 또는 삭제 성공 메시지 하나 추가해도 됨

		return ResponseEntity.ok(new ApiResponse<>(200, message, null));
	}
}
