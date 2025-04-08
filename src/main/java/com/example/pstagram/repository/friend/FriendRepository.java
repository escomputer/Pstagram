package com.example.pstagram.repository.friend;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pstagram.domain.friend.Friend;
import com.example.pstagram.domain.user.User;

public interface FriendRepository extends JpaRepository {
	Optional<Friend> findByBoth(User requester, User reciever);
}
