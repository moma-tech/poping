package top.moma.example.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis Cluster Configuration
 * @author shuzongrui
 * @date 2019-09-20
 */
//@Configuration
public class RedisConnectionFactoryConfiguration extends CachingConfigurerSupport {

    /**
     * 逗号分隔，例如 192.168.0.1:9091,192.168.0.2:9092
     */
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private int maxRedirects;

    @Value("${spring.redis.is-cluster:true}")
    private boolean isCluster;

    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password:}")
    private String password;

    @Value("${spring.redis.db:0}")
    private int db;

    @Bean
    public RedisConfiguration redisConfiguration() {
        if (isCluster) {
            // cluster 模式
            List<String> nodes = new ArrayList<>();
            nodes.add(clusterNodes);
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(nodes);
            redisClusterConfiguration.setMaxRedirects(maxRedirects);
            redisClusterConfiguration.setPassword(password);
            return redisClusterConfiguration;
        }
        // master-slave 模式
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setPassword(password);

        return redisStandaloneConfiguration;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.redis.lettuce.pool")
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        return poolConfig;
    }

    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
        return LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
    }

    @Bean
    public RedisConnectionFactory connectionFactory(RedisConfiguration redisConfiguration, LettuceClientConfiguration lettuceClientConfiguration) {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
        lettuceConnectionFactory.setDatabase(db);
        return lettuceConnectionFactory;
    }
}
