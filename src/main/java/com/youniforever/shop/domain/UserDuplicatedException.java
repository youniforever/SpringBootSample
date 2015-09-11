package com.youniforever.shop.domain;

public class UserDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 7862064971836367741L;

	private String exceptionType;
	
	public UserDuplicatedException(String exceptionType) {
		this.setExceptionType(exceptionType);
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
}
