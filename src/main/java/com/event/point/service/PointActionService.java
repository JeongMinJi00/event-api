package com.event.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.point.dto.PointResponseDto;
import com.event.review.dto.ReviewRequestDto;

@Service
@Transactional
public interface PointActionService {
	public PointResponseDto handleAction(ReviewRequestDto reviewDto);
}
