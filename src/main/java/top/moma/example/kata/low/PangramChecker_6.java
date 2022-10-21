package top.moma.example.kata.low;

import java.util.stream.IntStream;

/**
 * PangramChecker_6
 *
 * <p>A pangram is a sentence that contains every single letter of the alphabet at least once. For
 * example, the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses
 * the letters A-Z at least once (case is irrelevant).
 *
 * <p>Given a string, detect whether or not it is a pangram. Return True if it is, False if not.
 * Ignore numbers and punctuation.
 *
 * @version 1.0
 * @author Created by ivan at 15:52.
 */
public class PangramChecker_6 {

  public static boolean check(String sentence) {
    for (int i = 97; i <= 122; i++) {
      IntStream intStream = sentence.toLowerCase().chars().sorted();
      int finalI = i;
      if (intStream.parallel().noneMatch(s -> s == finalI)) {
        return false;
      }
    }
    return true;
  }

  public boolean check2(String sentence) {
    for (char c = 'a'; c <= 'z'; c++) if (!sentence.toLowerCase().contains("" + c)) return false;
    return true;
  }

  public static void main(String[] args) {
    String a = "The quick brown fox jumps over the lazy dog";
    System.out.println(PangramChecker_6.check(a));
  }
}
