package com.example.pstagram.domain.comment;

import com.example.pstagram.domain.post.Post;
import com.example.pstagram.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 게시물에 작성된 댓글을 저장하는 엔티티
 * - 댓글 작성자(User), 대상 게시물(Post)과 연관
 * - 정렬 기준: createdAt DESC (최신순)
 * - 댓글 수정은 작성자만 가능, 삭제는 작성자 또는 게시물 작성자만 가능
 */


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 빼
@Builder // ''
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
