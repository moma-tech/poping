package top.moma.example.kata.med;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Runes_4
 *
 * <p>https://www.codewars.com/kata/546d15cebed2e10334000ed9/train/java
 *
 * <p>To give credit where credit is due: This problem was taken from the ACMICPC-Northwest Regional
 * Programming Contest. Thank you problem writers.
 *
 * <p>You are helping an archaeologist decipher some runes. He knows that this ancient society used
 * a Base 10 system, and that they never start a number with a leading zero. He's figured out most
 * of the digits as well as a few operators, but he needs your help to figure out the rest.
 *
 * <p>The professor will give you a simple math expression, of the form
 *
 * <p>[number][op][number]=[number] He has converted all of the runes he knows into digits. The only
 * operators he knows are addition (+),subtraction(-), and multiplication (*), so those are the only
 * ones that will appear. Each number will be in the range from -1000000 to 1000000, and will
 * consist of only the digits 0-9, possibly a leading -, and maybe a few ?s. If there are ?s in an
 * expression, they represent a digit rune that the professor doesn't know (never an operator, and
 * never a leading -). All of the ?s in an expression will represent the same digit (0-9), and it
 * won't be one of the other given digits in the expression. No number will begin with a 0 unless
 * the number itself is 0, therefore 00 would not be a valid number.
 *
 * <p>Given an expression, figure out the value of the rune represented by the question mark. If
 * more than one digit works, give the lowest one. If no digit works, well, that's bad news for the
 * professor - it means that he's got some of his runes wrong. output -1 in that case.
 *
 * <p>Complete the method to solve the expression to find the value of the unknown rune. The method
 * takes a string as a paramater repressenting the expression and will return an int value
 * representing the unknown rune or -1 if no such rune exists.
 *
 * @version 1.0
 * @author Created by ivan at 15:43.
 */
public class Runes_4 {
  public static final String RUNE = "\\?";

  public static int solveExpression(final String expression) {
    int missingDigit = -1;
    char[] exps = expression.toCharArray();
    int equalP = -1;
    int opP = -1;
    int addOp = -1;
    int minOp = -1;
    int mulOp = -1;
    int[] ranges = new int[10];
    for (int i = 0; i < exps.length; i++) {
      Character d = exps[i];
      if (d == '=') {
        equalP = i;
      } else if (d == '+') {
        opP = i;
        addOp = i;
      } else if (d == '-') {
        if (minOp == -1 && opP == -1 && i != 0) {
          opP = i;
          minOp = i;
        }
      } else if (d == '*') {
        opP = i;
        mulOp = i;
      } else if (d == '?') {
      } else {
        String po = d.toString();
        ranges[Integer.valueOf(po)]++;
      }
    }
    String leftFirst = expression.substring(0, opP);
    String leftSecond = expression.substring(opP + 1, equalP);
    String rightP = expression.substring(equalP + 1);
    String lFirstS, lSecondS, rResultS;
    Integer lFirst, lSecond, rResult;
    for (int rune = 0; rune < 10; rune++) {
      if (ranges[rune] > 0) {
        continue;
      }
      lFirstS = leftFirst.replaceAll(RUNE, rune + "");
      lSecondS = leftSecond.replaceAll(RUNE, rune + "");
      rResultS = rightP.replaceAll(RUNE, rune + "");
      lFirst = Integer.valueOf(lFirstS);
      lSecond = Integer.valueOf(lSecondS);
      rResult = Integer.valueOf(rResultS);
      if (lFirstS.length() != lFirst.toString().length()
          || lSecondS.length() != lSecond.toString().length()
          || rResult.toString().length() != rResultS.length()) {
        continue;
      }

      if (addOp > 0) {
        if (addOp(lFirst, lSecond) == rResult) {
          missingDigit = rune;
          break;
        }
      }
      if (minOp > 0) {
        if (subOp(lFirst, lSecond) == rResult) {
          missingDigit = rune;
          break;
        }
      }
      if (mulOp > 0) {
        if (mulOp(lFirst, lSecond) == rResult) {
          missingDigit = rune;
          break;
        }
      }
    }

    // Write code to determine the missing digit or unknown rune
    // Expression will always be in the form
    // (number)[opperator](number)=(number)
    // Unknown digit will not be the same as any other digits used in expression

    return missingDigit;
  }

  public static int addOp(Integer first, Integer second) {
    return first + second;
  }

  public static int subOp(Integer first, Integer second) {
    return first - second;
  }

  public static long mulOp(Integer first, Integer second) {
    return first.intValue() * second.intValue();
  }

  private static boolean thisIsTrue(final String expr) {
    final Pattern p =
        Pattern.compile(
            "(-?+[0-9]+)([*+-])(-?+[0-9]+)=(.*)"); // Expr always has form: (num)[op](num)=(num)
    final Matcher m = p.matcher(expr);
    if (m.matches()) {
      final String a = m.group(1), b = m.group(3), c = m.group(4);
      if (a.matches("-*0.+") || b.matches("-*0.+") || c.matches("-*0.+"))
        return false; // leading zeroes not allowed
      final int ai = Integer.valueOf(a), bi = Integer.valueOf(b), ci = Integer.valueOf(c);
      switch (m.group(2)) {
        case "+":
          return ai + bi == ci;
        case "-":
          return ai - bi == ci;
        case "*":
          return ai * bi == ci;
      }
    }
    return false;
  }

  public static int solveExpression2(final String expression) {
    for (int i = 0; i < 10; i++) {
      if (expression.contains("" + i))
        continue; // unknown digit will not be the same as any other digits
      if (thisIsTrue(expression.replace("?", "" + i))) return i;
    }
    return -1;
  }

  public static void main(String[] args) {
    String expression = "-?56373--9216=-?47157";
    System.out.println(solveExpression(expression));
  }
}
