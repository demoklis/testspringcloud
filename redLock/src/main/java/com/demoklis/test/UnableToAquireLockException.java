package com.demoklis.test;

/**
 * 不能获取锁异常
 * @author demoklis
 *
 */
public class UnableToAquireLockException extends Exception {
	public UnableToAquireLockException() {
	}
	
	public UnableToAquireLockException(String message) {
		super(message);
	}
	
	public UnableToAquireLockException(String message,Throwable cause) {
		super(message, cause);
	}
}
