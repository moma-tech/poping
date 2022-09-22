package top.moma.example.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * AreSame
 *
 * <p>Given two arrays a and b write a function comp(a, b) (orcompSame(a, b)) that checks whether
 * the two arrays have the "same" elements, with the same multiplicities (the multiplicity of a
 * member is the number of times it appears). "Same" means, here, that the elements in b are the
 * elements in a squared, regardless of the order.
 *
 * <p>Examples Valid arrays a = [121, 144, 19, 161, 19, 144, 19, 11] b = [121, 14641, 20736, 361,
 * 25921, 361, 20736, 361] comp(a, b) returns true because in b 121 is the square of 11, 14641 is
 * the square of 121, 20736 the square of 144, 361 the square of 19, 25921 the square of 161, and so
 * on. It gets obvious if we write b's elements in terms of squares:
 *
 * <p>a = [121, 144, 19, 161, 19, 144, 19, 11] b = [11*11, 121*121, 144*144, 19*19, 161*161, 19*19,
 * 144*144, 19*19] Invalid arrays If, for example, we change the first number to something else,
 * comp is not returning true anymore:
 *
 * <p>a = [121, 144, 19, 161, 19, 144, 19, 11] b = [132, 14641, 20736, 361, 25921, 361, 20736, 361]
 * comp(a,b) returns false because in b 132 is not the square of any number of a.
 *
 * <p>a = [121, 144, 19, 161, 19, 144, 19, 11] b = [121, 14641, 20736, 36100, 25921, 361, 20736,
 * 361] comp(a,b) returns false because in b 36100 is not the square of any number of a.
 *
 * <p>Remarks a or b might be [] or {} (all languages except R, Shell). a or b might be nil or null
 * or None or nothing (except in C++, COBOL, Crystal, D, Dart, Elixir, Fortran, F#, Haskell, Nim,
 * OCaml, Pascal, Perl, PowerShell, Prolog, PureScript, R, Racket, Rust, Shell, Swift). If a or b
 * are nil (or null or None, depending on the language), the problem doesn't make sense so return
 * false.
 *
 * <p>Note for C The two arrays have the same size (> 0) given as parameter in function comp.
 *
 * @version 1.0
 * @author Created by ivan at 16:28.
 */
public class AreSame {
  public static boolean comp(int[] a, int[] b) {
    if (null == a || null == b || a.length != b.length) {
      return false;
    }
    String list =
        Arrays.stream(a)
            .parallel()
            .mapToObj(i -> i * i)
            .sorted()
            .map(i -> i + "")
            .collect(Collectors.joining(","));

    String list2 =
        Arrays.stream(b).sorted().parallel().mapToObj(i -> i + "").collect(Collectors.joining(","));

    if (list.equals(list2)) {
      return true;
    }
    return false;
  }

  public static boolean comp2(int[] a, int[] b) {
    if (null == a || null == b || a.length != b.length) {
      return false;
    }
    int[] temp = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      temp[i] = a[i] * a[i];
    }
    Arrays.sort(b);
    Arrays.sort(temp);
    return Arrays.equals(b, temp);
//    for (int i = 0; i < b.length; i++) {
//      if (b[i] != temp[i]) {
//        return false;
//      }
//    }
//    return true;
  }
}
