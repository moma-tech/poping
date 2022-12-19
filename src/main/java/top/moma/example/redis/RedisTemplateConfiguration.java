package top.moma.example.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author shuzongrui
 * @date 2018-09-20
 */
@Configuration
public class RedisTemplateConfiguration {

  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // 序列化机制
    RedisSerializer<?> stringSerializer = new StringRedisSerializer();
    RedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);

    //ObjectMapper om = new ObjectMapper();
    // om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //    om.activateDefaultTyping(
    //        om.getPolymorphicTypeValidator(),
    //        ObjectMapper.DefaultTyping.NON_FINAL,
    //        JsonTypeInfo.As.PROPERTY);
    //    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    ObjectMapper om = new ObjectMapper();
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =
        new GenericJackson2JsonRedisSerializer(om);

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    // redisTemplate序列化方式配置
    // key序列化
    redisTemplate.setKeySerializer(stringSerializer);
    // value序列化
    redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

    // Hash key序列化
    redisTemplate.setHashKeySerializer(stringSerializer);
    // Hash value序列化
    redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
