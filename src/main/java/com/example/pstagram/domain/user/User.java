package com.example.pstagram.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 사용자 정보를 저장하는 엔티티
 * - 이메일, 비밀번호, 닉네임, 소개글 등 회원 정보 관리
 * - Soft Delete 지원: isDeleted + deletedAt
 */

@Entity
@Table(name = "users") // 테이블 이름 충돌 방지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String bio;

    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public User(String email, String password, String nickname, String bio, LocalDateTime deletedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.bio = bio;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Soft delete 처리: 탈퇴 시 호출
     */

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void updateProfile(String nickname, String bio) {
        this.nickname = nickname;
        this.bio = bio;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // base entity
}
