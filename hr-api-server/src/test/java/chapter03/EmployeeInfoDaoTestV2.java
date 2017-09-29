
package chapter03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeInfoDaoTestV2 {
  static EmployeeInfoDao employeeInfoDao;

  @BeforeClass
  public static void setUpBeforeClass() {
    employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
  }

  @Test
  public void 정상_사번조회_테스트() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");

      assertEquals("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n",
          result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void null_사번조회_테스트() {
    try {
      String empNo = null;
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo(empNo);

      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void 존재하지_않는_사번조회_테스트() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");

      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test(expected = SQLException.class)
  public void SQL예외_테스트2() throws SQLException {
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
