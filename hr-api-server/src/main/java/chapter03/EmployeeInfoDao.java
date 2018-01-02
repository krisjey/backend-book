
package chapter03;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeInfoDao extends BasicDao {
  public StringBuffer getEmployeeInfoByNo(String empNo) throws SQLException {
    StringBuffer result = null;
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
    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(builder.toString());) {

      result = toApiResult(resultSet);
    }

    return result;
  }
}
