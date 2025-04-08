package com.example.pstagram.domain.friend;

import com.example.pstagram.domain.Base;
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
public class Friend extends Base {

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
	private FriendStatus status; // WAITING, ACCEPTED, REJECTED

	private LocalDateTime requestedAt;
	private LocalDateTime respondedAt;

	public void accept() {
		this.status = FriendStatus.ACCEPTED;
		this.respondedAt = LocalDateTime.now();
	} //요청 수락 상황

	public void reject() {
		this.status = FriendStatus.REJECTED;
		this.respondedAt = LocalDateTime.now();
	} // 요청 거절상황

	@Builder
	public Friend(User requester, User receiver) {
		this.requester = requester;
		this.receiver = receiver;
		this.status = FriendStatus.WAITING;
		this.requestedAt = LocalDateTime.now();
	} // 생성자
}
