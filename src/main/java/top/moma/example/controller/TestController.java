package top.moma.example.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.example.apollo.TestService;

@Slf4j
@RestController
@RequestMapping("/api/ex")
public class TestController {
  @Autowired TestService testService;

  @GetMapping("/apm/value")
  @Cacheable(value = "user", key = "#uid")
  public String getRest(String uid) {

    log.info("--------Test - value");
    return "TEst";
  }

  @GetMapping("/test/v")
  public String getT(String uid) {
    log.info("--------Test - T");
    log.debug("--------Test - D");

    log.error("--------E Test - T");
    return "TEst V";
  }

  @GetMapping("/test/exception")
  public String getE(String uid) {
    throw new RuntimeException("This is a exception");
  }

  @GetMapping("/test/getpost")
  public String getPost(HttpServletRequest httpServletRequest) {
    Enumeration<String> a = httpServletRequest.getHeaderNames();
    while (a.hasMoreElements()) {
      System.out.println(a.nextElement());
    }

    System.out.println("sw8::" + httpServletRequest.getHeader("sw8"));
    System.out.println("sw8-x::" + httpServletRequest.getHeader("sw8-x"));
    System.out.println("sw8-cr::" + httpServletRequest.getHeader("sw8-correlation"));
    System.out.println(TraceContext.traceId());
    //    String re = "{'a':'123'}";
    //    String response = "";
    //    try {
    //      response = testService.sendPost(re);
    //      return response;
    //    } catch (Exception e) {
    //      System.out.println(e);
    //    }
    return "error";
  }

  public static void main(String[] args) {

    String input = "YXBpLXNlcnZpY2U6Om14LW5wLWFwaS1nYXRld2F5LWFwcA==.1_R0VUOi8=";
    String[] part = input.split("_");
    String service = part[0].split("\\.")[0];
    String end = part[1];
    String a = new String(Base64.getDecoder().decode(service), StandardCharsets.UTF_8);
    String b = new String(Base64.getDecoder().decode(end), StandardCharsets.UTF_8);
    System.out.println(a);
    System.out.println(b);
  }
}
