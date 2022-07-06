package com.event.point.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.point.domain.PointHst;

@Repository
public interface PointHstRepository  extends JpaRepository<PointHst, Integer> {

	@Query(value = "SELECT * FROM point_hst p \n" + 
            		"WHERE p.review_id =?1 \n " + 
            		"  AND p.user_id = ?2",
            nativeQuery = true)
	List<PointHst> searchReviewPoint(@Param("reviewId") String reviewId, @Param("userId") String userId);

	@Query(value = "SELECT * FROM point_hst \n " + 
    				"WHERE user_id = ?1",
    nativeQuery = true)
	List<PointHst> findTotalPoint(@Param("userId") String userId);

}
