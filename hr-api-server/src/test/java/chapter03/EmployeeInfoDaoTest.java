
package chapter03;

//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

public class EmployeeInfoDaoTest {
  @Test
  public void test() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
//    assertThat(employeeInfoDao).isNotNull();
    assertNotNull(employeeInfoDao);
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");
//      assertThat(result).isNotNull();
      assertNotNull(result);
      assertEquals("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n",
          result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }
}
