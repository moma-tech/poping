package top.moma.example.kata.low;

/**
 * Clock_8
 *
 * <p>DESCRIPTION: Clock shows h hours, m minutes and s seconds after midnight.
 *
 * <p>Your task is to write a function which returns the time since midnight in milliseconds.
 *
 * @version 1.0
 * @author Created by ivan at 12:06.
 */
public class Clock_8 {
  public static int Past(int h, int m, int s) {
    // Happy Coding! ^_^
    m = h * 60 + m;
    s = m * 60 + s;
    return s * 1000;
  }
}
