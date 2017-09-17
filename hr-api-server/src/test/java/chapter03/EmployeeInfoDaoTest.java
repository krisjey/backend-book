
package chapter03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

public class EmployeeInfoDaoTest {
  @Test
  public void ����_�����ȸ_�׽�Ʈ() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("403144");
      assertNotNull(result);
      assertEquals("403144|Kristin Serra|Staff|Sales|1993-02-27|57459\r\n",
          result.toString());
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void null_�����ȸ_�׽�Ʈ() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      String empNo = null;
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo(empNo);
      assertNotNull(result);
      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void ��������_�ʴ�_�����ȸ_�׽�Ʈ() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    try {
      StringBuffer result = employeeInfoDao.getEmployeeInfoByNo("16");
      assertNotNull(result);
      assertEquals("", result.toString());
    }
    catch (SQLException e) {
      fail("SQL ���� ����");
    }
  }

  @Test
  public void SQL����_�׽�Ʈ1() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
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
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertNotNull(employeeInfoDao);
    employeeInfoDao.getEmployeeInfoByNo("");
  }
}
