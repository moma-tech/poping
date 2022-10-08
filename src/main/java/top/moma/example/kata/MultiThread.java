package top.moma.example.kata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThread {

  public static Map<String, String> getE(String row) throws InterruptedException {
    Map reMap = new ConcurrentHashMap();
    int size = row.length() / 2 + row.length() % 2;

    CountDownLatch c = new CountDownLatch(size);
    ExecutorService executorService = Executors.newFixedThreadPool(size);
    for (int i = 0; i < size; i++) {
      int finalI = i;
      Runnable runnable =
          new Runnable() {
            @Override
            public void run() {
              Thread.currentThread().setName(finalI + "");
              reMap.put(finalI + "", row.substring(finalI * 2, finalI * 2 + 2) + "*");
              c.countDown();
            }
          };
      executorService.execute(runnable);
    }
    c.await();
    executorService.shutdown();
    return reMap;
  }

  public static void main(String[] args) {
    String testRow = "abcdabcdabcdabcd";
    try {
      Map m = MultiThread.getE(testRow);
      for (int i = 0; i < 10; i++) {
        System.out.println(m.get(i + ""));
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
