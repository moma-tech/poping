package top.moma.example.kata;

/**
 * Given a set of numbers, return the additive inverse of each. Each positive becomes negatives, and
 * the negatives become positives.
 *
 * <p>invert([1,2,3,4,5]) == [-1,-2,-3,-4,-5] invert([1,-2,3,-4,5]) == [-1,2,-3,4,-5] invert([]) ==
 * []
 */
public class InvertInt {
  public static int[] invert(int[] array) {

    int[] output = new int[array.length];
    for (int a = 0; a < array.length; a++) {
      output[a] = 0 - array[a];
    }
    return output;
  }

  public static int[] invert2(int[] array) {
    return java.util.Arrays.stream(array).map(i -> -i).toArray();
  }

  public static int[] invert3(int[] array) {

    for (int i = 0; i < array.length; i++) {
      array[i] *= -1;
    }

    return array;
  }
}
