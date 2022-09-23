package top.moma.example.kata;

import java.util.Arrays;

/**
 * StarTower
 *
 * <p>Build a pyramid-shaped tower given a positive integer number of floors. A tower block is
 * represented with "*" character.
 *
 * <p>For example, a tower with 3 floors looks like this:
 *
 * <p>[ " * ", " *** ", "*****" ] And a tower with 6 floors looks like this:
 *
 * <p>[ " * ", " *** ", " ***** ", " ******* ", " ********* ", "***********" ]
 *
 * @version 1.0
 * @author Created by ivan at 16:15.
 */
public class StarTower {
  public static String[] towerBuilder(int nFloors) {
    // b
    int bottom = nFloors * 2 - 1;
    String[] tower = new String[nFloors];
    String stars = "";
    String space = "";
    for (int i = 0; i < nFloors; i++) {
      int a = (i + 1) * 2 - 1;
      stars = "";
      for (int s = 0; s < a; s++) {
        stars = stars + "*";
      }
      space = "";
      int b = (bottom - a) / 2;
      for (int p = 0; p < b; p++) {
        space = space + " ";
      }
      tower[i] = space + stars + space;
    }
    return tower;
  }

  public static String[] TowerBuilder2(int n) {
    String t[] = new String[n], e;

    for (int i = 0; i < n; i++) t[i] = (e = " ".repeat(n - i - 1)) + "*".repeat(i + i + 1) + e;

    return t;
  }

  public static void main(String[] args) {
    int n = 6;
    String[] t = StarTower.towerBuilder(n);
    Arrays.stream(t).forEach(System.out::println);
  }
}
