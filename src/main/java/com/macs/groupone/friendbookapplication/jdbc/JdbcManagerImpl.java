package com.macs.groupone.friendbookapplication.jdbc;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.config.ApplicationConfigPropertyConfigurator;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseConnectionFailure;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseOperationException;

public class JdbcManagerImpl implements IJdbcManager {
	
	private final  Logger logger = Logger.getLogger(JdbcManagerImpl.class);
	private final  String URL="spring.datasource.url";
	private final  String USER_NAME="spring.datasource.username";
	private final  String PASSWORD="spring.datasource.password";
	
	private String url;
	private String username;
	private String password;
	
	public JdbcManagerImpl() {
		this.url = ApplicationConfigPropertyConfigurator.getProperty(URL);
		this.username =ApplicationConfigPropertyConfigurator.getProperty(USER_NAME);
		this.password =ApplicationConfigPropertyConfigurator.getProperty(PASSWORD);
	}

	private final Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (final SQLException e) {
			logger.error("Connection failure" + e);
			throw new DatabaseConnectionFailure(e);
		}

	}

	private void closeConnection(final Connection connection, final PreparedStatement statement,
			final ResultSet resultSet) {
		if (null != connection)
			try {
				connection.close();
			} catch (final Exception e) {
				logger.error("Exception occured while closing connection" + e);
				e.printStackTrace();
			}
		if (null != statement)
			try {
				statement.close();
			} catch (final Exception e) {
				logger.error("Exception occured while closing Statement" + e);
				e.printStackTrace();
			}
		
		if (null != resultSet)
			try {
				resultSet.close();
			} catch (final Exception e) {
				logger.error("Exception occured while closing Resultset" + e);
				e.printStackTrace();
			}

	}

	private void rollback(final Connection connection) {
		if (null != connection) {
			try {
				connection.rollback();
			} catch (final Exception e) {
				logger.error("Connection error occured." + e);
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public <T> List<T> select(final String procedureName, final RowMapper<T> rowMapper, final Object... parameters)
			throws DatabaseAccessException {
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		final List<T> result = new ArrayList<T>();
		try {
			connection = getConnection();
			statement = connection.prepareCall(procedureName);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(rowMapper.map(resultSet));
			}
		} catch (final SQLException e) {
			logger.error("Database Exception occured." + e);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
		return result;
	}

	@Override
	public long insert(final String procedureName, final Object... parameters) throws DatabaseAccessException {
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareCall(procedureName);
			setParameters(statement, parameters);
			statement.executeUpdate();
			connection.commit();
			return 1;
		} catch (final DatabaseAccessException e) {
			logger.error("Database Access exception" + e);
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			logger.error("Database Exception occured." + e);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
	}

	@Override
	public int update(final String procedureName, final Object... parameters) throws DatabaseAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		final ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareCall(procedureName);
			setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			connection.commit();
			return result;
		} catch (final DatabaseAccessException e) {
			rollback(connection);
			logger.error("Database Access Exception occured." + e);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			logger.error("Exception occured while updating database" + e);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
	}

	

	private void setParameters(final PreparedStatement statement, final Object... parameters) throws SQLException {
		for (int i = 0, length = parameters.length; i < length; i++) {
			final Object parameter = parameters[i];
			final int parameterIndex = i + 1;
			if (null == parameter) {
				statement.setObject(parameterIndex, null);
			}  else if (parameter instanceof Short) {
				statement.setShort(parameterIndex, (Short) parameter);
			} else if (parameter instanceof String) {
				statement.setString(parameterIndex, (String) parameter);
			} else if (parameter instanceof Date) {
				statement.setDate(parameterIndex, new java.sql.Date(((Date) parameter).getTime()));
			} else if (parameter instanceof Calendar) {
				statement.setDate(parameterIndex, new java.sql.Date(((Calendar) parameter).getTimeInMillis()));
			} else if (parameter instanceof Integer) {
				statement.setInt(parameterIndex, (Integer) parameter);
			} else if (parameter instanceof Long) {
				statement.setLong(parameterIndex, (Long) parameter);
			} else if (parameter instanceof Boolean) {
				statement.setBoolean(parameterIndex, (Boolean) parameter);
			} else if (parameter instanceof Character) {
				statement.setString(parameterIndex, String.valueOf(parameter));
			} else if (parameter instanceof Byte) {
				statement.setByte(parameterIndex, (Byte) parameter);
			}else if (parameter instanceof Float) {
				statement.setFloat(parameterIndex, (Float) parameter);
			} else if (parameter instanceof Double) {
				statement.setDouble(parameterIndex, (Double) parameter);
			} else if (parameter instanceof BigDecimal) {
				statement.setBigDecimal(parameterIndex, (BigDecimal) parameter);
			}else if (parameter instanceof Blob) {
				statement.setBlob(parameterIndex, (Blob) parameter);
			}else if (parameter instanceof InputStream) {
				statement.setBinaryStream(parameterIndex, (InputStream) parameter);
			}  
			else {
				throw new IllegalArgumentException(
						String.format("parameter type is not found. [param: %s, paramIndex: %s]", parameter,
								parameterIndex));
			}
		}
	}
	
}
