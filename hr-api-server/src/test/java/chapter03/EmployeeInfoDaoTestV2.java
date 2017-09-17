
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.Test;

public class EmployeeInfoDaoTestV2 {
  @Test
  public void test() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertThat(employeeInfoDao).isNotNull();

    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");
      assertThat(result).isNotNull();

      assertThat(result.toString()).isEqualTo("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n");
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }
}
