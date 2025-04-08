package com.example.pstagram.domain.friend;

import com.example.pstagram.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 사용자 간의 친구 관계를 저장하는 엔티티
 * - 친구 요청자(requester), 수신자(receiver) 모두 User와 연관
 * - 상태(status)는 WAITING, ACCEPTED, REJECTED
 * - 요청/응답 시각 포함
 */


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor //
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(nullable = false)
    private String status; // WAITING, ACCEPTED, REJECTED

    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;

    @PrePersist
    public void onRequest() {
        this.requestedAt = LocalDateTime.now();
    }

    public void respond(String status) {
        this.status = status;
        this.respondedAt = LocalDateTime.now();
    }
}
