
package chapter03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

public class EmployeeInfoDaoTest {
  @Test
  public void 정상_사번조회_테스트() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");
      assertNotNull(result);
      assertEquals("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n",
          result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void null_사번조회_테스트() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      String empNo = null;
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo(empNo);
      assertNotNull(result);
      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void 존재하지_않는_사번조회_테스트() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");
      assertNotNull(result);
      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void SQL예외_테스트1() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      employeeInfoDao.getEmployeeInfoByNo("");
      fail("SQL 실행 오류");
    }
    catch (SQLException e) {
      // 성공
    }
  }
  
  @Test(expected = SQLException.class)
  public void SQL예외_테스트2() throws SQLException {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
