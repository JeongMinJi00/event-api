package com.event.review.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.event.image.domain.Image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Review {
	
	@Id
	@Column(name = "review_id")
	private String reviewId;	//리뷰 아이디
	
	private String content;	//리뷰 내용
	
	private String placeId;	//장소 아이디
	
	private String userId;	//사용자 아이디
	
	@CreationTimestamp
	private LocalDateTime regDtime;	//등록일시
	
	@UpdateTimestamp
	private LocalDateTime uptDtime;	//수정일시
	
	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<Image>();
	
	public void addImage(Image image) {
		this.images.add(image);
		if(image.getReview() != this) {
			image.setReview(this);
		}
	}
	
	@Builder
	public Review(String reviewId, String content, String placeId, String userId, LocalDateTime regDtime, LocalDateTime uptDtime, List<Image> images) {
		this.reviewId = reviewId;
		this.content = content;
		this.placeId = placeId;
		this.userId = userId;
		this.regDtime = regDtime;
		this.uptDtime = uptDtime;
		this.images = images;
	}
	
	/**
	 * 해당 장소에 작성된 리뷰 있는지 확인(보너스 점수)
	 * @param review
	 * @return
	 */
	public long isExistsReview(Review review) {
		return (review == null) ? 1 : 0;
	}
	
	/**
	 * 리뷰 등록시 사진업로드 여부 확인(내용 점수)
	 * @param photos
	 * @return
	 */
	public long isExistsPhotos(List<String> photos) {
		long point = 0;
		if(photos != null && photos.size() > 0) {
			point = 1;
		}
		return point; 
	}
	
	/**
	 * 리뷰 등록시 내용작성 여부 확인(내용 점수)
	 * @param content
	 * @return
	 */
	public long isExistsContent(String content) {
		return content.length() > 0 ? 1 : 0; 
	}
}
