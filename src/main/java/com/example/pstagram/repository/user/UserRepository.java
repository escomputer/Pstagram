package com.example.pstagram.repository.user;

import java.nio.FloatBuffer;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pstagram.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
