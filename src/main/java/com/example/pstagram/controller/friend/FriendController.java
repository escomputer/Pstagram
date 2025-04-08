package com.example.pstagram.controller.friend;

import java.util.List;
import java.util.Locale;

import lombok.RequiredArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pstagram.dto.dto.friend.ApiResponse;
import com.example.pstagram.dto.dto.friend.FriendListResponseDto;
import com.example.pstagram.dto.dto.friend.FriendResponseDto;
import com.example.pstagram.dto.dto.friend.FriendWaitingResponseDto;
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

	@DeleteMapping("/request/{userId}")
	public ResponseEntity<ApiResponse<Void>> cancelRequest(@PathVariable Long userId, Locale locale) {
		friendService.cancelRequest(getCurrentUserId(), userId);
		String message = messageSource.getMessage("friend.cancel.success", null, locale);
		return ResponseEntity.ok(new ApiResponse<>(200, message, null));
	}

	@PostMapping("/status/{requesterId}/accept")
	public ResponseEntity<ApiResponse<FriendResponseDto>> acceptFriend(@PathVariable Long requesterId, Locale locale) {
		FriendResponseDto response = friendService.acceptFriend(requesterId, getCurrentUserId());
		String message = messageSource.getMessage("friend.accept.success", null, locale);
		return ResponseEntity.ok(new ApiResponse<>(200, message, response));
	}

	@PostMapping("status/{requesterId}/reject")
	public ResponseEntity<ApiResponse<FriendResponseDto>> rejectFriend(@PathVariable Long requesterId, Locale locale) {
		FriendResponseDto response = friendService.rejectFriend(requesterId, getCurrentUserId());
		String message = messageSource.getMessage("friend.reject.success", null, locale);
		return ResponseEntity.ok(new ApiResponse<>(200, message, response));

	}

	@GetMapping("/friend/accept")
	public ResponseEntity<ApiResponse<List<FriendListResponseDto>>> getFriends(Locale locale) {
		List<FriendListResponseDto> friends = friendService.getFriendList(getCurrentUserId());
		return ResponseEntity.ok(new ApiResponse<>(200, null, friends));
	}

	@GetMapping("/friend/waiting")
	public ResponseEntity<ApiResponse<List<FriendWaitingResponseDto>>> getWaitingList() {
		Long currentUserId = getCurrentUserId();
		List<FriendWaitingResponseDto> friends = friendService.getWaitingList(currentUserId);
		return ResponseEntity.ok(new ApiResponse<>(200, null, friends));

	}

}
