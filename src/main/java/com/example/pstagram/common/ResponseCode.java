package com.example.pstagram.common;

public enum ResponseCode {
	REQUEST_SUCCESS("friend.request.success"),
	REQUEST_CANCEL("friend.request.cancel"),
	REQUEST_ACCEPT("friend.request.accept"),
	REQUEST_REJECT("friend.request.reject"),
	FRIEND_DELTE("friend.list.delete"),
	FRIEND_NOT_FOUND("friend.notfound"),
	FRIEND_ALREADY_REQUEST("friend.already.requested"),
	SELF_REQUEST("friend.self.request"),
	UNAUTHORIZED("user.unauthorized");

	private final String messageKey;

	ResponseCode(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
