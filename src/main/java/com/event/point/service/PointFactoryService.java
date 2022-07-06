package com.event.point.service;

import com.event.review.dto.ReviewRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointFactoryService {

    private final PointAddService pointAddService;
    private final PointModService pointModService;
    private final PointDeleteService pointDeleteService;

    public PointActionService getInstance(ReviewRequestDto dto) {

        final PointActionService pointActionService;

        switch (dto.getAction()) {
            case ADD:
            	pointActionService = pointAddService;
                break;
            case MOD:
            	pointActionService = pointModService;
                break;
            case DELETE:
            	pointActionService = pointDeleteService;
                break;
            default:
                throw new IllegalArgumentException("Unknown action type");
        }

        return pointActionService;
    }
}
