package top.moma.example.kata;

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
    boolean three = true;
    boolean five = true;
    int counter = 1;
    int total = 0;
    while (three || five) {
      if (three) {
        int tRe = counter * 3;
        if (tRe < number) {
          total = total + counter * 3;
        } else {
          three = false;
        }
      }
      if (five) {
        int tFv = counter * 5;
        if (tFv < number) {
          total = total + counter * 5;
        } else {
          five = false;
        }
      }
      counter++;
    }
    return total;
  }
}
