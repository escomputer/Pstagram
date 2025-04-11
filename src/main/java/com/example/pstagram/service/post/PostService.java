package com.example.pstagram.service.post;

import com.example.pstagram.config.MessageUtil;
import com.example.pstagram.domain.post.Post;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.post.PostListResponseDto;
import com.example.pstagram.dto.post.PostRequestDto;
import com.example.pstagram.dto.post.PostResponseDto;
import com.example.pstagram.exception.post.PostNotFoundException;
import com.example.pstagram.exception.post.UnauthorizedPostAccessException;
import com.example.pstagram.exception.user.EmailNotFoundException;
import com.example.pstagram.exception.user.UnauthorizedException;
import com.example.pstagram.repository.post.PostRepository;
import com.example.pstagram.repository.user.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시물 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final MessageUtil messageUtil;

	/**
	 * 게시물 목록을 최신순(createdAt DESC) + 페이징으로 조회
	 *
	 * @param page 현재 페이지 번호
	 * @param size 페이지당 게시물 수
	 * @return 페이징된 게시물 목록 응답 DTO
	 */
	@Transactional(readOnly = true)
	public PostListResponseDto getPostsSortedByCreatedAt(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<Post> postPage = postRepository.findAll(pageable);

		List<PostResponseDto> postDtos = postPage.getContent().stream()
			.map(post -> new PostResponseDto(
				post.getId(),
				post.getContent(),
				post.getUser().getNickname(),  // 연관된 User 정보 사용
				post.getCreatedAt()
			))
			.collect(Collectors.toList());

		return new PostListResponseDto(
			postDtos,
			postPage.getTotalPages(),
			(int)postPage.getTotalElements(),
			postPage.getNumber()
		);
	}

	/**
	 * 게시물 등록
	 *
	 * @param requestDto 게시물 본문
	 * @param session 로그인한 사용자 세션
	 * @return 저장된 게시물 응답 DTO
	 */
	@Transactional
	public PostResponseDto createPost(PostRequestDto requestDto, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		if (userId == null) {
			throw new UnauthorizedException("로그인이 필요합니다.");
		}

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new EmailNotFoundException("사용자를 찾을 수 없습니다."));

		Post post = Post.builder()
			.user(user)
			.content(requestDto.getContent())
			.build();

		Post savedPost = postRepository.save(post);

		return new PostResponseDto(
			savedPost.getId(),
			savedPost.getContent(),
			user.getNickname(),
			savedPost.getCreatedAt()
		);
	}

	/**
	 * 게시물 수정
	 *
	 * @param postId 게시물 ID
	 * @param requestDto 수정할 내용
	 * @param session 로그인한 사용자
	 * @return 수정된 게시물 응답
	 */
	@Transactional
	public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		if (userId == null) {
			throw new UnauthorizedException(messageUtil.getMessage("user.unauthorized"));
		}

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(
				messageUtil.getMessage("post.not-found")
			));

		if (!post.getUser().getId().equals(userId)) {
			throw new UnauthorizedPostAccessException(
				messageUtil.getMessage("post.unauthorized")
			);
		}

		post.updateContent(requestDto.getContent());

		return new PostResponseDto(
			post.getId(),
			post.getContent(),
			post.getUser().getNickname(),
			post.getCreatedAt()
		);
	}

	/**
	 * 게시물 삭제
	 *
	 * @param postId 삭제할 게시물 ID
	 * @param session 로그인한 사용자
	 */
	@Transactional
	public void deletePost(Long postId, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		if (userId == null) {
			throw new UnauthorizedException(messageUtil.getMessage("user.unauthorized"));
		}

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(
				messageUtil.getMessage("post.not-found")
			));

		if (!post.getUser().getId().equals(userId)) {
			throw new UnauthorizedPostAccessException(
				messageUtil.getMessage("post.unauthorized")
			);
		}

		postRepository.delete(post);
	}

}
