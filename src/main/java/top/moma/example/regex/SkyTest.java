package top.moma.example.regex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Pattern;

public class SkyTest {
  public static void main(String[] args) {
    try {
      FileReader fileReader = new FileReader("./yaml/test.yaml");
      YmalReader ymalReader = new YmalReader(fileReader);
      String reg = ymalReader.readRules();
      Pattern p;
      p = Pattern.compile(reg);
      String input =
          "aaacoreipb.MicroLoanCoreService.getOrdersLeftTotalAmount/server/Request/onComplete in core-service::mx-np-collection-service";
      System.out.println(p.matcher(input).matches());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
