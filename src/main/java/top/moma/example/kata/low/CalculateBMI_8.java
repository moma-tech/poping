package top.moma.example.kata.low;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateBMI_8 {
  public static String bmi(double weight, double height) {
    BigDecimal w = new BigDecimal(Double.toString(weight));
    BigDecimal h = new BigDecimal(Double.toString(height));
    double bmi = w.divide(h.pow(2), 1, RoundingMode.HALF_UP).doubleValue();
    if (bmi > 30) {
      return "Obese";
    } else if (bmi > 25) {
      return "OverWeight";
    } else if (bmi > 18.5) {
      return "Normal";
    }
    return "UnderWeight";
  }
}
