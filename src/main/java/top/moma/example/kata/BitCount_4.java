package top.moma.example.kata;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BitCount_4
 *
 * <p>https://www.codewars.com/kata/596d34df24a04ee1e3000a25/train/java
 *
 * <p>Given two numbers: 'left' and 'right' (1 <= 'left' <= 'right' <= 200000000000000) return sum
 * of all '1' occurencies in binary representations of numbers between 'left' and 'right' (including
 * both)
 *
 * <p>Example: countOnes 4 7 should return 8, because:<br>
 * 4(dec) = 100(bin), which adds 1 to the result. <br>
 * 5(dec) = 101(bin), which adds 2 to the result. <br>
 * 6(dec) = 110(bin), which adds 2 to the result. <br>
 * 7(dec) = 111(bin), which adds 3 to the result. <br>
 * 8 = 1000 -- 1 9 = 1001 -- 2 10 = 1010 -- 2 11 = 1011 -- 3 12 = 1100 -- 2 13 = 1101 -- 3 14 = 1110
 * -- 3 15 = 1111 -- 4
 *
 * <p>16 = 10000 -- 1 17 = 10001 -- 2 18 = 10010 -- 2 19 = 10011 -- 3 20 = 10100 -- 2 21 = 10101 --
 * 3 22 = 10110 -- 3 23 = 10111 -- 4 24 = 11000 -- 2 25 = 11001 -- 3 26 = 11010 -- 3 27 = 11011 -- 4
 * 28 = 11100 -- 3 29 = 11101 -- 4 30 = 11110 -- 4 31 = 11111 -- 5
 *
 * <p>So finally result equals 8. WARNING: Segment may contain billion elements, to pass this kata,
 * your solution cannot iterate through all numbers in the segment!
 *
 * @version 1.0
 * @author Created by ivan at 14:46.
 */
public class BitCount_4 {

  public static BigInteger countOnes(long left, long right) {
    BigInteger result = BigInteger.ZERO;
    for (long i = left; i <= right; i++) {
      result = result.add(countOnes2(i));
    }
    return result;
  }
  /**
   * 这种方法速度比较快，其运算次数与输入n的大小无关，只与n中1的个数有关。如果n的二进制表示中有k个1，那么这个方法只需要循环k次即可。其原理是不断清除n的二进制表示中最右边的1，同时累加计数器，直至n为0
   * 为什么n &= (n – 1)能清除最右边的1呢？因为从二进制的角度讲，n相当于在n - 1的最低位加上1。举个例子，8（1000）= 7（0111）+ 1（0001），所以8 & 7 =
   * （1000）&（0111）= 0（0000），清除了8最右边的1（其实就是最高位的1，因为8的二进制中只有一个1）。再比如7（0111）= 6（0110）+ 1（0001），所以7 & 6
   * = （0111）&（0110）= 6（0110），清除了7的二进制表示中最右边的1（也就是最低位的1）
   *
   * @param num num
   * @return java.math.BigInteger
   * @author Created by ivan
   * @since 2022/10/21 16:44
   */
  public static BigInteger countOnes(long num) {
    long count = 0;
    while (num != 0) {
      count += num & 0x01;
      num = num >> 1;
    }
    return new BigInteger(count + "");
  }

  public static BigInteger countOnes2(long num) {
    // write your code here
    long sum = 0;
    char[] cs = Long.toBinaryString(num).toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if (cs[i] == '1') {
        sum++;
      }
    }
    return new BigInteger(sum + "");
  }

  @Test
  public void sampleTests() {
    assertEquals(new BigInteger("7"), BitCount_4.countOnes(5, 7));
    assertEquals(new BigInteger("51"), BitCount_4.countOnes(12, 29));
  }
}
