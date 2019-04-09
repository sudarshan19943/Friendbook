package com.macs.groupone.friendbookapplication.dao;

import com.macs.groupone.friendbookapplication.jdbc.IJdbcManager;
import com.macs.groupone.friendbookapplication.jdbc.JdbcManagerImpl;

public abstract class AbstractDao {
	protected final IJdbcManager jdbcManager() {
		return new JdbcManagerImpl();
	}
}
