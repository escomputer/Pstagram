package com.example.pstagram.dto.dto.friend;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.pstagram.domain.friend.FriendStatus;

// 친구 목록 반환해주는 dto

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendListResponseDto {
	private Long friendId;
	private Long requesterId;
	private String requesterName;
	private Long receiverId;
	private String receiverName;
	private FriendStatus status;
	private LocalDateTime requestedAt;
	private LocalDateTime respondedAt;
}
