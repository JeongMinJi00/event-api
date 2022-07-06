package com.event.frm.exception;

public class ReviewNotFoundException extends BaseException {
	
	public ReviewNotFoundException(int errorCode) {
		super(errorCode, "등록된 리뷰를 찾을 수 없습니다.");
	}
}
