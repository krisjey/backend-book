
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
  public void ù_��°_�׽�Ʈ() {
    System.out.println("ù �׽�Ʈ");
  }

  @Test
  @Ignore
  public void ��_��°_�׽�Ʈ() {
    System.out.println("�� ��° �׽�Ʈ");
  }
  
  @Test
  public void ��_��°_�׽�Ʈ() {
    System.out.println("�� ��° �׽�Ʈ");
  }
}
