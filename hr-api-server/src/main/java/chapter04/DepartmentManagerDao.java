
package chapter04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import chapter03.BasicDao;

public class DepartmentManagerDao extends BasicDao {

  public StringBuffer getDepartmentManagerByNo(String deptNo) throws SQLException {
    StringBuffer result = null;
    StringBuffer builder = new StringBuffer();
    builder.append(" SELECT ");
    builder.append("     dept.dept_no,");
    builder.append("     dept.dept_name,");
    builder.append("     emp.emp_no,");
    builder.append("     concat(emp.first_name, ' ', emp.last_name) name,");
    builder.append("     emp.hire_date join_date,");
    builder.append("     mgr.from_date appointment_date");
    builder.append(" FROM");
    builder.append("     departments dept");
    builder.append("         INNER JOIN");
    builder.append("     dept_manager mgr ON dept.dept_no = mgr.dept_no");
    builder.append("         INNER JOIN");
    builder.append("     employees emp ON mgr.emp_no = emp.emp_no");
    builder.append(" WHERE");
    builder.append("     dept.dept_no = '" + deptNo + "'");
    builder.append("         AND mgr.to_date > NOW()");

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(builder.toString());) {

      result = toApiResult(resultSet);
    }

    return result;
  }

}
