
package com.macs.groupone.friendbookapplication.exceptions;

public class DatabaseOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseOperationException() {
	}

	public DatabaseOperationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DatabaseOperationException(final String message) {
		super(message);
	}

	public DatabaseOperationException(final Throwable cause) {
		super(cause);
	}
}
