
package chapter04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BasicDaoV3 {
  private Logger logger = Logger.getLogger(BasicDaoV3.class);

  private Connection getConnection() {
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

  protected StringBuffer toApiResult(List<Map<String, Object>> result) throws SQLException {
    StringBuffer builder = new StringBuffer();

    if (result != null) {
      for (Map<String, Object> record : result) {
        int i = 0;
        for (Entry<String, Object> field : record.entrySet()) {
          i++;
          if (i != 1) {
            builder.append("|");
          }
          builder.append(field.getValue());
        }
        builder.append("\r\n");
      }
    }

    return builder;
  }

  protected List<Map<String, Object>> executeQuery(StringBuffer query) throws SQLException {
    List<Map<String, Object>> records = new ArrayList<>();
    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query.toString())) {

      while (resultSet.next()) {
        Map<String, Object> record = new LinkedHashMap<>();
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
          record.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
        }
        records.add(record);
      }
    }

    return records;
  }
}
