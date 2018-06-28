
package chapter03;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JunitAnnotationTest {
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println("@BeforeClass");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("@AfterClass");
  }

  @Before
  public void setUp() throws Exception {
    System.out.println("@Before");
  }

  @After
  public void tearDown() throws Exception {
    System.out.println("@After");
  }

  @Test
  public void 첫_번째_테스트() {
    System.out.println("첫 테스트");
  }

  @Test
  @Ignore
  public void 두_번째_테스트() {
    System.out.println("두 번째 테스트");
  }
  
  @Test
  public void 세_번째_테스트() {
    System.out.println("세 번째 테스트");
  }
}
