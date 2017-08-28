
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.Test;

public class EmployeeInfoDaoTest {

  @Test
  public void test() {
    EmployeeInfoDao employeeInfoDao = new EmployeeInfoDao();
    assertThat(employeeInfoDao).isNotNull();
    try {
      employeeInfoDao.getEmployeeInfoByNo("123123");
    }
    catch (SQLException e) {
      fail("");
    }
  }

}
