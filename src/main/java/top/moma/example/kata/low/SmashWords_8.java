package top.moma.example.kata.low;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SmashWords_8 {

  public static String smash(String... words) {
    // TODO Write your code below this comment please
    StringBuilder stringBuilder = new StringBuilder();
    Arrays.stream(words)
        .forEach(
            word -> {
              stringBuilder.append(word).append("_");
            });
    return stringBuilder.toString().trim();
  }

  public static String smash2(String... words) {
    return String.join(" ", words);
  }

  public static String smash3(String... words) {
    return Arrays.stream(words).collect(Collectors.joining(" "));
  }
}
