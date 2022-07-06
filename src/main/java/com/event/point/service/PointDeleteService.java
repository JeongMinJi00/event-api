package com.event.point.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.frm.exception.ReviewNotFoundException;
import com.event.frm.exception.UserNotFoundException;
import com.event.frm.util.ResponseCode;
import com.event.point.domain.PointHst;
import com.event.point.dto.ApplyType;
import com.event.point.dto.PointRequestDto;
import com.event.point.dto.PointResponseDto;
import com.event.review.domain.Review;
import com.event.review.dto.ReviewRequestDto;
import com.event.review.service.ReviewRepository;
import com.event.user.domain.User;
import com.event.user.service.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PointDeleteService implements PointActionService {
	
	private final PointHstRepository pointHstRepository;
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	
	@Override
	public PointResponseDto handleAction(ReviewRequestDto reviewDto) {
		Review review =  reviewRepository.findById(reviewDto.getReviewId()).orElse(null);
		
		if(review == null) {
			throw new ReviewNotFoundException(ResponseCode.NOT_EXIST_REVIEW);
		}
		
		//1. 리뷰 작성으로 적립된 포인트 조회
		User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new UserNotFoundException(ResponseCode.NOT_EXIST_USER));
		List<PointHst> points = pointHstRepository.searchReviewPoint(reviewDto.getReviewId(), reviewDto.getUserId());
		
		long totalPoint = user.getTotalPoint(points);
		
		//포인트 이력 쌓기
		PointRequestDto pointDto = PointRequestDto.builder()
				.applyTypeCd(ApplyType.DEDUCT)
				.point(totalPoint)
				.userId(reviewDto.getUserId())
				.reviewId(reviewDto.getReviewId())
				.build();
		
		pointHstRepository.save(pointDto.toEntity());
		
		//2. 리뷰삭제
		reviewRepository.deleteById(reviewDto.getReviewId());
		
		return PointResponseDto.builder()
				.code(ResponseCode.OK)
				.msg("리뷰가 삭제되었습니다.")
				.build();
	}
}
