
package chapter04;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class DbManagerTest {
  @Test
  public void test() {
    SqlSession session = DbManager.getInstance().getSession(true);
    List<Map<String, Object>> result = session.selectList("Employees.getDepartmentManagerByNo", "d009");

    for (Map<String, Object> recored : result) {
      for (Entry<String, Object> field : recored.entrySet()) {
        System.out.println(field);
      }
    }
  }
}
