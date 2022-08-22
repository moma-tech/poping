package top.moma.example.redis;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Common Cache Interface
 * @author shuzongrui
 * @date 2019-09-20
 *
 * @param <K>
 * @param <V>
 */

public interface Cache<K, V> {
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	void set(K key, V value);
	
	/**
	 * 
	 * @param key
	 * @param value 必须是一个可序列化的对象, 可以是容器类型如:List，但容器里面保存的对象必须是可序列化的
	 * @param expire
	 * @param unit
	 */
    void set(K key, V value, long expire, TimeUnit unit);

	/**
	 * HASH操作
	 * @param h 基础值
	 * @param hk key
	 * @param hv value
	 */
	void set(Object h, K hk, V hv);

	/**
	 *在一个项目中添加key
	 *set
	 *@param key
	 *@param value
	 */
	/**
	 * HASH操作-超时
	 * @param h 基础值
	 * @param hk key
	 * @param hv value
	 * @param expire
	 * @param unit
	 */
	void set(Object h, K hk, V hv, long expire, TimeUnit unit);

	/**
	 * 设置KEY过期时间
	 * @param key
	 * @param expire
	 * @param unit
	 */
	void expire(K key, long expire, TimeUnit unit);

	/**
	 * HASH值增量操作-超时
	 * @param h 基础值
	 * @param hk key
	 * @param incr 增量值
	 * @param expireAt 过期时间
	 * @return
	 */
	Long increment(Object h, String hk, long incr, Date expireAt);

	/**
	 * 新值缓存，原来的值返回
	 * @param key
	 * @param value
	 * @return V 原来的缓存的值
	 */
	V getAndSet(K key, V value);

    /**
     * 
     * @param key
     * @param value
     */
    boolean setIfAbsent(K key, V value);

	/**
	 *
	 * @param key
	 * @param value
	 */
	/**
	 *
	 * @param key
	 * @param value
	 * @param expire 过期时间
	 * @return
	 */
	boolean setIfAbsent(K key, V value, long expire, TimeUnit unit);
    
    /**
     * 
     * @param key
     * @return
     */
    V get(K key);


	/**
	 * HASH方式获取值
	 * @param h 基础值
	 * @param hk key
	 * @return V
	 */
	V get(Object h, String hk);

	/**
	 *
	 * @param pattern keys prefix
	 * @return
	 * @throws Exception
	 */
	Collection<V> batchGet(K pattern) throws Exception;

    /**
     * 
     * @param keys
     * @return
     * @throws Exception
     */
	Collection<V> batchGet(final Collection<K> keys) throws Exception;
    
    /**
     * 
     * @param keys
     * @param keyPrefix
     * @return
     * @throws Exception
     */
	Collection<V> batchGet(final Collection<K> keys, final String keyPrefix) throws Exception;
    
    /**
     * 
     * @param key
     * @throws Exception
     */
    void delete(K key) throws Exception;

	/**
	 * HASH方式删除制定hk的值
	 * @param h 基础值
	 * @param hk key
	 * @return 状态码，1成功，0失败
	 */
	long delete(Object h, String hk);

	/**
	 *
	 * @param pattern
	 * @return
	 */
	Set<K> keys(K pattern);

    /*** set operator ***/
	/**
	 * @param key
	 * @param value
	 * @return
	 */
	Long add(K key, V... value);

	Long size(K key);

	Set<V> members(K key);

	Long remove(K key, Object... values);

	Boolean isMember(K key, Object value);

	/*** list operator ***/
	void leftPush(K key, V value);

	void leftPushAll(K key, V... values);

	void leftPushAll(K key, Collection<V> values);

	V leftPop(K key);

	V leftPop(K key, long timeout, TimeUnit unit);

	void rightPush(K key, V value);

	void rightPushAll(K key, V... values);

	void rightPushAll(K key, Collection<V> values);

	V rightPop(K key);

	V rightPop(K key, long timeout, TimeUnit unit);

	Long ttl(String key);

}
