package top.moma.example.cipher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.extern.slf4j.Slf4j;

/**
 * RsaHelper
 *
 * @version 1.0
 * @author Created by ivan at 11:28.
 */
@Slf4j
public class RsaHelper {

  private static final String RSA_CIPHER = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
  private static final String RSA_SIGN_CIPHER = "SHA256withRSA";
  public static final String RSA_PUBLIC_KEY =
      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzAknkiaQoddb/hG6vzo8bCPAXOduih17wGn86CBVV88I5ZFm2aAqEhfn36UAdHgOf/s15qMisbot2Usz3r6EzOjC6Ia65N+Jvj6y2PKaY8QC1K5mjqfAx5zJTOTY82WmNMwSv7R78iF6/ObxHjkpvk5ExVnHWqiBk2Id5G1eX5CRt+wrmO32Vcq5qPGGZUMaxXw5OdPvZJwQZJ1fhwhfDBLvDUP4pwqxOxOJ0md15smB2B3Jwsw4k8WKLvZY+EBpfaYqEE1flXraj6Cc56CWMPn3S3OiK/UkZH6L4LgdNO7eMow1jvteQHXuwi3vBJiTS14M3yas5TTZuic4Um97cwIDAQAB";
  public static final String RSA_PRIVATE_KEY =
      "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDMCSeSJpCh11v+Ebq/OjxsI8Bc526KHXvAafzoIFVXzwjlkWbZoCoSF+ffpQB0eA5/+zXmoyKxui3ZSzPevoTM6MLohrrk34m+PrLY8ppjxALUrmaOp8DHnMlM5NjzZaY0zBK/tHvyIXr85vEeOSm+TkTFWcdaqIGTYh3kbV5fkJG37CuY7fZVyrmo8YZlQxrFfDk50+9knBBknV+HCF8MEu8NQ/inCrE7E4nSZ3XmyYHYHcnCzDiTxYou9lj4QGl9pioQTV+VetqPoJznoJYw+fdLc6Ir9SRkfovguB007t4yjDWO+15Ade7CLe8EmJNLXgzfJqzlNNm6JzhSb3tzAgMBAAECggEAHmOKqkDmL8Skpe28E7k3wJ9+ihfKJfYINXtTuLsAGwjx+Uczu1wYiANZfLzVmYM8HaGrwIMxqqjhJUkHG7jijKZqSTmv4mDM4jHyb0+K4SsThNvI2JxxoQlgDfzTt0S/gYOXk0ftYf3MlJhM90RqTDbaFU6u23jXe70UWK5VbQQV7Jt0jV+2IU7B5v2f20Pgqed6y9DVrFDfJsthe2vtCw0l6tgkziUX7a+1VGC2wQBGwre6OZ2Gn9hxxz2R607ccF3/yqvfLg0DKa8CkaNxIAn482zt2KQyjgYW5ARoD0O+MshZ4Yh5WoOtKwu6k51cX76XRBRbM3ligW7Fu2Li8QKBgQD40iwF96eWiXotus+He6qdoX3yD6XQ7t6V0Pmr5xSgy3Fpr+COAAb/wfdobm63jUWvREDNmFcVglXpuIBlaMBUo1hNuE9tBX6a/zAhzfG24NPuBV4QySg3nqYxRtifXVjoL9+BB0ZoIdy/L3pIK+jT/GXlDBJmLYb1iKaUOJ/s6wKBgQDR7DFCU5xA+yiftjrIuxOFTYhlcvFq1BcH+WlrHOLQjzOUNZnEjOUpzrlH98jtuQg/f2tzW5ZBUvmdsOKNb77SmQpSjoAJVFB0/wiJj35il4kRp+nXNodJnCdgqBs7+A9M4tjaj+Fdy2FAmf7am/Jj05lsjCVDjGp2dxMxMmvpmQKBgHl4URmQr3XkI4tTmaCwlLhjcFLNpMt88Zj97gUnyIA/EVzhCaUJCmGtVZTb5J0jEJPhpCk6Z7kOadaxxay8GLi5DZDTm6LDfe05C0xVd90poQyf/i3/peyRPNztky8pqQ+g32HkJVEMxvFmwjGdjgp/O1c4L3tGWo5facMOabSFAoGADq06zG5YEFr+/huZhIs/1CQVkzI0Gsn1SkNv0WNVoEtCyevtckZ/hyrC3Xs/ew9iuj3IX2pZ2PtaJGJHlKfpaYP1qsv3u68/aM6j5Co6Jd5+YNOij79qOgVG44UdUlYHi9KYYr+IfCxKAmBB5zrb+YrDwUkTGePpVZsBpoDl9pECgYBTtiw1E5a15gSis7fTMGTBPSd7o4UXAnnbXYPRoQZs7/yjhQzrXj/JhrvoEqR2dUh4ROyVncK7dlX7a1s78wTeasMBI+C2TrqHb1YmxVolWL+7o+8o52OnEJSQplzrjvtQYD/5iTQ59HEYXzqRtsfyZ/3XbvAx1L64r16g1VR8vw==";

