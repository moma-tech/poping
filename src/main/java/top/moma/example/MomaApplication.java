package top.moma.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableApolloConfig
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

    //    JobConfig jobConfig = ContextHelper.getBeanByType(JobConfig.class);

    //    MessageUtil messageUtil = ContextHelper.getBeanByType(MessageUtil.class);
    //    System.out.println(messageUtil.getMessage("apply_done"));
    //    System.out.println(messageUtil.getMessage("apply-done", "zh_"));
    //
    //    try {
    //      File resource =
    //          ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +
    // "test/level/MyResources.txt");
    //      BufferedReader bufferedReader = new BufferedReader(new FileReader(resource));
    //      bufferedReader.lines().forEach(System.out::println);
    //    } catch (FileNotFoundException e) {
    //      e.printStackTrace();
    //    }
  }
}
