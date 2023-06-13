package top.moma.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ex")
public class TestController {

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
    return uid;
  }
}
