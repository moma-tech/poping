package top.moma.example.kata;

import java.util.List;
import java.util.stream.Collectors;

/**
 * In this kata you will create a function that takes a list of non-negative integers and strings
 * and returns a new list with the strings filtered out.
 *
 * <p>Example Kata.filterList(List.of(1, 2, "a", "b")) => List.of(1,2) Kata.filterList(List.of(1, 2,
 * "a", "b", 0, 15)) => List.of(1,2,0,15) Kata.filterList(List.of(1, 2, "a", "b", "aasf", "1",
 * "123", 231)) => List.of(1, 2, 231)
 */
public class FilterList {

  public static List filterList(final List<Object> list) {
    // Return the List with the Strings filtered out

    return list.stream()
        .filter(
            obj -> {
              return obj instanceof Integer;
            })
        .collect(Collectors.toList());
  }
}
