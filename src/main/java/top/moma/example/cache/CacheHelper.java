package top.moma.example.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CacheHelper {
  private static Cache<String, Object> localCache =
      Caffeine.newBuilder()
          .initialCapacity(100)
          .maximumSize(500)
          .expireAfterWrite(20, TimeUnit.MINUTES)
          .weakKeys()
          .recordStats()
          .build();

  private CacheHelper() {}

  public static Cache<String, Object> getCashier() {
    // System.out.println(localCache.asMap().size());
    return localCache;
  }

  /**
   * getKeys
   *
   * @return java.util.List<java.lang.String>
   * @author Created by ivan
   * @since 2024/4/7 15:10
   */
  public static List<String> getKeys() {
    return new ArrayList<>(localCache.asMap().keySet());
  }

  /**
   * put
   *
   * @param key key
   * @param value value
   * @author Created by ivan
   * @since 2024/4/7 16:56
   */
  public static void put(String key, Object value) {
    localCache.put(key, value);
  }

  /**
   * get
   *
   * @param key key
   * @return java.lang.Object
   * @author Created by ivan
   * @since 2024/4/7 17:12
   */
  public static Object get(String key) {
    return localCache.getIfPresent(key);
  }

  public static void remove(String key) {
    localCache.invalidate(key);
  }
}
