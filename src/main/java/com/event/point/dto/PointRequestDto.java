package com.event.point.dto;

import java.time.LocalDateTime;

import com.event.point.domain.PointHst;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointRequestDto {

	private int pointId;
	private String userId;
	private ApplyType applyTypeCd;
	private long point;
	private LocalDateTime regDtime;
	private LocalDateTime uptDtime;
	private String reviewId;
	
	@Builder
	public PointRequestDto(int pointId, String userId, ApplyType applyTypeCd, long point, LocalDateTime regDtime, LocalDateTime uptDtime, String reviewId) {
		this.pointId = pointId;
		this.userId = userId;
		this.applyTypeCd = applyTypeCd;
		this.point = point;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
		this.reviewId = reviewId;
	}
	
	public PointHst toEntity() {
		return PointHst.builder()
				.userId(userId)
				.reviewId(reviewId)
				.applyTypeCd(applyTypeCd)
				.point(point)
				.regDtime(regDtime)
				.uptDtime(uptDtime)
				.build();
	}
}
