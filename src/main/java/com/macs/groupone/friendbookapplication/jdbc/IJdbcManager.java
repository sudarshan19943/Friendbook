package com.macs.groupone.friendbookapplication.jdbc;

import java.util.List;

import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;

//References:
//https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch13s05.html
//https://www.javatpoint.com/RowMapper-example

public interface IJdbcManager
{
	<T> List<T> select(String procedureName, RowMapper<T> rowMapper, Object... parameters) throws DatabaseAccessException;
	long insert(final String procedureName, final Object... parameters) throws DatabaseAccessException;
	int update(final String procedureName, final Object... parameters) throws DatabaseAccessException;

}



