
package chapter03;

import java.util.Scanner;

public class ColorPapers {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numberOfTest = scanner.nextInt();
    for (int i = 0; i < numberOfTest; i++) {
      testCase(scanner);
    }
    
    scanner.close();
  }

  private static void testCase(Scanner scanner) {
    int numberOfColorPaper = scanner.nextInt();
    int bigPaper[][] = new int[100][100];
    ColorPaper[] colorPaper = new ColorPaper[numberOfColorPaper];

    for (int i = 0; i < numberOfColorPaper; i++) {
      colorPaper[i] = new ColorPaper(scanner.nextInt(), scanner.nextInt());
    }

    for (int i = 0; i < numberOfColorPaper; i++) {
      paint(bigPaper, colorPaper[i]);
    }

    System.out.println(getCount(bigPaper));
  }

  public static int getCount(int[][] bigPaper) {
    int count = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        count += bigPaper[i][j];
      }
    }
    return count;
  }

  public static void paint(int[][] bigPaper, ColorPaper colorPaper) {
    for (int i = colorPaper.p1X; i < colorPaper.p1X + 10; i++) {
      for (int j = colorPaper.p1Y; j < colorPaper.p1Y + 10; j++) {
        bigPaper[i][j] = 1;
      }
    }
  }

  static class ColorPaper {
    int p1X;
    int p1Y;

    ColorPaper(int x, int y) {
      this.p1X = x;
      this.p1Y = y - 10;
    }
  }
}
