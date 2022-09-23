package top.moma.example.kata;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 *
 * <p>Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number
 * passed in. Additionally, if the number is negative, return 0 (for languages that do have them).
 *
 * <p>Note: If the number is a multiple of both 3 and 5, only count it once.
 *
 * <p>Courtesy of projecteuler.net (Problem 1)
 */
public class MultiplesSol {

  public int solution(int number) {
    if (number <= 0) {
      return 0;
    }
    Set<Integer> all = new HashSet<>();
    boolean three = true;
    boolean five = true;
    int counter = 1;
    int total = 0;
    while (three || five) {
      if (three) {
        int tRe = counter * 3;
        if (tRe < number) {
          all.add(tRe);
        } else {
          three = false;
        }
      }
      if (five) {
        int tFv = counter * 5;
        if (tFv < number) {
          all.add(tFv);
        } else {
          five = false;
        }
      }
      counter++;
    }
    return all.stream().collect(Collectors.summingInt(Integer::intValue));
  }

  public int solution2(int number) {
    return IntStream.range(0, number).filter(n -> (n % 3 == 0) || (n % 5 == 0)).sum();
  }

  public static void main(String[] args) {
    MultiplesSol multiplesSol = new MultiplesSol();
    int input = 1000000;
    Instant start;
    Instant finish;
    long timeElapsed;
    start = Instant.now();
    int out_1 = multiplesSol.solution(input);
    finish = Instant.now();
    timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(timeElapsed);
    start = Instant.now();
    int out_2 = multiplesSol.solution2(input);
    finish = Instant.now();
    timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(timeElapsed);
  }
}
