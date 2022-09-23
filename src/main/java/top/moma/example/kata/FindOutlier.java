package top.moma.example.kata;

import java.util.Arrays;

/**
 * FindOutlier
 *
 * <p>You are given an array (which will have a length of at least 3, but could be very large)
 * containing integers. The array is either entirely comprised of odd integers or entirely comprised
 * of even integers except for a single integer N. Write a method that takes the array as an
 * argument and returns this "outlier" N.
 *
 * <p>Examples [2, 4, 0, 100, 4, 11, 2602, 36] Should return: 11 (the only odd number)
 *
 * <p>[160, 3, 1719, 19, 11, 13, -21] Should return: 160 (the only even number)
 *
 * @version 1.0
 * @author Created by ivan at 17:51.
 */
public class FindOutlier {
  static int find(int[] integers) {
    int outlier = 0;
    for (int i = 0; i < integers.length - 1; i++) {
      int c = integers[i];
      int n = integers[i + 1];
      int p = i == 0 ? integers[2] : integers[0];
      if ((c + n) % 2 == 0) {
        continue;
      } else {
        if ((c + p) % 2 == 0) {
          outlier = n;
          break;
        } else {
          outlier = c;
          break;
        }
      }
    }
    return outlier;
  }

  public static int find2(int[] integers) {
    // Since we are warned the array may be very large, we should avoid counting values any more
    // than we need to.

    // We only need the first 3 integers to determine whether we are chasing odds or evens.
    // So, take the first 3 integers and compute the value of Math.abs(i) % 2 on each of them.
    // It will be 0 for even numbers and 1 for odd numbers.
    // Now, add them. If sum is 0 or 1, then we are chasing odds. If sum is 2 or 3, then we are
    // chasing evens.
    int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
    int mod = (sum == 0 || sum == 1) ? 1 : 0;

    return Arrays.stream(integers)
        .parallel() // call parallel to get as much bang for the buck on a "large" array
        .filter(n -> Math.abs(n) % 2 == mod)
        .findFirst()
        .getAsInt();
  }

  public static void main(String[] args) {
    int[] ints = new int[] {Integer.MAX_VALUE, 0, 1};
    System.out.println(FindOutlier.find(ints));
  }
}
