package top.moma.example.kata.high;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * NextSmaller_3
 *
 * <p>https://www.codewars.com/kata/5659c6d896bc135c4c00021e
 *
 * <p>Write a function that takes a positive integer and returns the next smaller positive integer
 * containing the same digits.
 *
 * <p>For example:
 *
 * <p>nextSmaller(21) == 12 <br>
 * nextSmaller(531) == 513 <br>
 * nextSmaller(2071) == 2017 <br>
 *
 * <p>Return -1 (for Haskell: return Nothing, for Rust: return None), when there is no smaller
 * number that contains the same digits. Also return -1 when the next smaller number with the same
 * digits would require the leading digit to be zero.
 *
 * <p>nextSmaller(9) == -1 <br>
 * nextSmaller(111) == -1 <br>
 * nextSmaller(135) == -1 <br>
 * nextSmaller(1027) == -1 // 0721 is out since we don't write numbers with leading zeros
 *
 * <p>some tests will include very large numbers. test data only employs positive integers.
 *
 * @version 1.0
 * @author Created by ivan at 10:54.
 */
public class NextSmaller_3 {
  /**
   * description nextSmaller
   *
   * <p>513 351 315 153 135 2071 1207 414 100 123456789 29009 1234567908 9999999999 59884848483559
   * 1023456789 51226262651257 202233445566 506789
   *
   * <p>25 40 1050 56100 319328 154055879876101472 633704 1624866095424461568 43576651300619
   * 18321772823560 2506277254879 1884 7357 40206 260789009201568 2110275654668276 1454 235423007
   * 119913519809025472 12847 921970609506 3806264955571868 76554 126849 640842912911 1062
   * 12959485451 108788092131995 333042121 66 4913 67643255260053 15078263266352 10473863
   * 712324217796135680 20255 1761507423 237402 17267575110560 2484555495646052352
   * 265723153365898208 1099617832 479376519406 26036 6713399050619 3 173784989 531609516
   * 334818078782342464 4660680 510517241428372800 4222384419 2806247416627532800 121185
   * 1544932351513762 3840480370 61122 201347971 2230942279525048320 21425614226574 442968213
   * 28747132299974928 62 50 571107 34060478704162 9802383957 15953908694385 30 2612005
   * 497016131470819520 22127 11893 55 5724307 5957926763316 3 508665 184648 14748212 40477415 7
   * 1225996566 28617126 1111958412662 154490637296306048 159226789683 13652 987 88012791244263
   * 40103386452443456 44535 16059 16194 1001 26173458257261 193551852327 3198077523417810 18 38
   * 2287046 2 477746598363 1833836289 618198710638256 12643999293 22153 195674709778071552
   * 106718521 2054648880119894784 7 79628167952225 3131591963954913 8 245 1434787978705923328
   * 143270440750464 489225642412273088 2883051 47519 34890 8 76231519957105 111537468070 8
   * 2727729916702 479849368557 780162 2 1942070755022409472 54097140 2163 6112371 27926093
   * 4003852801153550336 153503046673 3 555599396 16 9 4 993 89030114583492 1703 39324076994097
   * 8739213609217985 213634787711 1129286927027872 27833668033467 13657434325 2 1092505 55994712
   * 15500475207950886 31567664203208 103421483998692 196813404210188640 3296322 2855072
   * 28878148356876156 11971691 4260088491390797 62074082909 22408933 1944293494 4114637970
   * 14420526428490 199 22923657672422 1 1738 37 998405790327714816 4 316853907399490944 4898281970
   * 15527376817380 1485751387375786 9151024 44695 200403353577976 14332913709943318 6581973335788
   * 18217792265455700 1434814155602574336 14893 5262416868631972 23290155909503712 7481576855458 5
   * 13014 399843091219 3479605431278121 1243266838 50371699 956 24 207566192 261358265873595
   * 8025345
   *
   * @param n n
   * @return long
   * @author Created by ivan
   * @since 2022/10/10 12:29
   */
  public static long nextSmaller(long n) {
    String num = Long.valueOf(n).toString();
    String finalResult = "-1";
    for (int i = num.length() - 2; i >= 0; i--) {
      String sub = num.substring(i);
      String result = findSmaller(sub);
      if (!result.equals("-1")) {
        if (i > 0) {
          finalResult = num.substring(0, i) + result;
        } else {
          finalResult = result;
        }
        break;
      }
    }
    return finalResult.startsWith("0") ? -1 : Long.valueOf(finalResult);
  }

  private static String findSmaller(String num) {
    char[] nums = num.toCharArray();
    int pointer = -1;
    int switcher = nums.length - 1;
    while (switcher > 0) {
      char last = nums[switcher];
      for (int i = switcher; i > 0; i--) {
        if (nums[i - 1] > last) {
          pointer = i - 1;
          break;
        }
      }
      if (pointer != -1) {
        break;
      }
      switcher--;
    }
    if (pointer != -1) {
      String tail = "";
      if (switcher < num.length() - 1) {
        tail = num.substring(switcher + 1, num.length());
      }
      tail = num.substring(pointer, switcher) + tail;
      tail =
          tail.chars()
              .boxed()
              .sorted(Comparator.reverseOrder())
              .map(i -> (char) i.intValue() + "")
              .collect(Collectors.joining());
      String result;
      if (pointer == 0) {
        result = num.charAt(switcher) + tail;
      } else {
        result = num.substring(0, pointer) + num.charAt(switcher) + tail;
      }
      return result;
    }
    return "-1";
  }

  public static void main(String[] args) {
    long n = 531;
    System.out.println(NextSmaller_3.nextSmaller(n));
    long n2 = 51226262651257L;
    System.out.println(NextSmaller_3.nextSmaller(n2));
    String tail = "588";
  }
}
