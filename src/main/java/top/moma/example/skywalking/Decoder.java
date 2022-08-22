package top.moma.example.skywalking;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decoder {

  public static void main(String[] args) {
    String content = "QmFsYW5jZXIvcHVzaC9zZW5k";
    String a = new String(Base64.getDecoder().decode(content), StandardCharsets.UTF_8);
    System.out.println(a);
  }
}
