package com.example.pstagram.domain.comment;

import com.example.pstagram.domain.Base;

import com.example.pstagram.domain.post.Post;
import com.example.pstagram.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * 게시물에 작성된 댓글을 저장하는 엔티티
 * - 댓글 작성자(User), 대상 게시물(Post)과 연관
 * - 정렬 기준: createdAt DESC (최신순)
 * - 댓글 수정은 작성자만 가능, 삭제는 작성자 또는 게시물 작성자만 가능
 */


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment extends Base {

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

    @Builder
    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    } // AllArgsConstructor 대신 ! (id를 제외하기 위해서)


    public void updateContent(String content) {
        this.content = content;
    }
}
