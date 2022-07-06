package com.event.point.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.frm.exception.AlreadyExistsException;
import com.event.frm.util.ResponseCode;
import com.event.image.domain.Image;
import com.event.point.dto.ApplyType;
import com.event.point.dto.PointRequestDto;
import com.event.point.dto.PointResponseDto;
import com.event.review.domain.Review;
import com.event.review.dto.ReviewRequestDto;
import com.event.review.service.ReviewRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PointAddService implements PointActionService {
	
	private final PointHstRepository pointHstRepository;
	private final ReviewRepository reviewRepository;
	
	@Override
	public PointResponseDto handleAction(ReviewRequestDto reviewDto) {
		Review review =  reviewRepository.findById(reviewDto.getReviewId()).orElse(null);
		
		// 한 사용자는 장소마다 한 개의 리뷰 작성 가능
		int count = reviewRepository.isExistReview(reviewDto.getUserId(), reviewDto.getPlaceId());
		
		// 1. 포인트 이력 생성
		if(count > 0) {
			throw new AlreadyExistsException(ResponseCode.ALREADY_EXIST_REVIEW);
		} else {
			// 리뷰 등록
			Review reviewEntity = reviewDto.toEntity();
			
			// 이미지 생성
			List<String> ids = reviewDto.getAttachedPhotoIds();			
			List<Image> images =  reviewDto.toImageEntity(ids, reviewEntity);
			reviewEntity.setImages(images);
			
			reviewRepository.save(reviewEntity);
			
			long point = 0;
			
			point = reviewEntity.isExistsReview(review)
					+ reviewEntity.isExistsPhotos(reviewDto.getAttachedPhotoIds()) 
					+ reviewEntity.isExistsContent(reviewDto.getContent());
			
			PointRequestDto pointDto = PointRequestDto.builder()
					.applyTypeCd(ApplyType.PAY)
					.point(point)
					.userId(reviewDto.getUserId())
					.reviewId(reviewDto.getReviewId())
					.build();
			
			pointHstRepository.save(pointDto.toEntity());
		}
		
		return PointResponseDto.builder()
				.code(ResponseCode.OK)
				.msg("리뷰가 등록되었습니다.")
				.build();
	}
}
