package com.event.place.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Place {

	@Id
	@Column(name = "place_id")
	private String placeId;	//장소 아이디
	
	private String placeNm;	//장소 명
	
	@CreationTimestamp
	private LocalDateTime regDtime;	//등록일시
	
	@UpdateTimestamp
	private LocalDateTime uptDtime;	//수정일시
	
	@Builder
	public Place(String placeId, String placeNm, LocalDateTime regDtime, LocalDateTime uptDtime) {
		this.placeId = placeId;
		this.placeNm = placeNm;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
	}
}