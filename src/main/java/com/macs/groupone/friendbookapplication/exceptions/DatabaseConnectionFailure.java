
package com.macs.groupone.friendbookapplication.exceptions;

public class DatabaseConnectionFailure extends DatabaseAccessException {
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionFailure(final Throwable cause) {
		super(cause);
	}
}
