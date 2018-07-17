
package chapter04;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Test;

public class MockitoTest {

  @Test
  public void 목업테스트() throws SQLException {
    DepartmentManagerDao daoMockup = mock(DepartmentManagerDao.class);

    when(daoMockup.getDepartmentManagerByNo("123"))
        .thenReturn(new StringBuffer("Hello world"));

    StringBuffer buffer = daoMockup.getDepartmentManagerByNo("123");
    assertThat(buffer.toString()).isEqualTo("Hello world");

    buffer = daoMockup.getDepartmentManagerByNo("124");
    assertThat(buffer).isNull();
  }
}
