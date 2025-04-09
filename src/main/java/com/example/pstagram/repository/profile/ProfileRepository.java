package com.example.pstagram.repository.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.pstagram.domain.user.User;

public interface ProfileRepository extends JpaRepository<User, Long> {
	default User findByOrIdElseTrow(Long id){
		return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
	}
}
