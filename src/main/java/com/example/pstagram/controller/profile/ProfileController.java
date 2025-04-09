package com.example.pstagram.controller.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pstagram.dto.profile.CreatetestProfileRequestDto;
import com.example.pstagram.dto.profile.ProfileResponseDto;
import com.example.pstagram.dto.profile.UpdateProfileRequestDto;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.service.profile.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {
	private final ProfileService profileService;

//test용 회원 생성
	//후에 회원가입 기능과 합칠 때 삭제
	@PostMapping("/test")
	public ResponseEntity<ProfileResponseDto> test(@RequestBody CreatetestProfileRequestDto requestDto) {
		ProfileResponseDto profileResponseDto =
			profileService.test(
				requestDto.getEmail(),
				requestDto.getPassword(),
				requestDto.getNickname(),
				requestDto.getBio()
			);
		return new ResponseEntity<>(profileResponseDto, HttpStatus.CREATED);
	}

	//id로 유저 조회
	@GetMapping("/{id}")
	public  ResponseEntity<ViewProfileResponseDto> findById(@PathVariable Long id){

		ViewProfileResponseDto viewProfileResponseDto = profileService.findById(id);

		return new ResponseEntity<>(viewProfileResponseDto, HttpStatus.OK);
	}

	//현재 주어진 id에 해당하는 유저가 password를 통해 인증하여 수정할 수 있음
	//sesson을 통해 정보를 불러 오도록 수정 예정
	@PatchMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateProfileRequestDto requestDto) {

		// UserService를 통해 주어진 id에 해당하는 유저의 이메일을 수정합니다.
		profileService.update(id, requestDto);

		// 유저 정보 수정 성공 응답을 반환합니다.
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
