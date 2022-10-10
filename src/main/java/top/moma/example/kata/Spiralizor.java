package top.moma.example.kata;

/**
 * Spiralizor
 *
 * <p>Your task, is to create a NxN spiral with a given size.
 *
 * <p>For example, spiral with size 5 should look like this:
 *
 * <p>00000 ....0 000.0 0...0 00000 and with the size 10:
 *
 * <p>0000000000 .........0 00000000.0 0......0.0 0.0000.0.0 0.0..0.0.0 0.0....0.0 0.000000.0
 * 0........0 0000000000 Return value should contain array of arrays, of 0 and 1, with the first row
 * being composed of 1s. For example for given size 5 result should be:
 *
 * <p>[[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]] Because of the edge-cases for
 * tiny spirals, the size will be at least 5.
 *
 * <p>General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.
 *
 * @version 1.0
 * @author Created by ivan at 15:29.
 */
public class Spiralizor {
  public static int[][] spiralize(int size) {
    int[][] result = new int[size][size];
    int sign = 1;
    int n4 = size % 4 == 0 ? 1 : 0;
    for (int loop = 0; loop <= size / 2; loop++) {
      if (loop % 2 == 0) {
        sign = 1;
      } else {
        sign = 0;
      }
      for (int x = loop; x < size - loop; x++) {
        result[loop][x] = sign;
        result[size - loop - 1][x] = sign;
        result[x][loop] = sign;
        result[x][size - loop - 1] = sign;
      }
      //      if (loop + n4 < size / 2) {
      //        result[loop + 1][loop] = sign == 1 ? 0 : 1;
      //      }
      if (size % 2 != 0 && loop < size / 2) {
        result[loop + 1][loop] = sign == 1 ? 0 : 1;
      }
      if (size % 2 == 0 && loop + 1 < size / 2) {
        result[loop + 1][loop] = sign == 1 ? 0 : 1;
      }
      if (size % 2 == 0 && ((size - 6) / 2) % 2 == 0 && loop + 1 == size / 2) {
        result[loop + 1][loop] = sign == 1 ? 0 : 1;
      }
    }
    return result;
  }

  public static int[][] spiralize2(int size) {
    if (size <= 0) return null;
    int[][] spiral = new int[size][size];
    int minCol = 0;
    int maxCol = size - 1;
    int minRow = 0;
    int maxRow = size - 1;

    for (int i = 0; i < spiral.length; i++) {
      for (int j = 0; j < spiral.length; j++) spiral[i][j] = 0;
    }

    while (minRow <= maxRow) {
      for (int i = minCol; i <= maxCol; i++) spiral[minRow][i] = 1;

      for (int i = minRow; i <= maxRow; i++) spiral[i][maxCol] = 1;

      if (minCol != 0) minCol += 1;
      if (maxRow - 1 == minRow) break;

      for (int i = maxCol - 1; i >= minCol; i--) spiral[maxRow][i] = 1;

      for (int i = maxRow - 1; i >= minRow + 2; i--) spiral[i][minCol] = 1;

      minCol += 1;
      minRow += 2;
      maxCol -= 2;
      maxRow -= 2;
    }
    return spiral;
  }

  public static int[][] spiralize3(int n) {
    int[][] a = new int[n][n];
    int n4 = n % 4 == 0 ? 1 : 0;
    for (int y = 0, c = 1; y <= n / 2; y++) {
      for (int x = y; x < n - y; x++)
        a[y][x] = a[x][n - 1 - y] = a[n - 1 - y][n - 1 - x] = a[n - 1 - x][y] = c;
      c = 1 - c;
      if (y + n4 < n / 2) a[y + 1][y] = c;
    }
    return a;
  }

  public static void main(String[] args) {
    int size = 8;

    // System.out.println(size / 2);
    // System.out.println(0 % 2);

    int[][] r = Spiralizor.spiralize(size);

    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[i].length; j++) {
        System.out.print(r[i][j]);
      }
      System.out.println();
    }
  }
}
