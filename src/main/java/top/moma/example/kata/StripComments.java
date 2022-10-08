package top.moma.example.kata;

/**
 * StripComments
 *
 * <p>Complete the solution so that it strips all text that follows any of a set of comment markers
 * passed in. Any whitespace at the end of the line should also be stripped out.
 *
 * <p>Example:
 *
 * <p>Given an input string of:
 *
 * <p>apples, pears # and bananas
 *
 * <p>grapes
 *
 * <p>bananas !apples
 *
 * <p>The output expected would be:
 *
 * <p>apples, pears
 *
 * <p>grapes
 *
 * <p>bananas
 *
 * <p>The code would be called like so:
 *
 * <p>var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
 * </br>// result should == "apples, pears\ngrapes\nbananas"
 *
 * @version 1.0
 * @author Created by ivan at 17:57.
 */
public class StripComments {
  public static String stripComments(String text, String[] commentSymbols) {
    String[] lines = text.split("\n");
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
      for (String comment : commentSymbols) {
        int j = line.indexOf(comment.trim());
        if (j > 0) {
          line = line.substring(0, j).trim();
        }
        if (j == 0) {
          break;
        }
      }
      stringBuilder.append(line).append("\n");
    }

    return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
  }

  public static void main(String[] args) {
    String text = "apples, pears # and bananas\ngrapes\nbananas !apples";
    String[] commentSymbols = new String[] {"#", "!"};
    System.out.println(StripComments.stripComments(text, commentSymbols));
  }
}
