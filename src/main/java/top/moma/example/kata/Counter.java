package top.moma.example.kata;

public class Counter {
  public int countSheeps(Boolean[] arrayOfSheeps) {
    // TODO May the force be with you
    int total = 0;
    for (Boolean b : arrayOfSheeps) {
      if (null != b && b) {
        total = total + 1;
      }
    }
    return total;
  }
}
