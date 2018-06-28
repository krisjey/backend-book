
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import chapter02.DepartmentDao;

public class DepartmentDaoTest {
  @Test
  public void 부서정보_조회_테스트() {
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

  @Test
  public void 부서별_최근입사자_조회_테스트() {
    String expect = "226633|Xuejun Benzmuller|2000-01-04|Marketing\r\n" +
        "270991|Rosita Fujisawa|1999-12-20|Marketing\r\n" +
        "277558|Herbert Luit|1999-11-17|Marketing\r\n" +
        "92030|Vitaly Zobel|1999-11-15|Marketing\r\n" +
        "437831|Gaetan Sooriamurthi|1999-11-12|Marketing\r\n" +
        "241837|Berni Jansch|1999-11-02|Marketing\r\n" +
        "84978|Yuguang Rajaraman|1999-10-16|Marketing\r\n" +
        "205927|Sudhanshu Zykh|1999-10-15|Marketing\r\n" +
        "413342|Tadahiro Scharstein|1999-10-03|Marketing\r\n" +
        "12495|Mooi Erni|1999-10-01|Marketing\r\n";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();

      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao
          .getDepartmentEmployeeInfo("Marketing", 10);
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }

  @Ignore
  @Test
  public void 고객서비스부서_최근입사자_조회_테스트() {
    String expect = "60134|Seshu Rathonyi|2000-01-02|Customer Service\r\n" +
        "73925|Vasilii Stavenow|1999-12-30|Customer Service\r\n" +
        "13919|Brewster Sinicrope|1999-12-04|Customer Service\r\n" +
        "218859|Arvin Nooteboom|1999-12-03|Customer Service\r\n" +
        "89511|Kazuhiro DuBourdieux|1999-11-27|Customer Service\r\n" +
        "65535|Aleksander Birjandi|1999-11-21|Customer Service\r\n" +
        "252656|Moti Nourani|1999-10-28|Customer Service\r\n" +
        "292220|Danny Ouhyoung|1999-10-26|Customer Service\r\n" +
        "461853|Licheng Ginneken|1999-10-14|Customer Service\r\n" +
        "498990|Adil Litecky|1999-10-12|Customer Service\r\n";

    DepartmentDao departmentDao = null;
    try {
      departmentDao = new DepartmentDao();

      assertThat(departmentDao).isNotNull();
      StringBuffer stringBuffer = departmentDao
          .getDepartmentEmployeeInfo("Customer Service", 10);
      assertThat(stringBuffer).isNotNull();
      assertThat(stringBuffer.toString()).isEqualTo(expect);
    }
    catch (SQLException e) {
      fail("Test Fail");
    }
  }
}
