package top.moma.example.kata;

import java.math.BigInteger;
import java.util.Arrays;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * SumFct
 *
 * <p>The drawing shows 6 squares the sides of which have a length of 1, 1, 2, 3, 5, 8. It's easy to
 * see that the sum of the perimeters of these squares is : 4 * (1 + 1 + 2 + 3 + 5 + 8) = 4 * 20 =
 * 80
 *
 * <p>Could you give the sum of the perimeters of all the squares in a rectangle when there are n +
 * 1 squares disposed in the same manner as in the drawing:
 *
 * <p>alternative text
 *
 * <p>Hint: See Fibonacci sequence
 *
 * <p>Ref: http://oeis.org/A000045
 *
 * <p>The function perimeter has for parameter n where n + 1 is the number of squares (they are
 * numbered from 0 to n) and returns the total perimeter of all the squares.
 *
 * <p>perimeter(5) should return 80 perimeter(7) should return 216
 *
 * @version 1.0
 * @author Created by ivan at 12:00.
 */
public class SumFct {

  public static BigInteger perimeter(BigInteger n) {
    // your code
    BigInteger start = new BigInteger("1");
    BigInteger next = new BigInteger("1");
    BigInteger[] digis = new BigInteger[n.intValue() + 1];
    for (int i = 0; i <= n.intValue(); i++) {
      digis[i] = start;
      start = next;
      next = digis[i].add(next);
    }

    return new BigInteger(
        Arrays.stream(digis)
                .reduce(new BigInteger("0"), (integer, bigInteger) -> integer.add(bigInteger))
                .multiply(new BigInteger("4"))
            + "");
  }

  public static BigInteger perimeter2(BigInteger n) {

    BigInteger a = ZERO;
    BigInteger b = ONE;
    BigInteger c = ONE;
    BigInteger sum = ZERO;

    for (int i = 0; i <= n.intValue(); i++) {
      a = b;
      b = c;
      c = a.add(b);
      sum = sum.add(a);
    }

    return sum.multiply(BigInteger.valueOf(4));
  }

  /**
   * Helper class for {@link #fastPerimeter}.
   *
   * <p>Represents a 2x2 Matrix capable of fast exponentiation (m^n in log n matrix
   * multiplications).
   */
  private static class Matrix2x2 {
    private static final Matrix2x2 identity =
        new Matrix2x2(new BigInteger[] {ONE, ZERO, ZERO, ONE});

    public final BigInteger[] elements;

    public Matrix2x2(BigInteger[] elements) {
      this.elements = elements; // No need for copy because BigInteger is immutable.
    }

    private static Matrix2x2 multiply(Matrix2x2 m1, Matrix2x2 m2) {
      final BigInteger[] a = m1.elements;
      final BigInteger[] b = m2.elements;
      return new Matrix2x2(
          new BigInteger[] {
            a[0].multiply(b[0]).add(a[1].multiply(b[2])),
                a[0].multiply(b[1]).add(a[1].multiply(b[3])),
            a[2].multiply(b[0]).add(a[3].multiply(b[2])),
                a[2].multiply(b[1]).add(a[3].multiply(b[3]))
          });
    }

    public static Matrix2x2 exponentiate(Matrix2x2 m, long exponent) {
      if (exponent == 0) {
        return identity;
      } else if (exponent % 2 == 0) {
        return exponentiate(multiply(m, m), exponent / 2);
      } else {
        return multiply(exponentiate(m, exponent - 1), m);
      }
    }
  }

  /**
   * Uses the fact that the sum of the n first Fibonacci numbers is the (n+2) Fibonacci number minus
   * one. This can be proven inductively but is not proven here.
   *
   * <p>Then applies a fast matrix exponentiation algorithm for finding the nth Fibonacci number.
   *
   * <p>Runs in O(log n) matrix multiplications but see time complexity notes on {@link #perimeter}.
   * The multiplications run in superlinear time in number of digits, depending on BigInteger's
   * multiplication algorithm.
   */
  public static BigInteger perimeter3(BigInteger n) {
    // m * v = w
    // | a b | * | e | = | f     |
    // | c d |   | f |   | e + f |

    // m =
    // | 0 1 |
    // | 1 1 |
    final Matrix2x2 m = new Matrix2x2(new BigInteger[] {ZERO, ONE, ONE, ONE});
    // Find fib(n) = first element of (m ^ n) * w
    final Matrix2x2 me = Matrix2x2.exponentiate(m, n.longValue() + 2);
    // Note that me_2_2 is multiplied by 1 to get the first element of the Fibonacci vector (w)
    // above.
    // This means we do not need to multiply our exponentiated matrix by a vector to get the nth fib
    // number.
    final BigInteger fibSum = me.elements[3].subtract(ONE);
    return fibSum.multiply(BigInteger.valueOf(4));
  }

  public static void main(String[] args) {
    System.out.println(SumFct.perimeter(new BigInteger("300")));
    System.out.println(SumFct.perimeter2(new BigInteger("300")));
  }
}
