
package chapter03;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class JunitAssertionTest {
  @Ignore
  @Test
  public void equals메서드_테스트() {
    assertEquals("1", "2");
  }
}
