package com.example.pstagram.service.friend;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.pstagram.domain.friend.Friend;
import com.example.pstagram.domain.user.User;
import com.example.pstagram.dto.dto.friend.FriendListResponseDto;
import com.example.pstagram.dto.dto.friend.FriendResponseDto;
import com.example.pstagram.dto.dto.friend.FriendWaitingResponseDto;
import com.example.pstagram.repository.friend.FriendRepository;
import com.example.pstagram.repository.user.UserRepository;

//전체적으로 로그인한 상태가 아닐때? 예외가 추가되어야할것같다.
@Service
@RequiredArgsConstructor
public class FriendService {

	private final FriendRepository friendRepository;
	private final UserRepository userRepository;

	private User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
	}

	@Transactional
	public void requestFriend(Long requesterId, Long recieverId) {
		User requester = getUser(requesterId);
		User reciever = getUser(recieverId);

		if (friendRepository.findByRequesterAndReciever(requester, reciever).isPresent()) {
			throw new IllegalArgumentException("이미 보낸 요청입니다.");
		}// 중복 방지

		Friend friend = Friend.builder().requester(requester).receiver(reciever).build();

		friendRepository.save(friend);
	}

	@Transactional
	public void cancelRequest(Long requesterId, Long recieverId) {
		User requester = getUser(requesterId);
		User reciever = getUser(recieverId);

		Friend friend = friendRepository.findByRequesterAndReciever(requester, reciever)
			.orElseThrow(() -> new IllegalArgumentException("요청이 존재하지 않습니다."));

		friendRepository.delete(friend);
	}

	@Transactional
	public FriendResponseDto acceptFriend(Long requesterId, Long recieverId) {
		User reciever = getUser(recieverId);
		User requester = getUser(requesterId);

		Friend friend = friendRepository.findByRequesterAndReciever(requester, reciever)
			.orElseThrow(() -> new IllegalArgumentException("요청이 존재하지 않습니다."));
		friend.accept();
		return new FriendResponseDto(friend.getStatus());
	}

	@Transactional
	public FriendResponseDto rejectFriend(Long requesterId, Long currentUserId) {
		User requester = getUser(requesterId);
		User reciever = getUser(currentUserId);

		Friend friend = friendRepository.findByRequesterAndReciever(requester, reciever)
			.orElseThrow(() -> new IllegalArgumentException("요청이 존재하지 않습니다."));
		friend.reject();
		return new FriendResponseDto(friend.getStatus());

	}

	@Transactional(readOnly = true)
	public List<FriendListResponseDto> getFriendList(Long currentUserId) {
		User currentUser = getUser(currentUserId);

		List<FriendListResponseDto> friendList = friendRepository.findFriendList(currentUser.getId());
		//친구가 한명도 없을때 ! , 로그인한 사용자가 아닐때
		return friendList;
	}

	@Transactional(readOnly = true)
	public List<FriendWaitingResponseDto> getWaitingList(Long currentUserId) {
		User currentUser = getUser(currentUserId);

		List<FriendWaitingResponseDto> waitingResponseDtoList = friendRepository.findWaitingRequests(
			currentUser.getId());

		//로그인한 사용자가 아닐때, 친구요청이 1명도 없을때
		return waitingResponseDtoList;
	}

	@Transactional
	public void deleteFriend(Long friendId) {
		friendRepository.deleteById(friendId);

		//로그인한 사용자가 아닐떄
	}
}

