package com.event.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.user.dto.UserResponseDto;
import com.event.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
	
	private final UserService userService; 
	
	/**
	 * 회원 포인트 조회
	 * @param userId
	 * @return
	 */
	@GetMapping("/point/{userId}")
	public ResponseEntity<UserResponseDto> getTotalPoint(@PathVariable String userId) {
		return ResponseEntity.ok(userService.getTotalPoint(userId));
	}
}
