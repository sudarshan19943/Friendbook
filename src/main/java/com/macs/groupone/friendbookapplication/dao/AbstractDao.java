
package com.macs.groupone.friendbookapplication.dao;

import com.macs.groupone.friendbookapplication.jdbc.JdbcManager;
import com.macs.groupone.friendbookapplication.jdbc.JdbcManagerImpl;

public abstract class AbstractDao {
	protected final JdbcManager jdbcManager() {
		return new JdbcManagerImpl();
	}
}
