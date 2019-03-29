/**
 *
 */
package com.macs.groupone.friendbookapplication.jdbc;

import java.math.BigDecimal;
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

import com.macs.groupone.friendbookapplication.config.Config;
import com.macs.groupone.friendbookapplication.controller.LoginController;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseConnectionFailure;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseAccessException;
import com.macs.groupone.friendbookapplication.exceptions.DatabaseOperationException;

public class JdbcManagerImpl implements JdbcManager {
	
	public static final String URL = "spring.datasource.url";
	public static final String USERNAME = "spring.datasource.username";
	public static final String PASSWORD = "spring.datasource.password";

	// Application FIle path
	public static final String APPLICATION_PROPERTIES = "src/main/resources/application.properties";

	private String url;
	private String username;
	private String password;

	private static final Logger log = Logger.getLogger(JdbcManagerImpl.class);
	
	public JdbcManagerImpl() {
		this.url = Config.getProperty(URL);
		this.username = Config.getProperty(USERNAME);
		this.password = Config.getProperty(PASSWORD);
	}

	protected final Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (final SQLException e) {
			log.error("Connection failure" + e);
			throw new DatabaseConnectionFailure(e);
		}

	}

	private void closeConnection(final Connection connection, final PreparedStatement statement,
			final ResultSet resultSet) {

		if (null != connection)
			try {
				connection.close();
			} catch (final Exception e) {
				log.error("Connection cannot be closed" + e);
				e.printStackTrace();
			}
		
		if (null != statement)
			try {
				statement.close();
			} catch (final Exception e) {
				log.error("Statement cannot be closed" + e);
				e.printStackTrace();
			}
		
		if (null != resultSet)
			try {
				resultSet.close();
			} catch (final Exception e) {
				log.error("Resultset cannot be closed" + e);
				e.printStackTrace();
			}

	}

	private void rollback(final Connection connection) {
		if (null != connection) {
			try {
				connection.rollback();
			} catch (final Exception e) {
				log.error("Connection error" + e);
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
			log.error("Database Exception" + e);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
			log.info("Connection closed");
		}
		return result;
	}

	@Override
	public long insertAndGetId(final String procedureName, final Object... parameters) throws DatabaseAccessException {
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareCall(procedureName);
			setParameters(statement, parameters);
			final int result = statement.executeUpdate();
			/*Long id = null;
			if (0 != result) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getLong(1);
				}
			}*/
			connection.commit();
			return 1;
		} catch (final DatabaseAccessException e) {
			log.error("Database exception" + e);
			rollback(connection);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			log.error("Database Exception" + e);
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
			log.error("Database Exception" + e);
			throw e;
		} catch (final Exception e) {
			rollback(connection);
			log.error("Database Exception" + e);
			throw new DatabaseOperationException(e);
		} finally {
			closeConnection(connection, statement, resultSet);
		}
	}



}
