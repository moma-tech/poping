package top.moma.example.kata;

import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

/**
 * TopWords
 *
 * <p>Write a function that, given a string of text (possibly with punctuation and line-breaks),
 * returns an array of the top-3 most occurring words, in descending order of the number of
 * occurrences.
 *
 * <p>Assumptions: <br>
 * A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in
 * ASCII. <br>
 * Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all
 * valid) <br>
 * Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as
 * whitespace. <br>
 * Matches should be case-insensitive, and the words in the result should be lowercased. <br>
 * Ties may be broken arbitrarily. <br>
 * If a text contains fewer than three unique words, then either the top-2 or top-1 words should be
 * returned, or an empty array if a text contains no words. Examples: top_3_words("In a village of
 * La Mancha, the name of which I have no desire to call to mind, there lived not long since one of
 * those gentlemen that keep a lance in the lance-rack, an old buckler, a lean hack, and a greyhound
 * for coursing. An olla of rather more beef than mutton, a salad on most nights, scraps on
 * Saturdays, lentils on Fridays, and a pigeon or so extra on Sundays, made away with three-quarters
 * of his income.") # => ["a", "of", "on"]
 *
 * <p>top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e") # => ["e", "ddd", "aa"]
 *
 * <p>top_3_words(" //wont won't won't") # => ["won't", "wont"] For java users, the calls will
 * actually be in the form: TopWords.top3(String s), expecting you to return a List<String>.
 *
 * <p>Bonus points (not really, but just for fun): <br>
 * Avoid creating an array whose memory footprint is roughly as big as the input text. <br>
 * Avoid sorting the entire array of unique words.
 *
 * @version 1.0
 * @author Created by ivan at 14:39.
 */
public class TopWords {
  public static List<String> top3(String s) {
    // Your code here
    List<String> tops_word;
    Map<String, Integer> records = new HashMap<>();
    String are = "[a-z']+";
    Pattern p = Pattern.compile(are);
    Matcher m = p.matcher(s.toLowerCase());
    String key;
    while (m.find()) {
      key = m.group();
      if (!key.matches(".*[a-z]+.*")) {
        continue;
      }
      if (records.containsKey(key)) {
        records.put(key, records.get(key) + 1);
      } else {
        records.put(key, 1);
      }
    }
    tops_word =
        records.entrySet().stream()
            .sorted(comparingByValue(Comparator.reverseOrder()))
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(toList());

    return tops_word;
  }

  public static List<String> top32(String s) {
    return Arrays.stream(s.toLowerCase().split("[^a-z*|']"))
        .filter(o -> !o.isEmpty() && !o.replace("'", "").isEmpty())
        .collect(groupingBy(Function.identity(), counting()))
        .entrySet()
        .stream()
        .sorted(reverseOrder(comparingByValue()))
        .map(Map.Entry::getKey)
        .limit(3)
        .collect(toList());
  }

  public static List<String> top33(final String s) {
    return new Scanner(s.toLowerCase())
            .findAll("'?[a-z]['a-z]*")
            .map(MatchResult::group)
            .collect(groupingBy(identity(), counting()))
            .entrySet()
            .stream()
            .sorted(comparingByValue(reverseOrder()))
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(toList());
  }

  public static void main(String[] args) {
    // Arrays.asList("a", "of", "on")
    String input =
        Stream.of(
                "In a village of La Mancha, the name of which I have no desire to call to",
                "mind, there lived not long since one of those gentlemen that keep a lance",
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
                "coursing. An olla of rather more beef than mutton, a salad on most",
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
                "on Sundays, made away with three-quarters of his income.")
            .collect(Collectors.joining("\n"));
    // TopWords.top3(input);
    // Arrays.asList("e", "ddd", "aa")
    String input2 = "e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e";
    TopWords.top3(input2).stream().forEach(System.out::println);
    // Arrays.asList("won't", "wont")
    String input3 = "  //wont won't won't ";

    //    String some = "  //wont won't won't ";
    //    String are = "[a-z']+";
    //    Pattern s = Pattern.compile(are);
    //    Matcher m = s.matcher(some);
    //    while (m.find()) {
    //      System.out.println(m.group());
    //    }
  }
}
