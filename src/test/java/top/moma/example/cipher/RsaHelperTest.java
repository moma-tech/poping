package top.moma.example.cipher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class RsaHelperTest {


  public static final String data = "test message";

  @Test
  void sign() {
    String[] keys = RsaHelper.generateKey();
    Assertions.assertEquals(2, keys.length);
    log.info(
        "Pub key:{}\n{},\n Pri Key:{}\n{}", keys[0].length(), keys[0], keys[1].length(), keys[1]);
    String sign = RsaHelper.generateSign(data, keys[1]);
    log.info("sign:{}\n{}", sign.length(), sign);
    Assertions.assertTrue(RsaHelper.verifySign(data, sign, keys[0]));
  }

  @Test
  void encrypt() {
    String[] keys = RsaHelper.generateKey();
    Assertions.assertEquals(2, keys.length);
    log.info(
        "Pub key:{}\n{},\n Pri Key:{}\n{}", keys[0].length(), keys[0], keys[1].length(), keys[1]);
    String encodePub = RsaHelper.encryptByPubKey(data, keys[0]);
    String decodePri = RsaHelper.decryptByPriKey(encodePub, keys[1]);
    Assertions.assertEquals(data, decodePri);
    //    String encodePri = RsaHelper.encryptByPriKey(data, keys[1]);
    //    String decodePub = RsaHelper.decryptByPubKey(encodePri, keys[0]);
    //    Assertions.assertEquals(data, decodePub);
  }

  @Test
  void generateKey() {
    String[] keys = RsaHelper.generateKey();
    log.info("Pub key:{},\n Pri Key:{}", keys[0], keys[1]);
  }
}
