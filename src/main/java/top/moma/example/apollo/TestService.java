package top.moma.example.apollo;

import cn.hutool.http.HttpRequest;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  public String sendPost(String request) {
    String url = "http://mmtech.top/abc";
    String result =
        HttpRequest.post(url)
            .header("Content-Type", "application/json;charset=UTF-8")
            .body(request)
            .timeout(1000)
            .execute()
            .body();
    return result;
  }
}
