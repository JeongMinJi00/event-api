package com.event.review.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.review.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

	@Query(value = "SELECT count(1) FROM review r \n" + 
            		"WHERE r.user_id =?1 \n"+ 
            		"AND r.place_id = ?2",
            nativeQuery = true)
	int isExistReview(@Param("userId") String userId, @Param("palceId") String placeId);
}
