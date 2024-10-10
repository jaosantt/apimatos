package com.matosbrasil.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
	UNEXPECTED_ERROR(-1),
	SUCCESS(0),
	ERROR(1);
	
	private final Integer code;
	
}
