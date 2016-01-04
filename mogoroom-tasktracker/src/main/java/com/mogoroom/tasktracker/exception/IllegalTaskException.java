package com.mogoroom.tasktracker.exception;

public class IllegalTaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalTaskException() {
		super();
	}

	public IllegalTaskException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalTaskException(String message) {
		super(message);
	}

	public IllegalTaskException(Throwable cause) {
		super(cause);
	}
}
