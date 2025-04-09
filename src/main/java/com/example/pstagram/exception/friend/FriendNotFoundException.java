package com.example.pstagram.exception.friend;

public class FriendNotFoundException extends RuntimeException {
	public FriendNotFoundException(String message) {
		super(message);
	}
}
