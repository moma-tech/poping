package top.moma.example.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AmountDivide {

  public static List<String> divideAmount(String amount, String spilt) {
    List<String> orders = new ArrayList<>();
    BigDecimal total = new BigDecimal(amount);
    BigDecimal divisor = new BigDecimal(spilt);
    BigDecimal[] result = total.divideAndRemainder(divisor);
    int numbers = result[0].intValue();
    String rem = result[1].toPlainString();
    for (int i = 0; i < numbers; i++) {
      orders.add(spilt);
    }
    if (!rem.equals("0")) {
      orders.add(rem);
    }
    return orders;
  }

  public static void main(String[] args) {
    AmountDivide.divideAmount("10000", "10000").forEach(System.out::println);
  }
}
