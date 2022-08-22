package top.moma.example.apollo;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.moma.example.utils.spring.ContextHelper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component
public class MapObjectBean {
  /** {A0:'{"levelCode":0,"period":"0","level":0,"levelName":"A0"}'} */
  @Value("#{${level.comfig.map}}")
  private Map<String, String> configMap;

  private Map<String, InnerClass> levelMap;

  public MapObjectBean() {}

  @PostConstruct
  public void initMap() {
    levelMap = new HashMap<>();
    if (Objects.nonNull(configMap)) {
      configMap.entrySet().parallelStream()
          .forEach(
              config -> {
                String key = config.getKey();
                String value = config.getValue();
                InnerClass innerClass = JSON.parseObject(value, InnerClass.class);
                levelMap.put(key, innerClass);
              });
    }
  }

  public Map<String, InnerClass> getLevelMap() {
    return levelMap;
  }

  /** {"levelCode":0,"period":"0","level":0,"levelName":"A0"} */
  @Data
  @Builder
  public static class InnerClass {
    private Integer level;

    private String levelName;

    private Integer levelCode;

    private String period;
  }

  public static void main(String[] args) {
    InnerClass innerClass =
        InnerClass.builder().level(0).levelName("A0").levelCode(0).period("0").build();
    System.out.println(JSON.toJSON(innerClass));
    MapObjectBean mapObjectBean = ContextHelper.getBeanByType(MapObjectBean.class);
    mapObjectBean.getLevelMap().forEach((key, value) -> System.out.println(value.getLevelName()));
    mapObjectBean.getLevelMap().entrySet().parallelStream()
        .map(entry -> entry.getValue().getLevelName())
        .collect(Collectors.toList());
  }
}
