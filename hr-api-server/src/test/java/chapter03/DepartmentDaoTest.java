
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.Test;

public class DepartmentDaoTest {

  @Test
  public void testDepartmentInfo() {
    String expect = "d009|Customer Service\r\n" +
        "d005|Development\r\n" +
        "d002|Finance\r\n" +
        "d003|Human Resources\r\n" +
        "d001|Marketing\r\n" +
        "d004|Production\r\n" +
        "d006|Quality Management\r\n" +
        "d008|Research\r\n" +
        "d007|Sales\r\n";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();
      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao.getDepartmentInfo();
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }

  // 코드 수정함 바인드 안되어 있었음.
  @Test
  public void testDepartmentEmployeeInfoCount3() {
    String expect = "60134|Seshu Rathonyi|2000-01-02|Customer Service\r\n" +
        "73925|Vasilii Stavenow|1999-12-30|Customer Service\r\n" +
        "13919|Brewster Sinicrope|1999-12-04|Customer Service\r\n";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();

      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao
          .getDepartmentEmployeeInfo("Customer Service", 3);
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }

  @Test
  public void testDepartmentEmployeeInfoCount0() {
    String expect = "";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();

      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao
          .getDepartmentEmployeeInfo("Customer Service", 0);
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }

  @Test
  public void testDepartmentEmployeeInfoCount5() {
    String expect = "60134|Seshu Rathonyi|2000-01-02|Customer Service\r\n" +
        "73925|Vasilii Stavenow|1999-12-30|Customer Service\r\n" +
        "13919|Brewster Sinicrope|1999-12-04|Customer Service\r\n" +
        "218859|Arvin Nooteboom|1999-12-03|Customer Service\r\n" +
        "89511|Kazuhiro DuBourdieux|1999-11-27|Customer Service\r\n";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();

      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao
          .getDepartmentEmployeeInfo("Customer Service", 5);
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }
}
