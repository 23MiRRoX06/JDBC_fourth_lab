package ua.lviv.iot.persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
  private static final String url =
      "jdbc:mysql://localhost:3306/ostap_koziaryk_itunes?currentSchema=ostap_koziaryk_itunes";
  private static final String user = "mirrox322";
  private static final String password = "528193746";

  private Connection connection = null;

  public ConnectionManager() {
  }

  public Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException exception) {
        System.out.println("SQLException:  " + exception.getMessage());
        System.out.println("SQLState:  " + exception.getSQLState());
        System.out.println("VendorError:  " + exception.getErrorCode());
      }
    }
    return connection;
  }
}
