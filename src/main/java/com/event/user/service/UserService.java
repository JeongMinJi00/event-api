package com.event.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.event.frm.exception.UserNotFoundException;
import com.event.frm.util.ResponseCode;
import com.event.point.domain.PointHst;
import com.event.point.service.PointHstRepository;
import com.event.user.domain.User;
import com.event.user.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PointHstRepository pointHstRepository;
	
	public UserResponseDto getTotalPoint(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ResponseCode.NOT_EXIST_USER));
		
		List<PointHst> points = pointHstRepository.findTotalPoint(userId);
		long totalPoint = user.getTotalPoint(points);
		
		return UserResponseDto.builder()
				.userId(userId)
				.totalPoint(totalPoint)
				.pointHsts(points)
				.build();
	}
}
