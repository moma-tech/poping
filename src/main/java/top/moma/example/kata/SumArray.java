package top.moma.example.kata;

    import java.util.Arrays;

public class SumArray {

  public static double sum(double[] numbers) {

    return numbers.length > 0 ? Arrays.stream(numbers).sum() : 0;
  }
}
