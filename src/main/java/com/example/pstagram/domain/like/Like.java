package com.example.pstagram.domain.like;

import com.example.pstagram.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 게시물 또는 댓글에 대한 좋아요를 저장하는 엔티티
 * - 좋아요 대상은 targetType(POST/COMMENT)과 targetId로 구분
 * - 게시물은 본인 글에 좋아요 불가, 댓글은 가능
 * - 유저당 하나의 대상에 한 번만 좋아요 가능
 */


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor //
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String targetType; // POST 또는 COMMENT

    @Column(nullable = false)
    private Long targetId; // 게시물 ID 또는 댓글 ID

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
