package com.event.point.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointResponseDto {

	private String code;
	private String msg;
	
	@Builder
	public PointResponseDto(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
