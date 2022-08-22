package top.moma.example.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * Message Util
 *
 * <p>I18n Message Util
 *
 * @version 1.0
 * @author Created by ivan at 14:35.
 */
@Component
@Slf4j
public class MessageUtil {
  final MessageSource messageSource;

  @Value("${application.i18n.code:es}")
  private String local;

  public MessageUtil(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * getMessage
   *
   * <p>获取国际化信息
   *
   * @param key key
   * @return java.lang.String
   * @author Created by ivan
   * @since 2022/7/6 16:42
   */
  public String getMessage(String key) {
    String message = key;
    try {
      message = messageSource.getMessage(key, null, new Locale(local));
    } catch (NoSuchMessageException e) {
      log.error("Can not locate i18n message key: {},{}", key, local);
    }
    return message;
  }

  /**
   * getMessage
   *
   * <p>获取国际化信息 - 自定义区域
   *
   * @param key key
   * @param local local
   * @return java.lang.String
   * @author Created by ivan
   * @since 2022/7/20 15:33
   */
  public String getMessage(String key, String local) {
    String message = key;
    Locale locale = new Locale(this.local);
    if (StringUtils.hasLength(local)) {
      if (local.contains("_")) {
        String[] args = local.split("_");
        if (args.length > 1) {
          locale = new Locale(args[0], args[1]);
        } else {
          locale = new Locale(args[0]);
        }
      }
    }
    try {
      message = messageSource.getMessage(key, null, locale);
    } catch (NoSuchMessageException e) {
      log.error("Can not locate i18n message key: {},{}", key, local);
    }
    return message;
  }
}
