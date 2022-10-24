package top.moma.example.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * SumOfSquares_4
 *
 * <p>The task is simply stated. Given an integer n (3 < n < 109), find the length of the smallest
 * list of perfect squares which add up to n. Come up with the best algorithm you can; you'll need
 * it!
 *
 * <p>Examples:
 *
 * <p>sum_of_squares(17) = 2 17 = 16 + 1 (16 and 1 are perfect squares). sum_of_squares(15) = 4 15 =
 * 9 + 4 + 1 + 1. There is no way to represent 15 as the sum of three perfect squares.
 * sum_of_squares(16) = 1 16 itself is a perfect square. Time constraints:
 *
 * <p>5 easy (sample) test cases: n < 20
 *
 * <p>5 harder test cases: 1000 < n < 15000
 *
 * <p>5 maximally hard test cases: 5e8 < n < 1e9
 *
 * <p>300 random maximally hard test cases: 1e8 < n < 1e9
 *
 * @version 1.0
 * @author Created by ivan at 12:01.
 */
public class SumOfSquares_4 {

  /**
   * description nSquaresFor
   *
   * <p>dp[0] = 0
   *
   * <p>dp[1] = dp[0] + 1 = 1
   *
   * <p>dp[2] = dp[1] + 1 = 2
   *
   * <p>dp[3] = dp[2] + 1 = 3
   *
   * <p>dp[4] = Min{dp[4-1*1]+1,dp[4-2*2]+1} = Min{dp[3]+1,dp[0]+1} = 1
   *
   * <p>dp[5] = Min{dp[5-1*1]+1,dp[5-2*2]+1} = Min{dp[4]+1,dp[1]+1} = 2
   *
   * <p>n - i*i >0
   *
   * <p>dp[n] = Min{dp[n-1*1]+1, dp[n-2*2]+1 ... dp[n-i*i]+1}, i>=1
   *
   * @param n n
   * @return int
   * @author Created by ivan
   * @since 2022/10/24 12:03
   */
  public static int nSquaresFor(int n) {
    // Your code here!
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      int min = Integer.MAX_VALUE;
      int j = 1;
      while (i - j * j >= 0) {
        min = Math.min(min, dp[i - j * j] + 1);
        ++j;
      }
      dp[i] = min;
    }
    return dp[n];
  }

  public static int nSquaresFor_2(int n) {
    int len = (int) Math.sqrt(n);
    for (int a = 0; a <= len; a++) {
      for (int b = a; b <= len; b++) {
        for (int c = b; c <= len; c++) {
          for (int d = c; d <= len; d++) {
            if (a * a + b * b + c * c + d * d == n) {
              System.out.println(a + " " + b + " " + c + " " + d);
              if (c == 0) {
                return 1;
              }
              if (b == 0) {
                return 2;
              }
              if (a == 0) {
                return 3;
              }
              return 4;
            }
          }
        }
      }
    }
    return 0;
  }

  @Test
  public void easyTests() {
    //    assertEquals(4, SumOfSquares_4.nSquaresFor(15));
    //    assertEquals(1, SumOfSquares_4.nSquaresFor(16));
    //    assertEquals(2, SumOfSquares_4.nSquaresFor(17));
    //    assertEquals(2, SumOfSquares_4.nSquaresFor(18));
    //    assertEquals(3, SumOfSquares_4.nSquaresFor(19));
    //    assertEquals(4, SumOfSquares_4.nSquaresFor_2(15));
    //    assertEquals(1, SumOfSquares_4.nSquaresFor_2(16));
    //    assertEquals(2, SumOfSquares_4.nSquaresFor_2(17));
    //    assertEquals(2, SumOfSquares_4.nSquaresFor_2(18));
    //    assertEquals(3, SumOfSquares_4.nSquaresFor_2(19));
  }
}
