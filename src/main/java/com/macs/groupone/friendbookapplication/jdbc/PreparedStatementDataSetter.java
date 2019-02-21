
package com.macs.groupone.friendbookapplication.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface PreparedStatementDataSetter
{
  void setParameters(final PreparedStatement statement, final Object... parameters) throws SQLException;
}
