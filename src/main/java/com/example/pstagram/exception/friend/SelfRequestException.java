package com.example.pstagram.exception.friend;

public class SelfRequestException extends RuntimeException {
	public SelfRequestException(String message) {
		super(message);
	}
}
