package com.macs.groupone.friendbookapplication.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class DBUtil {

	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(Config.getProperty("spring.datasource.driver-class-name"));
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/demo_db?"
                            + "user=root&password=P@ssw0rd");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            preparedStatement = connect
                    .prepareStatement("SELECT email, password from demo_db.user");
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null && !resultSet.next()){
                System.out.println("no result");
                statement.close();
                return false;
            }else
            {
            	 System.out.println("resultSet: " + resultSet);
                 writeResultSet(resultSet);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return true;
    }
    
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            String user = resultSet.getString("user");
            String website = resultSet.getString("email");
            System.out.println("User: " + user);
            System.out.println("email: " + website);
            
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }



       
}
