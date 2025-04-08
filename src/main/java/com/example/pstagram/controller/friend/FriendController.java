package com.example.pstagram.controller.friend;

import java.util.Locale;

import lombok.RequiredArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pstagram.dto.dto.friend.ApiResponse;
import com.example.pstagram.service.friend.FriendService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {
	private final FriendService friendService;
	private final MessageSource messageSource;

	private Long getCurrentUserId() {
		return 1L; //로그인한 유저아이디
	}

	@PostMapping("/request/{userId}")
	public ResponseEntity<ApiResponse<Void>> requestFriend(@PathVariable Long userId, Locale locale) {
		friendService.requestFriend(getCurrentUserId(), userId);
		String message = messageSource.getMessage("friend.request.success", null, locale);
		return ResponseEntity.ok(new ApiResponse<>(200, message, null));
	}

}
