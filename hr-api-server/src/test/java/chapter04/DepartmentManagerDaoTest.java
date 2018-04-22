
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.Test;

public class DepartmentManagerDaoTest {
  @Test
  public void ��������_�ʴ�_�μ�_������_��ȸ() throws SQLException {
    DepartmentManagerDao departmentManagerDao = new DepartmentManagerDao();
    StringBuffer result = departmentManagerDao.getDepartmentManagerByNo("");
    assertThat(result).isNull();
  }

  @Test
  public void ������_������_��ȸ() throws SQLException {
    DepartmentManagerDao departmentManagerDao = new DepartmentManagerDao();
    StringBuffer result = departmentManagerDao.getDepartmentManagerByNo("d009");
    assertThat(result).isNotNull();

    assertThat(result.toString())
        .isEqualTo("d009|Customer Service|111939|Yuchang Weedman|1989-07-10|1996-01-03\r\n");
  }
}
