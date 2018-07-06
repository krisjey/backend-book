
package chapter04;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DepartmentManagerDaoV3 extends BasicDaoV3 {

  public StringBuffer getDepartmentManagerByNo(String deptNo) throws SQLException {
    List<Map<String, Object>> queryResult = null;

    try (SqlSession session = DbManager.getInstance().getSession();) {
      queryResult = session.selectList("Employees.getDepartmentManagerByNo", deptNo);
    }

    return toApiResult(queryResult);
  }
}
