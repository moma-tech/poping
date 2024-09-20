package top.moma.example.stream;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import top.moma.example.sample.UserDataSample;
import top.moma.example.sample.UserDataSample.User;

public class ParallelStream {

  public static void main(String[] args) {
    Runnable task =
        new Runnable() {
          @Override
          public void run() {
            UserDataSample userDataSample = new UserDataSample();
            List<User> users = userDataSample.loadUser();
            List<String> ids =
                users.parallelStream()
                    .map(
                        user -> {
                          String userId = user.getUserId();
                          if (userId.startsWith("86")) {
                            return userId;
                          }
                          if ("INT".equals(user.getUserType()) && userId.length() < 5) {
                            return userId;
                          }
                          return null;
                        })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (ids.size() != 37) {
              System.out.println("Fail");
            }
          }
        };
    ParallelStream parallelStream = new ParallelStream();
    try {
      parallelStream.start(2000, task);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public long start(int nums, Runnable task) throws InterruptedException {
    final CountDownLatch startGate = new CountDownLatch(1);
    final CountDownLatch endGate = new CountDownLatch(nums);

    for (int i = 0; i < nums; i++) {
      Thread t =
          new Thread() {
            @Override
            public void run() {
              try {
                // 使线程在此等待，当开始门打开时，一起涌入门中
                startGate.await();
                try {
                  task.run();
                } finally {
                  // 将结束门减1，减到0时，就可以开启结束门了
                  endGate.countDown();
                }
              } catch (InterruptedException ie) {
                ie.printStackTrace();
              }
            }
          };
      t.start();
    }
    long startTime = System.nanoTime();
    System.out.println(
        startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
    // 因开启门只需一个开关，所以立马就开启开始门
    startGate.countDown();
    // 等等结束门开启
    endGate.await();
    long endTime = System.nanoTime();
    System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
    return endTime - startTime;
  }
}
