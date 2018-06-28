
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class BasicDaoV2Test {
  @Test
  public void toApiResult순서테스트() throws SQLException {
    List<Map<String, Object>> result = new ArrayList<>();
    for (int rowNum = 0; rowNum < 2; rowNum++) {
      Map<String, Object> record = new LinkedHashMap<>();
      for (int fieldNum = 1; fieldNum < 6; fieldNum++) {
        record.put(String.valueOf(rowNum) + String.valueOf(fieldNum),
            String.valueOf(rowNum) + String.valueOf(fieldNum));
      }
      result.add(record);
    }

    StringBuilder builder = new StringBuilder();
    builder.append("01|02|03|04|05");
    builder.append("\r\n");
    builder.append("11|12|13|14|15");
    builder.append("\r\n");

    BasicDaoV2 basicDaoV2 = new BasicDaoV2();
    assertThat(basicDaoV2.toApiResult(result).toString()).isEqualTo(builder.toString());
  }
}
