
package chapter03;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentDao extends BasicDao {
  public StringBuffer getDepartmentInfo() throws SQLException {
    StringBuffer result = null;

    StringBuffer builder = new StringBuffer();
    builder.append(" select dept_no, dept_name from departments ");

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(builder.toString());) {

      result = toApiResult(resultSet);
    }

    return result;
  }

  public StringBuffer getDepartmentEmployeeInfo(String departmentName, int size)
      throws SQLException {
    StringBuffer result = null;
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

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(builder.toString());) {

      result = toApiResult(resultSet);
    }

    return result;
  }
}
