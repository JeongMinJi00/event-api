package com.event.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

	private String userId;
	private long totalPoint;
	
	@Builder
	public UserResponseDto(String userId, long totalPoint) {
		this.userId = userId;
		this.totalPoint = totalPoint;
	}
}
