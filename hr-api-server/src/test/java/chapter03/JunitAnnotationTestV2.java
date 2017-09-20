
package chapter03;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JunitAnnotationTestV2 {
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
  public void _1ù_��°_�׽�Ʈ() {
    System.out.println("ù �׽�Ʈ");
  }

  @Test
  public void _2��_��°_�׽�Ʈ() {
    System.out.println("�� ��° �׽�Ʈ");
  }
  
  @Test
  public void _3��_��°_�׽�Ʈ() {
    System.out.println("�� ��° �׽�Ʈ");
  }
}
