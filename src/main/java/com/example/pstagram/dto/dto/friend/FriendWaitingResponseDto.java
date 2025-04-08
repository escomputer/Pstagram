package com.example.pstagram.dto.dto.friend;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendWaitingResponseDto {
	private Long friendId;
	private Long requesterId;
	private String requesterName;
	private LocalDateTime requestedAt;
}
