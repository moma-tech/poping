package top.moma.example.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

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
}
