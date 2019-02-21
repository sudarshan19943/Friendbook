/**
 *
 */
package com.macs.groupone.friendbookapplication.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.macs.groupone.friendbookapplication.common.Config;
import com.macs.groupone.friendbookapplication.constants.Constants;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseConnectionFailure;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseOperationException;

public class JdbcManagerImpl implements JdbcManager {

	private String url;
	private String username;
	private String password;

	
	public JdbcManagerImpl() {
		this.url = Config.getProperty(Constants.URL);
		this.username = Config.getProperty(Constants.USERNAME);
		this.password = Config.getProperty(Constants.PASSWORD);
	}

	protected final Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (final SQLException e) {
			throw new DatabaseConnectionFailure(e);
		}

	}

	private void closeConnection(final Connection connection, final PreparedStatement statement,
			final ResultSet resultSet) {

		if (null != connection)
			try {
				connection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		
		if (null != statement)
			try {
				statement.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		
		if (null != resultSet)
			try {
				resultSet.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}

	}

	private void rollback(final Connection connection) {
		if (null != connection) {
			try {
				connection.rollback();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void setParameters(final PreparedStatement statement, final Object... parameters) throws SQLException {
		for (int i = 0, length = parameters.length; i < length; i++) {
			final Object parameter = parameters[i];
			final int parameterIndex = i + 1;
			if (null == parameter) {
				statement.setObject(parameterIndex, null);
			} else if (parameter instanceof Boolean) {
				statement.setBoolean(parameterIndex, (Boolean) parameter);
			} else if (parameter instanceof Character) {
				statement.setString(parameterIndex, String.valueOf(parameter));
			} else if (parameter instanceof Byte) {
				statement.setByte(parameterIndex, (Byte) parameter);
			} else if (parameter instanceof Short) {
				statement.setShort(parameterIndex, (Short) parameter);
			} else if (parameter instanceof Integer) {
				statement.setInt(parameterIndex, (Integer) parameter);
			} else if (parameter instanceof Long) {
				statement.setLong(parameterIndex, (Long) parameter);
			} else if (parameter instanceof Float) {
				statement.setFloat(parameterIndex, (Float) parameter);
			} else if (parameter instanceof Double) {
				statement.setDouble(parameterIndex, (Double) parameter);
			} else if (parameter instanceof String) {
				statement.setString(parameterIndex, (String) parameter);
			} else if (parameter instanceof Date) {
				statement.setDate(parameterIndex, new java.sql.Date(((Date) parameter).getTime()));
			} else if (parameter instanceof Calendar) {
				statement.setDate(parameterIndex, new java.sql.Date(((Calendar) parameter).getTimeInMillis()));
			} else if (parameter instanceof BigDecimal) {
				statement.setBigDecimal(parameterIndex, (BigDecimal) parameter);
			} else {
				throw new IllegalArgumentException(
						String.format("parameter is found. [param: %s, paramIndex: %s]", parameter,
								parameterIndex));
			}
		}
	}

	@Override
	public <T> List<T> select(final String sql, final RowMapper<T> rowMapper, final Object... parameters)
			throws DatabaseAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		final List<T> result = new ArrayList<T>();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(rowMapper.map(resultSet));
			}
		} catch (final SQLException e) {
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
		return result;
	}

	@Override
	public long insertAndGetId(final String sql, final Object... parameters) throws DatabaseAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			Long id = null;
			if (0 != result) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getLong(1);
				}
			}
			if (null == id) {
				throw new DatabaseOperationException("ID not found");
			}
			connection.commit();
			return id;
		} catch (final DatabaseAccessException e) {
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
	}

	@Override
	public int update(final String sql, final Object... parameters) throws DatabaseAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		final ResultSet resultSet = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			connection.commit();
			return result;
		} catch (final DatabaseAccessException e) {
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
	}
}
