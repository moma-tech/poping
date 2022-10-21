package top.moma.example.kata.low;

import java.math.BigDecimal;

public class Multiply_8 {
  public static Double multiply(Double a, Double b) {
    BigDecimal bigA = new BigDecimal(Double.toString(a));
    BigDecimal bigB = new BigDecimal(Double.toString(b));
    return bigA.multiply(bigB).doubleValue();
  }
}
