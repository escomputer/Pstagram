package com.example.pstagram.controller.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pstagram.dto.profile.ProfileResponseDto;
import com.example.pstagram.dto.profile.ViewProfileResponseDto;
import com.example.pstagram.service.profile.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {
	private final ProfileService profileService;

	// @PostMapping("/test")
	// public ResponseEntity<ProfileResponseDto> test(@RequestBody)

	@GetMapping("/{id}")
	public  ResponseEntity<ViewProfileResponseDto> findById(@PathVariable Long id){

		ViewProfileResponseDto viewProfileResponseDto = profileService.findById(id);

		return new ResponseEntity<>(viewProfileResponseDto, HttpStatus.OK);
	}

}
