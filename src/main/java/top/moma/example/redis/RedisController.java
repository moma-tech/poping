package top.moma.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/redis")
public class RedisController {
  @Autowired RedisTemplate redisTemplate;

  @GetMapping("/sera")
  public String serailCard(String uid) {
    // Card card = Card.builder().id(1).name("abccccc").build();
    List<Card> cardList = new ArrayList<>();
    cardList.add(Card.builder().id(1).name("abccccc").build());
    cardList.add(Card.builder().id(2).name("abccccc").build());
    cardList.add(Card.builder().id(3).name("abccccc").build());
    redisTemplate.opsForValue().set("cardList", cardList);
    return redisTemplate.opsForValue().get("cardList").toString();
  }
}
