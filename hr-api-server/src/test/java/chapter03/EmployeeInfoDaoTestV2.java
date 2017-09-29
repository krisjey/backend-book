
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
  public void ����_�����ȸ_�׽�Ʈ() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");

      assertEquals("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n",
          result.toString());
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

      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void ��������_�ʴ�_�����ȸ_�׽�Ʈ() {
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");

      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test(expected = SQLException.class)
  public void SQL����_�׽�Ʈ2() throws SQLException {
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
