package com.macs.groupone.friendbookapplication.jdbc;

import java.util.List;

import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;


public interface IJdbcManager
{
	<T> List<T> select(String procedureName, RowMapper<T> rowMapper, Object... parameters) throws DatabaseAccessException;
	long insert(final String procedureName, final Object... parameters) throws DatabaseAccessException;
	int update(final String procedureName, final Object... parameters) throws DatabaseAccessException;

}