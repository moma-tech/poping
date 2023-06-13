package top.moma.example.cipher;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 * HmacSHA512
 *
 * @version 1.0
 * @author Created by ivan at 11:28.
 */
@Slf4j
public class HmacSHA512 {
  private static final String HMAC_SHA512 = "HmacSHA512";
  private static final String SHA512_DEF_KEY = "MOMA_SHA_KEY";

  /**
   * 默认Key加密 hmacSHA512
   *
   * @param data 原文
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:28
   */
  public static String hmacSHA512(String data)
      throws NoSuchAlgorithmException, InvalidKeyException {
    return hmacSHA512(data, SHA512_DEF_KEY);
  }

  /**
   * 计算SHA512-sign hmacSHA512
   *
   * @param data 原文
   * @param key 密钥
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/2 12:18
   */
  public static String hmacSHA512(String data, String key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec secretKeySpec =
        new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA512);
    Mac mac = Mac.getInstance(HMAC_SHA512);
    mac.init(secretKeySpec);
    return toHexString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
  }

  /**
   * Byte数组转String toHexString
   *
   * @param bytes bytes
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 12:18
   */
  private static String toHexString(byte[] bytes) {
    String result;
    try (Formatter formatter = new Formatter()) {
      for (byte b : bytes) {
        formatter.format("%02x", b);
      }
      result = formatter.toString();
    }
    return result;
  }

  private HmacSHA512() {
    throw new IllegalStateException("Utility class");
  }
}
