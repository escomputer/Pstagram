package com.example.pstagram.repository.user;

import com.example.pstagram.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자(User) 엔티티에 대한 데이터베이스 접근을 담당하는 레포지토리
 */

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 입력된 이메일이 이미 존재하는지 여부를 확인합니다.
	 *
	 * @param email 중복 확인할 이메일 주소
	 * @return 해당 이메일이 존재하면 true, 없으면 false
	 */

	boolean existsByEmail(String email);
}