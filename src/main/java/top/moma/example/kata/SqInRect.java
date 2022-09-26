package top.moma.example.kata;

import java.util.ArrayList;
import java.util.List;

/**
 * SqInRect
 *
 * <p>The drawing below gives an idea of how to cut a given "true" rectangle into squares ("true"
 * rectangle meaning that the two dimensions are different).
 *
 * <p>3 3 3 2 2 <br>
 *
 * <p>3 3 3 2 2 <br>
 *
 * <p>3 3 3 1 1
 *
 * <p>alternative text
 *
 * <p>Can you translate this drawing into an algorithm?
 *
 * <p>You will be given two dimensions
 *
 * <p>1. a positive integer length
 *
 * <p>2. a positive integer width
 *
 * <p>You will return a collection or a string (depending on the language; Shell bash, PowerShell,
 * Pascal and Fortran return a string) with the size of each of the squares.
 *
 * <p>Examples in general form: (depending on the language)
 *
 * <p>sqInRect(5, 3) should return [3, 2, 1, 1] sqInRect(3, 5) should return [3, 2, 1, 1]
 *
 * <p>You can see examples for your language in **"SAMPLE TESTS".
 *
 * <p>** Notes: lng == wdth as a starting case would be an entirely different problem and the
 * drawing is planned to be interpreted with lng != wdth. (See kata, Square into Squares. Protect
 * trees! http://www.codewars.com/kata/54eb33e5bc1a25440d000891 for this problem).
 *
 * <p>When the initial parameters are so that lng == wdth, the solution [lng] would be the most
 * obvious but not in the spirit of this kata so, in that case, return None/nil/null/Nothing or
 * return {} with C++, Array() with Scala, [] with Perl, Raku.
 *
 * <p>In that case the returned structure of C will have its sz component equal to 0.
 *
 * <p>Return the string "nil" with Bash, PowerShell, Pascal and Fortran.
 *
 * @version 1.0
 * @author Created by ivan at 11:00.
 */
public class SqInRect {
  public static List<Integer> sqInRect(int lng, int wdth) {
    List<Integer> result = new ArrayList<>();
    // your code
    int bigger = 0;
    int smaller = 0;
    if (lng > wdth) {
      bigger = lng;
      smaller = wdth;
    } else if (lng < wdth) {
      bigger = wdth;
      smaller = lng;
    } else {
      return null;
    }
    int remain = 0;
    while (bigger != smaller) {
      remain = bigger - smaller;
      result.add(smaller);
      while (remain > smaller) {
        result.add(smaller);
        remain = remain - smaller;
      }
      bigger = smaller;
      smaller = remain;
    }
    result.add(remain);
    return result;
  }

  public static List<Integer> sqInRect2(int lng, int wdth) {
    if (lng == wdth) return null;
    List<Integer> squares = new ArrayList<Integer>();
    int area = lng * wdth;
    while (area > 0) {
      squares.add(Math.min(lng, wdth));
      if (wdth > lng) wdth = wdth - lng;
      else lng = lng - wdth;
      area = lng * wdth;
    }
    return squares;
  }

  public static void main(String[] args) {
    int lng = 5;
    int wdth = 5;
    SqInRect.sqInRect(lng, wdth).forEach(System.out::println);
  }
}
