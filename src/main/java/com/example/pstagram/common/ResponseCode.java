package com.example.pstagram.common;

public enum ResponseCode {
	REQUEST_SUCCESS("friend.request.success"),
	REQUEST_CANCEL("friend.request.cancel"),
	REQUEST_ACCEPT("friend.request.accept"),
	REQUEST_REJECT("friend.request.reject"),
	FRIEND_DELTE("friend.list.delete");

	private final String messageKey;

	ResponseCode(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
