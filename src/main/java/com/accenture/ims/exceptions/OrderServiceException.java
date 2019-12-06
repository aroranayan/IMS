package com.accenture.ims.exceptions;

/**
 * Custom Exception class to be thrown from OrderServiceImpl
 * @author nayan.arora
 *
 */
public class OrderServiceException extends Exception {
	
	public OrderServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
