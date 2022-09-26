package top.moma.example.kata;

public class ColorTriangle {

  public static String triangle(String firstRow) {
    String result = firstRow;
    while (result.length() > 1) {
      StringBuffer stringBuffer = new StringBuffer();
      for (int i = 0; i < result.length() - 1; i++) {
        stringBuffer.append(single(firstRow.charAt(i) + "", firstRow.charAt(i + 1) + ""));
      }
      result = stringBuffer.toString();
    }
    return result;
  }

  public static String single(String a, String b) {
    if (a.equals(b)) {
      return a;
    }
    String cont = a + b;
    if (cont.contains("R") && cont.contains("G")) {
      return "B";
    }
    if (cont.contains("R") && cont.contains("B")) {
      return "G";
    }
    return "R";
  }

  public static void main(String[] args) {
    String firstRow = "B";
    System.out.println(ColorTriangle.triangle(firstRow));
  }
}
