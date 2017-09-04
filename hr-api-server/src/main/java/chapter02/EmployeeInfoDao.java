
package chapter02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class EmployeeInfoDao {
  private Logger logger = Logger.getLogger(EmployeeInfoDao.class);

  private Connection getConnection() {
    String url = "jdbc:mysql://172.28.128.5:3306/employees";
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

  private StringBuffer buildEmployeeInfoByNo(ResultSet resultSet) throws SQLException {
    StringBuffer builder = new StringBuffer();

    if (resultSet != null) {
      while (resultSet.next()) {
        builder.append(resultSet.getString("emp_no"));
        builder.append("|");
        builder.append(resultSet.getString("name"));
        builder.append("|");
        builder.append(resultSet.getString("title"));
        builder.append("|");
        builder.append(resultSet.getString("dept_name"));
        builder.append("|");
        builder.append(resultSet.getString("hire_date"));
        builder.append("|");
        builder.append(resultSet.getString("salary"));
        builder.append("\r\n");
      }
    }

    return builder;
  }

  public StringBuffer getEmployeeInfoByNo(String empNo) throws SQLException {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    StringBuffer result = null;

    try {
      connection = getConnection();
      statement = connection.createStatement();

      StringBuffer builder = new StringBuffer();
      builder.append(" select ");
      builder.append("    emp.emp_no, ");
      builder.append("    concat(emp.first_name, ' ', emp.last_name) name, ");
      builder.append("    titles.title, ");
      builder.append("    dept.dept_name, ");
      builder.append("    emp.hire_date, ");
      builder.append("    sal.salary ");
      builder.append(" from ");
      builder.append("    employees emp ");
      builder.append("    INNER JOIN dept_emp on emp.emp_no = dept_emp.emp_no ");
      builder.append("    inner join departments dept on dept_emp.dept_no = dept.dept_no ");
      builder.append("    inner join titles on titles.emp_no = emp.emp_no ");
      builder.append("    inner join ( ");
      builder.append("        select ");
      builder.append("            emp_no, max(salary) salary ");
      builder.append("        from ");
      builder.append("            salaries ");
      builder.append("        where emp_no = " + empNo);
      builder.append("        group by emp_no ");
      builder.append("    ) sal on sal.emp_no = emp.emp_no ");
      builder.append(" where emp.emp_no = " + empNo);

      resultSet = statement.executeQuery(builder.toString());

      result = buildEmployeeInfoByNo(resultSet);
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
