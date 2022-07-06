package com.event.point;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.point.dto.PointResponseDto;
import com.event.point.service.PointActionService;
import com.event.point.service.PointFactoryService;
import com.event.review.dto.ReviewRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PointController {
	
	private final PointFactoryService pointFactoryService;

	@PostMapping("/events")
	public ResponseEntity<PointResponseDto> event(@RequestBody ReviewRequestDto reviewDto) {
		PointActionService pointActionService = pointFactoryService.getInstance(reviewDto);
		return ResponseEntity.ok(pointActionService.handleAction(reviewDto));
	}
}
