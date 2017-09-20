
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeInfoDaoTestV3 {
  static EmployeeInfoDao employeeInfoDao;

  @BeforeClass
  public static void setUpBeforeClass() {
    employeeInfoDao = new EmployeeInfoDao();
    assertThat(employeeInfoDao).isNotNull();
  }

  @Test
  public void 정상_사번조회_테스트() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");

      assertThat(result.toString())
          .isEqualTo("403144|Kristin Ser ra|Staff|Sales|1993-02-27|57459\r\n");

      assertEquals(result.toString(),
          "403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n");
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

      assertThat(result.toString()).isEqualTo("");
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void 존재하지_않는_사번조회_테스트() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");

      assertThat(result.toString()).isEqualTo("");
    }
    catch (SQLException e) {
      fail("SQL 실행 오류");
    }
  }

  @Test
  public void SQL예외_테스트1() {
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
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
