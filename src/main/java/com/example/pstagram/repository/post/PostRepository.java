package com.example.pstagram.repository.post;

import com.example.pstagram.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시물(Post) 데이터를 DB에서 조회/저장하는 Repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

	/**
	 * 사용자 ID로 해당 사용자가 작성한 게시물만 페이징 조회
	 * (선택 기능이지만 확장성 고려해 미리 추가)
	 */
	Page<Post> findAllByUserId(Long userId, Pageable pageable);
}
