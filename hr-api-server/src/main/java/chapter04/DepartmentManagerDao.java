
package chapter04;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DepartmentManagerDao extends BasicDaoV2 {

  public StringBuffer getDepartmentManagerByNo(String deptNo) throws SQLException {
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

    List<Map<String, Object>> queryResult = executeQuery(builder);

    return toApiResult(queryResult);
  }
}
