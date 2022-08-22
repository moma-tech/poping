package top.moma.example.kata;

import java.util.Arrays;

public class SquareSum {
  public static int squareSum(int[] n) {
    final Integer[] sum = {0};
    Arrays.stream(n)
        .forEach(
            nu -> {
              sum[0] = sum[0] + nu * nu;
            });
    return sum[0];
  }

  public static int squareSum2(int[] numbers) {
    int sum = 0;
    for (int n : numbers) {
      sum += n * n;
    }

    return sum;
  }

  
}
