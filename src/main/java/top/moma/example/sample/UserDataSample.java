package top.moma.example.sample;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

@Slf4j
public class UserDataSample {

  public static void main(String[] args) {
    UserDataSample userDataSample = new UserDataSample();
    List<User> users = userDataSample.loadUser();
    System.out.println(users.size());
  }

  public List<User> loadUser() {
    List<User> result = new ArrayList<>();

    try {
      File file = ResourceUtils.getFile("classpath:user_sample.json");
      FileReader fileReader = new FileReader(file);
      String a = FileCopyUtils.copyToString(fileReader);
      result = JSON.parseArray(a, User.class);
    } catch (Exception e) {
      log.error("Error", e);
    }
    return result;
  }

  @Data
  public static class User {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("user_mobile")
    private String userMobile;
  }
}
