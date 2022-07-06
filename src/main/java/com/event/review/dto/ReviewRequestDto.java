package com.event.review.dto;

import java.util.ArrayList;
import java.util.List;

import com.event.image.domain.Image;
import com.event.review.domain.Review;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequestDto {

	private String type;
	private ActionType action;
	private String reviewId;
	private String content;
	private List<String> attachedPhotoIds;
	private String userId;
	private String placeId;
	private List<Image> images = new ArrayList<Image>();;
	
	@Builder
	public ReviewRequestDto(String type, ActionType action, String reviewId, String content, List<String> attachedPhotoIds, String userId, String placeId, List<Image> images) {
		this.type = type;
		this.action = action;
		this.reviewId = reviewId;
		this.content = content;
		this.attachedPhotoIds = attachedPhotoIds;
		this.userId = userId;
		this.placeId = placeId;
		this.images = images;
	}
	
	public List<Image> toImageEntity(List<String> ids, Review reviewEntity) {
		for(String id : ids) {
			this.images.add(Image.builder()
					.imageId(id)
					.review(reviewEntity)
					.build());
		}
		return this.images;
	}

	public Review toEntity() {
		return Review.builder()
				.reviewId(reviewId)
				.content(content)
				.userId(userId)
				.placeId(placeId)
				.images(images)
				.build();
	}
}
