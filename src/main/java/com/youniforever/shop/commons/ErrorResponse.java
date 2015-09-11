package com.youniforever.shop.commons;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class ErrorResponse {
	private String message;
	private String code;
	private List<FieldError> errors;
	
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", code=" + code + "]";
	}
	
	
	@Data
	@Setter
	@Getter
	public static class FieldError {
		private String field;
		private String value;
		private String reason;
	}
}