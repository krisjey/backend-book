package chapter03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BasicDao {
  private Logger logger = Logger.getLogger(BasicDao.class);
  
  protected Connection getConnection() {
    String url = "jdbc:mysql://172.28.128.4:3306/employees?serverTimezone=UTC";
    String username = "backend";
    String password = "kris34#$";

    Connection connect = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties info = new Properties();
      info.setProperty("user", username);
      info.setProperty("password", password);
      info.setProperty("characterSetResults", "utf-8");

      connect = DriverManager.getConnection(url, info);
    }
    catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }
    catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }

    return connect;
  }
  
  protected StringBuffer toApiResult(ResultSet resultSet) throws SQLException {
    StringBuffer builder = new StringBuffer();

    if (resultSet != null) {
      while (resultSet.next()) {
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
          if (i != 1) {
            builder.append("|");
          }
          builder.append(resultSet.getString(i));
        }
        builder.append("\r\n");
      }
    }

    return builder;
  }
}
