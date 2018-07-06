
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.Test;

public class DepartmentManagerDaoV3Test {
  @Test
  public void 존재하지_않는_부서_관리자_조회() throws SQLException {
    DepartmentManagerDaoV3 departmentManagerDao = new DepartmentManagerDaoV3();
    StringBuffer result = departmentManagerDao.getDepartmentManagerByNo("");
    assertThat(result.toString()).isEqualTo("");
  }

  @Test
  public void 고객센터_관리자_조회() throws SQLException {
    DepartmentManagerDaoV3 departmentManagerDao = new DepartmentManagerDaoV3();
    StringBuffer result = departmentManagerDao.getDepartmentManagerByNo("d009");
    assertThat(result).isNotNull();

    assertThat(result.toString())
        .isEqualTo("d009|Customer Service|111939|Yuchang Weedman|1989-07-10|1996-01-03\r\n");
  }
}
