package top.moma.example.kata.low;

import java.time.Duration;
import java.time.Instant;

/**
 * PersistentBugger_6
 *
 * <p>Write a function, persistence, that takes in a positive parameter num and returns its
 * multiplicative persistence, which is the number of times you must multiply the digits in num
 * until you reach a single digit.
 *
 * <p>For example (Input --> Output):
 *
 * <p>39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit) 999 --> 4 (because
 * 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2) 4 --> 0 (because 4 is already a
 * one-digit number)
 *
 * @version 1.0
 * @author Created by ivan at 15:50.
 */
public class PersistentBugger_6 {
  public static int persistence(long n) {
    // your code
    int counter = 0;
    String valueString = n + "";
    while (1 < valueString.length()) {
      long result = 1L;
      for (int i = 0; i < valueString.length(); i++) {
        String pointer = String.valueOf(valueString.charAt(i));
        result = result * Long.valueOf(pointer);
      }
      valueString = result + "";
      counter++;
    }
    return counter;
  }

  public static int persistence2(long n) {
    long m = 1, r = n;

    if (r / 10 == 0) return 0;

    for (r = n; r != 0; r /= 10) m *= r % 10;

    return persistence(m) + 1;
  }

  public static int persistence3(long n) {
    int times = 0;
    while (n >= 10) {
      n = Long.toString(n).chars().reduce(1, (r, i) -> r * (i - '0'));
      times++;
    }
    return times;
  }

  public static void main(String[] args) {
    long input = 9999999999999999L;

    Instant start;
    Instant finish;
    long timeElapsed;
    start = Instant.now();

    PersistentBugger_6.persistence(input);
    finish = Instant.now();
    timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(timeElapsed);

    start = Instant.now();

    PersistentBugger_6.persistence2(input);
    finish = Instant.now();
    timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(timeElapsed);

    start = Instant.now();

    PersistentBugger_6.persistence3(input);
    finish = Instant.now();
    timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(timeElapsed);
  }
}
