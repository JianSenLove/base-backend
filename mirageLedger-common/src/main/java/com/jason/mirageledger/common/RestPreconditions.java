package com.jason.mirageledger.common;

import org.springframework.http.HttpStatus;

public class RestPreconditions {
	public static void checkParamArgument(boolean expression, Object errorMessage) {
		if (!expression) {
			throw new CustomException(errorMessage.toString());
		}
	}

	public static void checkParamArgument(boolean expression, Object errorMessage, HttpStatus status) {
		if (!expression) {
			throw new CustomException(errorMessage.toString());
		}
	}

}