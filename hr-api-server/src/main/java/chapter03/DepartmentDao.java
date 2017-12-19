
package chapter03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DepartmentDao {
  private Logger logger = Logger.getLogger(EmployeeInfoDao.class);

  private Connection getConnection() {
    String url = "jdbc:mysql://172.28.128.3:3306/employees";
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

  private StringBuffer buildResponse(ResultSet resultSet) throws SQLException {
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

  public StringBuffer getDepartmentInfo() throws SQLException {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    StringBuffer result = null;

    try {
      connection = getConnection();
      statement = connection.createStatement();

      StringBuffer builder = new StringBuffer();
      builder.append(" select dept_no, dept_name from departments ");

      resultSet = statement.executeQuery(builder.toString());
      result = buildResponse(resultSet);
    }
    finally {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }

    return result;
  }

  public StringBuffer getDepartmentEmployeeInfo(String departmentName, int size)
      throws SQLException {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    StringBuffer result = null;

    try {
      connection = getConnection();
      statement = connection.createStatement();

      StringBuffer builder = new StringBuffer();
      builder.append(" select  ");
      builder.append("     emp.emp_no, ");
      builder.append("     concat(emp.first_name, ' ', emp.last_name) name, ");
      builder.append("     emp.hire_date, ");
      builder.append("     dept3.dept_name ");
      builder.append(" from ");
      builder.append("     employees emp  ");
      builder.append("     inner join current_dept_emp dept  ");
      builder.append("         on dept.emp_no = emp.emp_no ");
      builder.append(
          "     inner join dept_emp_latest_date dept2 on dept.emp_no = dept2.emp_no  ");
      builder.append(
          "     and dept.from_date = dept2.from_date and dept.to_date = dept2.to_date ");
      builder.append(
          "     inner join departments dept3 on dept.dept_no = dept3.dept_no ");
      builder.append(" where dept3.dept_name = '" + departmentName + "' ");
      builder.append(" order by hire_date desc  ");
      builder.append(" limit " + size + " ");

      resultSet = statement.executeQuery(builder.toString());
      result = buildResponse(resultSet);
    }
    finally {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connection != null) {
        connection.close();
      }
    }

    return result;
  }
}
