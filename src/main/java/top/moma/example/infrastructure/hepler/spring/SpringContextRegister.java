package top.moma.example.infrastructure.hepler.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextRegister
 *
 * @author ivan
 * @version 1.0 Created by ivan at 5/31/21.
 */
@Component
public class SpringContextRegister implements ApplicationContextAware {
  private static ApplicationContext APPLICATION_CONTEXT;

  static ApplicationContext getApplicationContext() {
    return APPLICATION_CONTEXT;
  }

  static void setStaticApplicationContext(ApplicationContext applicationContext) {
    APPLICATION_CONTEXT = applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextRegister.setStaticApplicationContext(applicationContext);
  }
}
