package top.moma.example.cipher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class HmacSHA512Test {

  public static final String data = "test message";
  public static final String key = "test key";

  @Test
  void sign() {
    String sign = HmacSHA512.sign(data);
    log.info("Sign is: {}", sign);
    Assertions.assertEquals(128,sign.length());
    String sign2 = HmacSHA512.sign(data);
    log.info("Sign2 is: {}", sign2);
    Assertions.assertEquals(sign, sign2);
    try {
      String sign3 = HmacSHA512.sign(data, key);
      log.info("Sign3 is: {}", sign3);
      Assertions.assertNotEquals(sign, sign3);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      e.printStackTrace();
    }
  }
}
