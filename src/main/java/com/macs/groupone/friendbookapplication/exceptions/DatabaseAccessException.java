
package com.macs.groupone.friendbookapplication.exceptions;

public class DatabaseAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseAccessException() {
	}

	public DatabaseAccessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DatabaseAccessException(final String message) {
		super(message);
	}

	public DatabaseAccessException(final Throwable cause) {
		super(cause);
	}
}
