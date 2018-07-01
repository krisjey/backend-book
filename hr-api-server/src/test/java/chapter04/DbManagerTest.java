
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class DbManagerTest {
  @Test
  public void test() {
    SqlSession session = DbManager.getInstance().getSession();
    List<Map<String, Object>> result = session.selectList("Employees.getDepartmentManagerByNo", "d009");

    assertThat(result).isNotNull();

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0)).isNotEmpty();

    assertThat(result.get(0).get("dept_no")).isEqualTo("d009");
    assertThat(result.get(0).get("emp_no")).isEqualTo("111939");
    assertThat(result.get(0).get("join_date")).isEqualTo("1989-07-10");
    assertThat(result.get(0).get("name")).isEqualTo("Yuchang Weedman");
    assertThat(result.get(0).get("dept_name")).isEqualTo("Customer Service");
    assertThat(result.get(0).get("appointment_date")).isEqualTo("1996-01-03");
  }
}