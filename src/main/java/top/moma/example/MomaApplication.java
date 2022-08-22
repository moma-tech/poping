package top.moma.example;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.moma.example.i18n.MessageUtil;
import top.moma.example.utils.spring.ContextHelper;

@SpringBootApplication
@EnableApolloConfig
// @EnableCaching
public class MomaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MomaApplication.class, args);
    //    MapObjectBean mapObjectBean = ContextHelper.getBeanByType(MapObjectBean.class);
    //    // mapObjectBean.initMap();
    //    mapObjectBean.getLevelMap().forEach((key, value) ->
    // System.out.println(value.getLevelName()));

    //    CacheTest cacheTest = ContextHelper.getBeanByType(CacheTest.class);
    //    cacheTest.cacheAdd();
    //    cacheTest.cacheAdd();
    //    cacheTest.cacheGet("task-name-3");

    MessageUtil messageUtil = ContextHelper.getBeanByType(MessageUtil.class);
    System.out.println(messageUtil.getMessage("apply_done"));
    System.out.println(messageUtil.getMessage("apply-done", "zh_"));
  }
}
