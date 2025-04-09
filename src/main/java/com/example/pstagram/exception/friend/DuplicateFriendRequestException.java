package com.example.pstagram.exception.friend;

public class DuplicateFriendRequestException extends RuntimeException {
	public DuplicateFriendRequestException(String message) {
		super(message);
	}
}
