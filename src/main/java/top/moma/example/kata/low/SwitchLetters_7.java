package top.moma.example.kata.low;

public class SwitchLetters_7 {

  public static String switcheroo(String x) {
    x = x.replaceAll("a", "x");
    x = x.replaceAll("b", "a");
    x = x.replaceAll("x", "b");
    return x;
  }
}
