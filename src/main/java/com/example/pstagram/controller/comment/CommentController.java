package com.example.pstagram.controller.comment;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pstagram.dto.comment.CommentRequestDto;
import com.example.pstagram.dto.comment.CommentResponseDto;
import com.example.pstagram.service.comment.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comments")
	public ResponseEntity<CommentResponseDto> save(
		@PathVariable Long postId,
		@RequestParam Long userId,
		@RequestBody CommentRequestDto commentRequestDto){

		// CommentResponseDto commentResponseDto = commentService.save(userId, postId, commentRequestDto);
		// return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

		return ResponseEntity.ok(commentService.save(userId,postId,commentRequestDto));
	}

	@GetMapping("/comments")
	public ResponseEntity<List<CommentResponseDto>> findByPost(@PathVariable Long postId) {
		// List<CommentResponseDto> commentResponseDtoList = commentService.getCommentsByPost(postId);
		// return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK); // 팀원들과 형태 맞추기!!

		return ResponseEntity.ok(commentService.getCommentsByPost(postId));
	}

	@PutMapping("/comments/{commentId}")
	public ResponseEntity<CommentResponseDto> updateComment(
		@PathVariable Long commentId,
		 @RequestParam Long userId,
		// @PathVariable Long postId,
		@RequestBody CommentRequestDto commentRequestDto) {

		// CommentResponseDto updateComment = commentService.updateComment(commentId, commentRequestDto);
		// return new ResponseEntity<>(updateComment, HttpStatus.OK);

		return ResponseEntity.ok(commentService.updateComment(commentId,userId,commentRequestDto));
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(
		@PathVariable Long commentId,
		@PathVariable Long postId,
		@RequestParam Long userId) {
		// commentService.deleteComment(commentId);
		// return new ResponseEntity<>(HttpStatus.OK);
		commentService.deleteComment(userId, commentId);
		return ResponseEntity.noContent().build();
	}
}