  public static String generateSign(String data, String key) {
    String result = "";
    try {
      Signature signature = Signature.getInstance(RSA_SIGN_CIPHER);
      PrivateKey priKey =
          KeyFactory.getInstance("RSA")
              .generatePrivate(
                  new PKCS8EncodedKeySpec(
                      Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8))));
      signature.initSign(priKey);
      signature.update(data.getBytes(StandardCharsets.UTF_8));
      byte[] signed = signature.sign();
      result = new String(Base64.getEncoder().encode(signed), StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("RsaHelper,generateSign, error", e);
    }
    return result;
  }

  public static String[] generateKey() {
    String[] keyP = new String[2];
    KeyPairGenerator kpg;
    try {
      kpg = KeyPairGenerator.getInstance(RSA_CIPHER);
      kpg.initialize(2048);
      KeyPair keyPair = kpg.generateKeyPair();
      keyP[0] = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
      keyP[1] = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      log.error("RsaHelper generateKey Failed", e);
      keyP[0] = RSA_PUBLIC_KEY;
      keyP[1] = RSA_PRIVATE_KEY;
    }
    return keyP;
  }

  /**
   * 默认公钥加密 encryptByPubKey
   *
   * @param data 原文
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:19
   */
  public static String encryptByPubKey(String data) {
    return encryptByRSA2048PubKey(data, Base64.getDecoder().decode(RSA_PUBLIC_KEY));
  }

  /**
   * 指定公钥加密 encryptByPubKey
   *
   * @param data 原文
   * @param pubKey pubKey
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:19
   */
  public static String encryptByPubKey(String data, String pubKey) {
    return encryptByRSA2048PubKey(data, Base64.getDecoder().decode(pubKey));
  }

  /**
   * 默认公钥解密 decryptByPubKey
   *
   * @param data 密文
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:19
   */
  public static String decryptByPubKey(String data) {
    return decryptByRSA2048PubKey(data, Base64.getDecoder().decode(RSA_PUBLIC_KEY));
  }

  /**
   * 指定公钥解密 decryptByPubKey
   *
   * @param data 密文
   * @param pubKey 公钥
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String decryptByPubKey(String data, String pubKey) {
    return decryptByRSA2048PubKey(data, Base64.getDecoder().decode(pubKey));
  }

  /**
   * 默认私钥加密 encryptByPriKey
   *
   * @param data 原文
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String encryptByPriKey(String data) {
    return encryptByRSA2048PriKey(data, Base64.getDecoder().decode(RSA_PRIVATE_KEY));
  }

  /**
   * 指定私钥加密 encryptByPriKey
   *
   * @param data 原文
   * @param priKey 私钥
   * @return 密文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String encryptByPriKey(String data, String priKey) {
    return encryptByRSA2048PriKey(data, Base64.getDecoder().decode(priKey));
  }

  /**
   * 默认私钥解密 decryptByPriKey
   *
   * @param data 密文
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String decryptByPriKey(String data) {
    return decryptByRSA2048PriKey(data, Base64.getDecoder().decode(RSA_PRIVATE_KEY));
  }

  /**
   * 指定私钥解密 decryptByPriKey
   *
   * @param data 密文
   * @param priKey 私钥
   * @return 原文
   * @author Created by ivan
   * @since 2023/2/3 11:20
   */
  public static String decryptByPriKey(String data, String priKey) {
    return decryptByRSA2048PriKey(data, Base64.getDecoder().decode(priKey));
  }

  /**
   * RSA_2048公钥加密 encryptByRSA2048PubKey
   *
   * @param data 原文
   * @param decoded decoded
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:40
   */
  private static String encryptByRSA2048PubKey(String data, byte[] decoded) {
    try {
      RSAPublicKey rsaPublicKey =
          (RSAPublicKey)
              KeyFactory.getInstance(RSA_CIPHER).generatePublic(new X509EncodedKeySpec(decoded));
      return rsaEncCipher(data, rsaPublicKey);
    } catch (Exception e) {
      log.error("encryptByRSA2048PubKey error.", e);
      return null;
    }
  }

  /**
   * RSA_2048公钥解密 decryptByRSA2048PubKey
   *
   * @param cipherText cipherText
   * @param decoded decoded
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:39
   */
  private static String decryptByRSA2048PubKey(String cipherText, byte[] decoded) {
    try {
      RSAPublicKey rsaPublicKey =
          (RSAPublicKey)
              KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
      return rsaDecCipher(cipherText, rsaPublicKey);
    } catch (Exception e) {
      log.error("decryptByRSA2048PubKey error.", e);
      return null;
    }
  }

  /**
   * RSA_2048私钥加密 encryptByRSA2048PriKey
   *
   * @param cipherText cipherText
   * @param decoded decoded
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:40
   */
  private static String encryptByRSA2048PriKey(String cipherText, byte[] decoded) {
    try {
      RSAPrivateKey rsaPrivateKey =
          (RSAPrivateKey)
              KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
      return rsaEncCipher(cipherText, rsaPrivateKey);
    } catch (Exception e) {
      log.error("encryptByRSA2048PriKey error.", e);
      return null;
    }
  }

  /**
   * RSA_2048私钥解密 decryptByRSA2048PriKey
   *
   * @param cipherText cipherText
   * @param decoded decoded
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 14:40
   */
  private static String decryptByRSA2048PriKey(String cipherText, byte[] decoded) {
    try {
      RSAPrivateKey rsaPrivateKey =
          (RSAPrivateKey)
              KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
      return rsaDecCipher(cipherText, rsaPrivateKey);
    } catch (Exception e) {
      log.error("decryptByRSA2048PriKey error.", e);
      return null;
    }
  }

  /**
   * RSA ENCODER CIPHER rsaEncCipher
   *
   * @param source source
   * @param rsaKey rsaKey
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 15:48
   */
  private static String rsaEncCipher(String source, Key rsaKey)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException, IOException {
    Cipher cipher = Cipher.getInstance(RSA_CIPHER);
    cipher.init(Cipher.ENCRYPT_MODE, rsaKey);
    byte[] datas = source.getBytes(StandardCharsets.UTF_8);
    int inputLen = datas.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offset = 0;
    byte[] cache;
    int i = 0;
    while (inputLen - offset > 0) {
      if (inputLen - offset > 245) {
        cache = cipher.doFinal(datas, offset, 245);
      } else {
        cache = cipher.doFinal(datas, offset, inputLen - offset);
      }
      out.write(cache, 0, cache.length);
      i++;
      offset = i * 245;
    }
    String encryptData = Base64.getEncoder().encodeToString(out.toByteArray());
    out.close();
    return encryptData;
  }

  /**
   * RSA DECODER CIPHER rsaDecCipher
   *
   * @param cipherText cipherText
   * @param rsaKey rsaKey
   * @return java.lang.String
   * @author Created by ivan
   * @since 2023/2/2 15:48
   */
  private static String rsaDecCipher(String cipherText, Key rsaKey)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException, IOException {
    Cipher cipher = Cipher.getInstance(RSA_CIPHER);
    cipher.init(Cipher.DECRYPT_MODE, rsaKey);
    byte[] inputByte = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));
    int inputLen = inputByte.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offset = 0;
    byte[] cache;
    int i = 0;
    while (inputLen - offset > 0) {
      if (inputLen - offset > 256) {
        cache = cipher.doFinal(inputByte, offset, 256);
      } else {
        cache = cipher.doFinal(inputByte, offset, inputLen - offset);
      }
      out.write(cache, 0, cache.length);
      i++;
      offset = i * 256;
    }
    String data = out.toString();
    out.close();
    return data;
  }

  private RsaHelper() {
    throw new IllegalStateException("Utility class");
  }
}
