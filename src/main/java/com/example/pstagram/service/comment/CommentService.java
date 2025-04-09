package com.example.pstagram.service.comment;

import java.util.List;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.example.pstagram.domain.comment.Comment;
import com.example.pstagram.domain.post.Post;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.comment.CommentRequestDto;
import com.example.pstagram.dto.comment.CommentResponseDto;
import com.example.pstagram.repository.comment.CommentRepository;
import com.example.pstagram.repository.post.PostRepository;
import com.example.pstagram.repository.user.UserRepository;
import com.example.pstagram.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;


	@Transactional
	public CommentResponseDto save(Long userId, Long postId, CommentRequestDto commentRequestDto) {
		User user = userRepository.findById(userId).get();
		Post post = postRepository.findById(postId).get();

		Comment comment = Comment.builder()
			.user(user)
			.post(post)
			.content(commentRequestDto.getContent())
			.build();

		Comment savedComment = commentRepository.save(comment);

		return new CommentResponseDto(
			post.getId(),
			savedComment.getId(),
			user.getNickname(),
			savedComment.getContent(),
			savedComment.getCreatedAt(),
			savedComment.getUpdatedAt(),
			savedComment.getDeletedAt()
		);
	}

	public List<CommentResponseDto> getCommentsByPost(Long postId) {
		List<Comment> comments = commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId);

		return comments.stream()
			.map(comment -> new CommentResponseDto(
				comment.getPost().getId(),
				comment.getId(),
				comment.getUser().getNickname(),
				comment.getContent(),
				comment.getCreatedAt(),
				comment.getUpdatedAt(),
				comment.getDeletedAt()
			))
			.toList();
	}
}
