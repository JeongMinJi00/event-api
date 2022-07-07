package com.event.frm.exception;

public class AlreadyExistsException extends BaseException {
	
	public AlreadyExistsException(String errorCode) {
		super(errorCode, "해당 장소에 이미 등록된 리뷰가 있습니다.");
	}
}
