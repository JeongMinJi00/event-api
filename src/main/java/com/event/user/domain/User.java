package com.event.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.event.point.domain.PointHst;
import com.event.point.dto.ApplyType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "user_id")
	private String userId;		//사용자 아이디
	
	private String userNm;	//사용자 명
	
	@CreationTimestamp
	private LocalDateTime regDtime;	//등록일시
	
	@UpdateTimestamp
	private LocalDateTime uptDtime;	//수정일시
	
	@Builder
	public User(String userId, String userNm, LocalDateTime regDtime, LocalDateTime uptDtime) {
		this.userId = userId;
		this.userNm = userNm;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
	}
	
	public long getTotalPoint(List<PointHst> points) {
		long payPoint = points.stream()
				.filter(p -> ApplyType.PAY.equals(p.getApplyTypeCd()))
				.mapToLong(PointHst::getPoint).sum();
		
		long deductPoint = points.stream()
				.filter(p -> ApplyType.DEDUCT.equals(p.getApplyTypeCd()))
				.mapToLong(PointHst::getPoint).sum();
		
		return payPoint - deductPoint;
	}
}
