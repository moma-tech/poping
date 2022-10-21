package top.moma.example.kata.low;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010) and After
 * Earth (2013). Jaden is also known for some of his philosophy that he delivers via Twitter. When
 * writing on Twitter, he is known for almost always capitalizing every word. For simplicity, you'll
 * have to capitalize each word, check out how contractions are expected to be in the example below.
 *
 * <p>Your task is to convert strings to how they would be written by Jaden Smith. The strings are
 * actual quotes from Jaden Smith, but they are not capitalized in the same way he originally typed
 * them.
 *
 * <p>Example:
 *
 * <p>Not Jaden-Cased: "How can mirrors be real if our eyes aren't real" Jaden-Cased: "How Can
 * Mirrors Be Real If Our Eyes Aren't Real"
 */
public class JadenCase_7 {
  public String toJadenCase(String phrase) {

    return null == phrase || "".equals(phrase)
        ? null
        : Arrays.stream(phrase.split(" "))
            .map(
                word -> {
                  char[] ws = word.toCharArray();
                  ws[0] = String.valueOf(ws[0]).toUpperCase().charAt(0);
                  return String.valueOf(ws);
                })
            .collect(Collectors.joining(" "));
  }

  public String toJadenCase2(String phrase) {

    return null == phrase || "".equals(phrase)
        ? null
        : Arrays.stream(phrase.split(" "))
            .map(
                word -> {
                  char[] ws = word.toCharArray();
                  ws[0] = Character.toUpperCase(ws[0]);
                  return String.valueOf(ws);
                })
            .collect(Collectors.joining(" "));
  }

  public String toJadenCase3(String phrase) {
    if (null == phrase || phrase.length() == 0) {
      return null;
    }

    return Arrays.stream(phrase.split(" "))
        .map(i -> i.substring(0, 1).toUpperCase() + i.substring(1, i.length()))
        .collect(Collectors.joining(" "));
  }

  public String toJadenCase4(String phrase) {
    if (phrase == null || phrase.equals("")) return null;

    char[] array = phrase.toCharArray();

    for (int x = 0; x < array.length; x++) {
      if (x == 0 || array[x - 1] == ' ') {
        array[x] = Character.toUpperCase(array[x]);
      }
    }

    return new String(array);
  }
}
