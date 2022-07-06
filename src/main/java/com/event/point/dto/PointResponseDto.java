package com.event.point.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointResponseDto {

	private int code;
	private String msg;
	
	@Builder
	public PointResponseDto(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
