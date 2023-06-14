package top.moma.example.infrastructure.hepler.spring;

import java.util.Objects;
import org.springframework.context.ApplicationContext;

/**
 * ContextHelper Context Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan at 5/31/21.
 */
public class ContextHelper {

  private ContextHelper() {}

  private static final ApplicationContext APPLICATION_CONTEXT =
      SpringContextRegister.getApplicationContext();

  /**
   * Object
   *
   * <p>get bean as Object
   *
   * @author Created by Ivan at 下午3:17:36 2020年1月12日
   * @return Object
   */
  public static Object getObjectBean(String beanName) {
    if (APPLICATION_CONTEXT.containsBean(beanName)) {
      return APPLICATION_CONTEXT.getBean(beanName);
    }
    return null;
  }

  /**
   * getBeanByName
   *
   * <p>get bean with type
   *
   * @author Created by Ivan at 下午3:17:52 2020年1月12日
   * @return T
   */
  @SuppressWarnings("unchecked")
  public static <T> T getBeanByName(String beanName) {
    if (APPLICATION_CONTEXT.containsBean(beanName)) {
      Class<T> beanType = (Class<T>) APPLICATION_CONTEXT.getType(beanName);
      if (!Objects.isNull(beanType)) {
        return APPLICATION_CONTEXT.getBean(beanName, beanType);
      }
    }
    return null;
  }

  /**
   * getBeanByType
   *
   * <p>get bean
   *
   * @author Created by Ivan at 下午3:18:09 2020年1月12日
   * @return T
   */
  public static <T> T getBeanByType(Class<T> beanType) {
    return APPLICATION_CONTEXT.getBean(beanType);
  }
}
