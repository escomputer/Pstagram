package com.example.pstagram.exception.friend;

import com.example.pstagram.common.ResponseCode;

public class UserNotFoundException extends RuntimeException {
	private final ResponseCode code;

	public UserNotFoundException(ResponseCode code) {
		super(code.getMessageKey());
		this.code = code;
	}

	public ResponseCode getCode() {
		return code;
	}
}

