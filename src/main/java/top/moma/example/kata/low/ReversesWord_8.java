package top.moma.example.kata.low;

/**
 * Complete the solution so that it reverses the string passed into it.
 *
 * <p>'world' => 'dlrow' 'word' => 'drow'
 */
public class ReversesWord_8 {

  public static String solution(String str) {
    // Your code here...
    char[] chars = str.toCharArray();
    char temp = 'a';
    int length = chars.length - 1;
    for (int i = 0; i < chars.length / 2; i++) {
      temp = chars[i];
      chars[i] = chars[length - i];
      chars[length - i] = temp;
    }

    return String.valueOf(chars);
  }

  public static String solution2(String str) {
    return new StringBuilder(str).reverse().toString();
  }
}
