package top.moma.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>Cache Default Impl By Redis
 * @author shuzongrui
 * @date 2019-09-20
 *
 * @param <V>
 */
//@Component("cache")
public class RedisTemplateCache<V> implements Cache<String, V>{

	@Value("${cache_key_prefix}")
	private String cacheKeyPrefix;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void set(String key, V value) {
		redisTemplate.opsForValue().set(getKey(key), value);
	}

	@Override
	public void set(String key, V value, long expire, TimeUnit unit) {
		redisTemplate.opsForValue().set(getKey(key), value, expire, unit);
	}

	@Override
	public void set(Object h, String hk, Object hv) {
		this.redisTemplate.opsForHash().put(h, hk, hv);
	}

	@Override
	public void set(Object h, String hk, Object hv, long expire, TimeUnit unit) {
		this.redisTemplate.opsForHash().put(h, hk, hv);
		this.redisTemplate.expire(h, expire, unit);
	}

	@Override
	public void expire(String key, long expire, TimeUnit unit) {
		redisTemplate.expire(this.getKey(key), expire, unit);
	}

	@Override
	public Long increment(Object h, String hk, long incr, Date expireAt) {
		Long res =  this.redisTemplate.opsForHash().increment(h, hk, incr);
		if (expireAt!=null) {
			this.redisTemplate.expireAt(h, expireAt);
		}
		return res;
	}

	@Override
	public V getAndSet(String key, V value) {
		return (V)redisTemplate.opsForValue().getAndSet(getKey(key), value);
	}

	@Override
	public boolean setIfAbsent(String key, V value) {
		return redisTemplate.opsForValue().setIfAbsent(getKey(key), value);
	}

	@Override
	public boolean setIfAbsent(String key, V value, long expire, TimeUnit unit) {
		String currentKey = getKey(key);
		if(redisTemplate.opsForValue().setIfAbsent(currentKey, value) && redisTemplate.expire(currentKey, expire, unit)) {
			return true;
		}
		return false;
	}

	@Override
	public V get(String key) {
		return (V)redisTemplate.opsForValue().get(getKey(key));
	}

	@Override
	public V get(Object h, String hk) {
		return (V)this.redisTemplate.opsForHash().get(h, hk);
	}

	@Override
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	@Override
	public Collection<V> batchGet(String pattern) throws Exception {
		Set<String> keySet = this.keys(pattern);
		return this.batchGet(keySet);
	}

	@Override
	public Collection<V> batchGet(Collection<String> keys) throws Exception {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Collection<V> batchGet(Collection<String> keys, String keyPrefix) throws Exception {
		throw new UnsupportedOperationException("cluster not support for batch get by key prefix");
	}

	@Override
	public void delete(String key) throws Exception {
		redisTemplate.delete(getKey(key));
	}

	public long delete(Object h,String hk) {
		return this.redisTemplate.opsForHash().delete(h, hk);
	}

	@Override
	public Long add(String key, V... value) {
		return redisTemplate.opsForSet().add(this.getKey(key), value);
	}

	@Override
	public Long size(String key) {
		return redisTemplate.opsForSet().size(this.getKey(key));
	}

	@Override
	public Set<V> members(String key) {
		return redisTemplate.opsForSet().members(this.getKey(key));
	}

	@Override
	public Long remove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(this.getKey(key), values);
	}

	@Override
	public Boolean isMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(this.getKey(key), value);
	}

	@Override
	public void leftPush(String key, V value) {
		redisTemplate.opsForList().leftPush(key, value);
	}

	@Override
	public void leftPushAll(String key, V... values) {
		redisTemplate.opsForList().leftPushAll(key, values);
	}

	@Override
	public void leftPushAll(String key, Collection<V> values) {
		redisTemplate.opsForList().leftPushAll(key, values);
	}

	@Override
	public V leftPop(String key) {
		return (V)redisTemplate.opsForList().leftPop(key);
	}

	@Override
	public V leftPop(String key, long timeout, TimeUnit unit) {
		return (V)redisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	@Override
	public void rightPush(String key, V value) {
		redisTemplate.opsForList().rightPush(key, value);
	}

	@Override
	public void rightPushAll(String key, V... values) {
		redisTemplate.opsForList().rightPushAll(key, values);
	}

	@Override
	public void rightPushAll(String key, Collection<V> values) {
		redisTemplate.opsForList().rightPushAll(key, values);
	}

	@Override
	public V rightPop(String key) {
		return (V)redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public V rightPop(String key, long timeout, TimeUnit unit) {
		return (V)redisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	@Override
	public Long ttl(String key) {
		return redisTemplate.getExpire(key);
	}

	private final static String CACHE_KEY_SEPARATOR = ":";
	private String getKey(String key) {
		return cacheKeyPrefix + CACHE_KEY_SEPARATOR + key;
	}

}
