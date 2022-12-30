package com.olmez.core.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/core";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "1234";

  private Connection connection;
  private static DbConnection instance;

  private DbConnection() throws SQLException {
    this.connection = DriverManager.getConnection(DB_CONNECTION_URL, USERNAME, PASSWORD);
  }

  public static DbConnection getInstance() throws SQLException {
    if (instance == null || instance.getConnection().isClosed()) {
      instance = new DbConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }

}