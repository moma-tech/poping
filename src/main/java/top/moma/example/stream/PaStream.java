package top.moma.example.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import top.moma.example.sample.UserDataSample;

public class PaStream {
  public static void main(String[] args) {
    UserDataSample userDataSample = new UserDataSample();
    List<UserDataSample.User> users = userDataSample.loadUser();
    // 1000
    System.out.println(users.size());
    System.out.println("--- Start ---");

    // Loop 10 times
    for (int i = 0; i < 10; i++) {
      System.out.println("--- Looping ---");
      wrong(users);
      collection(users);
      syncList(users);
    }
  }

  /**
   * wrong
   *
   * @param users users
   * @author Created by ivan
   * @since 2024/5/7 11:28
   */
  public static void wrong(List<UserDataSample.User> users) {
    List<String> ids = new ArrayList<>();
    users.parallelStream()
        .forEach(
            user -> {
              ids.add(user.getUserId());
            });
    // should be 1000
    System.out.println(ids.size());
  }

  /**
   * collection
   *
   * @param users users
   * @author Created by ivan
   * @since 2024/5/7 11:31
   */
  public static void collection(List<UserDataSample.User> users) {
    List<String> ids = new ArrayList<>();
    ids = users.parallelStream().map(UserDataSample.User::getUserId).collect(Collectors.toList());
    // should be 1000
    System.out.println(ids.size());
  }

  /**
   * syncList
   *
   * <p>写 > 读, 使用Collections.synchronizedList(new ArrayList<>())
   *
   * <p>读 > 写, 使用new CopyOnWriteArrayList<>();
   *
   * @param users users
   * @author Created by ivan
   * @since 2024/5/7 11:35
   */
  public static void syncList(List<UserDataSample.User> users) {
    List<String> ids = Collections.synchronizedList(new ArrayList<>());
    users.parallelStream()
        .forEach(
            user -> {
              ids.add(user.getUserId());
            });
    // should be 1000
    System.out.println(ids.size());
  }
}
