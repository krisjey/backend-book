
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
  public void ����_�����ȸ_�׽�Ʈ() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");

      assertThat(result.toString())
          .isEqualTo("403144|Kristin Ser ra|Staff|Sales|1993-02-27|57459\r\n");

      assertEquals(result.toString(),
          "403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n");
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void null_�����ȸ_�׽�Ʈ() {
    try {
      String empNo = null;
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo(empNo);

      assertThat(result.toString()).isEqualTo("");
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void ��������_�ʴ�_�����ȸ_�׽�Ʈ() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");

      assertThat(result.toString()).isEqualTo("");
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void SQL����_�׽�Ʈ1() {
    try {
      employeeInfoDao.getEmployeeInfoByNo("");
      fail("SQL ���� ����");
    }
    catch (SQLException e) {
      // ����
    }
  }

  @Test(expected = SQLException.class)
  public void SQL����_�׽�Ʈ2() throws SQLException {
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
