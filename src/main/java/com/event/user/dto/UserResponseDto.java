package com.event.user.dto;

import java.util.List;

import com.event.point.domain.PointHst;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

	private String userId;
	private long totalPoint;
	private List<PointHst> pointHsts;
	
	@Builder
	public UserResponseDto(String userId, long totalPoint, List<PointHst> pointHsts) {
		this.userId = userId;
		this.totalPoint = totalPoint;
		this.pointHsts = pointHsts;
	}
}
