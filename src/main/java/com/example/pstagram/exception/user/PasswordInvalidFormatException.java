package com.example.pstagram.exception.user;

import com.example.pstagram.common.ResponseCode;

public class PasswordInvalidFormatException extends RuntimeException {
	private final ResponseCode code;

	public PasswordInvalidFormatException(ResponseCode code) {
		super(code.getMessageKey());
		this.code = code;
	}

	public ResponseCode getCode() {
		return code;
	}
}
