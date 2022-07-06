package com.event.point.dto;

import lombok.Getter;

@Getter
public enum ApplyType {
	PAY("10", "지급"), 
	DEDUCT("20", "회수");
	
	private final String code;
	 private final String name;
	 
	 ApplyType(final String code, final String name) {
		 this.code = code;
		 this.name = name;
	}
}
