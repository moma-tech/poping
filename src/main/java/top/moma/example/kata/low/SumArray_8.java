package top.moma.example.kata.low;

    import java.util.Arrays;

public class SumArray_8 {

  public static double sum(double[] numbers) {

    return numbers.length > 0 ? Arrays.stream(numbers).sum() : 0;
  }
}
