package com.event.image.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.event.review.domain.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Image {

	@Id
	@Column(name = "image_id")
	private String imageId;	//이미지 아이디
	
	@CreationTimestamp
	private LocalDateTime regDtime;	//등록일시
	
	@UpdateTimestamp
	private LocalDateTime uptDtime;	//수정일시
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;
	
	@Builder
	public Image(String imageId, LocalDateTime regDtime, LocalDateTime uptDtime, Review review) {
		this.imageId = imageId;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
		this.review = review;
	}
}