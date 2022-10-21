package top.moma.example.kata.low;

import java.util.Arrays;

public class AveragePoints_8 {
  public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
    int a = classPoints.length;
    int total = Arrays.stream(classPoints).sum();
    int average = (total + yourPoints) / (a + 1);
    if (yourPoints > average) {
      return true;
    }
    return false;
  }
}
