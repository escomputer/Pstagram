package com.example.pstagram.dto.friend;

// 요청 수락 , 요청 거절 에 대한 주석

import com.example.pstagram.domain.friend.FriendStatus;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendResponseDto {
	private FriendStatus status; // ACCEPTED 또는 REJECTED
}
