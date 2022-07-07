package com.event.point.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.event.point.dto.ApplyType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class PointHst {

	@Id @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "point_id", columnDefinition = "VARCHAR(255)")
	private UUID pointId;
	
	@Enumerated(EnumType.STRING)
	private ApplyType applyTypeCd;	//지급유형(지급/회수)
	
	private long point;	
	
	private String userId;
	
	@CreationTimestamp
	private LocalDateTime regDtime;	//등록일시
	
	@UpdateTimestamp
	private LocalDateTime uptDtime;	//수정일시
	
	private String reviewId;	//리뷰 아이디
	
	@Builder
	public PointHst(UUID pointId, ApplyType applyTypeCd, long point, String userId, LocalDateTime regDtime, LocalDateTime uptDtime, String reviewId) {
		this.pointId = pointId;
		this.applyTypeCd = applyTypeCd;
		this.point = point;
		this.userId = userId;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
		this.reviewId = reviewId;
	}
}