package top.moma.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
public class TestPostController {
  @PostMapping("/t")
  public String postT(@PathVariable("uid") String uid) {

    log.info("--------Test - T" + uid);
    return "TEst V";
  }
}
