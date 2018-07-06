
package chapter04;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BasicDaoV3 {
  protected StringBuffer toApiResult(List<Map<String, Object>> result) throws SQLException {
    StringBuffer builder = new StringBuffer();

    if (result != null) {
      for (Map<String, Object> record : result) {
        int i = 0;
        for (Entry<String, Object> field : record.entrySet()) {
          i++;
          if (i != 1) {
            builder.append("|");
          }
          builder.append(field.getValue());
        }
        builder.append("\r\n");
      }
    }

    return builder;
  }
}
