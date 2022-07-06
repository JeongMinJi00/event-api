package com.event.point.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.frm.exception.ReviewNotFoundException;
import com.event.frm.util.ResponseCode;
import com.event.point.dto.ApplyType;
import com.event.point.dto.PointRequestDto;
import com.event.point.dto.PointResponseDto;
import com.event.review.domain.Review;
import com.event.review.dto.ReviewRequestDto;
import com.event.review.service.ReviewRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PointModService implements PointActionService {
	
	private final PointHstRepository pointHstRepository;
	private final ReviewRepository reviewRepository;
	
	@Override
	public PointResponseDto handleAction(ReviewRequestDto reviewDto) {
		Review review =  reviewRepository.findById(reviewDto.getReviewId()).orElse(null);
		
		if(review == null) {
			throw new ReviewNotFoundException(ResponseCode.NOT_EXIST_REVIEW);
		}
		
		ApplyType applyTypeCd = null;
		
		//기존 리뷰에 사진 등록여부 확인
		long beforePhoto = review.getImages().size();
		
		//수정한 리뷰에 사진 등록여부 확인
		long afterPhoto = review.isExistsPhotos(reviewDto.getAttachedPhotoIds());
		
		//글만 작성한 리뷰에 사진을 추가하면 1점을 부여합니다.
		if(beforePhoto < afterPhoto && beforePhoto == 0) {
			applyTypeCd = ApplyType.PAY;
		}
		
		//글과 사진이 있는 리뷰에서 사진을 모두 삭제하면 1점을 회수합니다.
		if(beforePhoto  > 0 && afterPhoto < 1) {
			applyTypeCd = ApplyType.DEDUCT;
		}
		
		if(applyTypeCd != null) {
			PointRequestDto pointDto = PointRequestDto.builder()
					.applyTypeCd(applyTypeCd)
					.point(1)
					.userId(reviewDto.getUserId())
					.reviewId(review.getReviewId())
					.build();
			
			pointHstRepository.save(pointDto.toEntity());
		}
		
		return PointResponseDto.builder()
				.code(ResponseCode.OK)
				.msg("리뷰가 수정되었습니다.")
				.build();
	}
}
