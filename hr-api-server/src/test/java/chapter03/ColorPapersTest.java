
package chapter03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import chapter03.ColorPapers.ColorPaper;

public class ColorPapersTest {

  @Test
  public void colorPaperTest() {
    int x = 1;
    int y = 11;
    ColorPaper colorPaper1 = new ColorPapers.ColorPaper(x, y);
    ColorPaper colorPaper2 = new ColorPapers.ColorPaper(x + 1, y + 1);
    assertThat(colorPaper1).isNotNull();
    assertThat(colorPaper2).isNotNull();

    assertThat(colorPaper1.p1X).isEqualTo(x);
    assertThat(colorPaper1.p1Y).isEqualTo(y - 10);

    assertThat(colorPaper2.p1X).isEqualTo(x + 1);
    assertThat(colorPaper2.p1Y).isEqualTo(y + 1 - 10);
  }

  @Test
  public void countTest() {
    int bigPaper[][] = new int[100][100];
    
    bigPaper[2][20] = 1;
    bigPaper[2][21] = 1;
    bigPaper[2][22] = 1;
    bigPaper[2][23] = 1;
        
    assertThat(ColorPapers.getCount(bigPaper)).isEqualTo(4);
  }
  
  @Test
  public void countPaint() {
    int bigPaper[][] = new int[100][100];
    
    bigPaper[2][20] = 1;
    bigPaper[2][21] = 1;
    bigPaper[2][22] = 1;
    bigPaper[2][23] = 1;
        
    assertThat(ColorPapers.getCount(bigPaper)).isEqualTo(4);
  }
  
  @Test
  public void paintTest() {
    int bigPaper[][] = new int[100][100];
    ColorPaper colorPaper = new ColorPapers.ColorPaper(3, 7);
    ColorPaper colorPaper2 = new ColorPapers.ColorPaper(15, 7);
    ColorPapers.paint(bigPaper, colorPaper);
    ColorPapers.paint(bigPaper, colorPaper2);
    assertThat(ColorPapers.getCount(bigPaper)).isEqualTo(200);
  }
}
