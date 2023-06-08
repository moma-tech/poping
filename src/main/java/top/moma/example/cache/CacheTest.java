package top.moma.example.cache;

import com.alibaba.fastjson2.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

// @Component
public class CacheTest {
  public void cacheAdd() {
    Map cacheMap = new HashMap<String, String>();
    List<String> keyList = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      String taskCode = "task-name-" + i % 5 + "-" + i;
      TaskVo taskVo =
          TaskVo.builder()
              .taskId(i + 0l)
              .taskCode(taskCode)
              .content("this is a task message")
              .build();
      cacheMap.put(taskCode, JSON.toJSONString(taskVo));
      keyList.add(taskCode);
    }
    CacheHelper.getCashier().putAll(cacheMap);
    CacheHelper.getCashier().put("keys", keyList);
  }

  public void cacheGet(String taskCodePrefix) {
    List<String> keys = (List<String>) CacheHelper.getCashier().getIfPresent("keys");

    for (String key : keys) {
      if (key.startsWith(taskCodePrefix)) {
        String json = (String) CacheHelper.getCashier().getIfPresent(key);
        System.out.println(json);
      }
    }
  }

  @Data
  @Builder
  static class TaskVo {
    private Long taskId;
    private String taskCode;
    private String content;
  }
}
