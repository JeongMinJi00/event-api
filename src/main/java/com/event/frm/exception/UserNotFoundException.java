package com.event.frm.exception;

public class UserNotFoundException extends BaseException {

	public UserNotFoundException(int errorCode) {
		super(errorCode, "등록된 사용자를 찾을 수 없습니다.");
	}
}
