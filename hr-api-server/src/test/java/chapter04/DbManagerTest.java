
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class DbManagerTest {
  @Test
  public void 마이바티스_SQL테스트() {
    SqlSession session = DbManager.getInstance().getSession();
    List<Map<String, Object>> result = session.selectList("Employees.getDepartmentManagerByNo", "d009");

    assertThat(result).isNotNull();

    assertThat(result.size()).isEqualTo(1);

    Map<String, Object> record = result.get(0);
    assertThat(record).isNotEmpty();

    assertThat(record.get("dept_no")).isEqualTo("d009");
    assertThat(record.get("emp_no")).isEqualTo(111939);
    assertThat(record.get("join_date").toString()).isEqualTo("1989-07-10");
    assertThat(record.get("name")).isEqualTo("Yuchang Weedman");
    assertThat(record.get("dept_name")).isEqualTo("Customer Service");
    assertThat(record.get("appointment_date").toString()).isEqualTo("1996-01-03");

  }
}