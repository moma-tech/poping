package top.moma.example.kata;

import java.util.Locale;

public class MakeUpperCase {
  public static String MakeUpperCase(String str) {
    return str.toUpperCase(Locale.ROOT);
  }

  public static String MakeUpperCase2(String str) {
    StringBuffer s = new StringBuffer();
    int Anumber = 97;
    int Znumber = 122;
    int len = str.length();
    int i = 0;
    for (i = 0; i < len; i++) {
      int asciiNumber = (int) str.charAt(i);
      int asciiNumberUpper = asciiNumber;
      if (asciiNumber >= Anumber && asciiNumber <= Znumber) {
        asciiNumberUpper = asciiNumber - 32;
      }
      s.append((char) asciiNumberUpper);
    }
    return s.toString();
  }
}
