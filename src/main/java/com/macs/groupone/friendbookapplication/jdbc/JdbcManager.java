package com.macs.groupone.friendbookapplication.jdbc;

import java.util.List;

import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;


public interface JdbcManager
{
  <T> List<T> select(String sql, RowMapper<T> rowMapper, Object... parameters) throws DatabaseAccessException;

  long insertAndGetId(final String sql, final Object... parameters) throws DatabaseAccessException;

  int update(final String sql, final Object... parameters) throws DatabaseAccessException;
}
