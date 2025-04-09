package com.example.pstagram.controller.comment;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pstagram.dto.comment.CommentRequestDto;
import com.example.pstagram.dto.comment.CommentResponseDto;
import com.example.pstagram.service.comment.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private CommentService commentService;

	@PostMapping("/api/posts/{postId}/comments")
	public ResponseEntity<CommentResponseDto> save(
		@PathVariable Long postId,
		@RequestParam Long userId,
		@RequestBody CommentRequestDto commentRequestDto) {

		CommentResponseDto commentResponseDto = commentService.save(userId, postId, commentRequestDto);
		return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
	}

	@GetMapping("/api/posts/{postId}/comments")
	public ResponseEntity<List<CommentResponseDto>> findByPost(@PathVariable Long postId) {
		List<CommentResponseDto> commentResponseDtoList = commentService.getCommentsByPost(postId);
		return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
	}

}
