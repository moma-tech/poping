package top.moma.example.kata;

public class Century {
  public static int century(int number) {
    // your code goes here
    if (number % 100 == 0) {
      return number / 100;
    } else {
      return number / 100 + 1;
    }
  }

  public static int century2(int number) {
    return (number + 99) / 100;
  }
}
