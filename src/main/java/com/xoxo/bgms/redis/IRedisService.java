package com.xoxo.bgms.redis;


/**
 * @Package com.learn.ssmmodules.cache
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-19 11:43
 */
public interface IRedisService {
    /**
     * 写入redis缓存（不设置expire存活时间）
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(final String key, String value);

    /**
     * 写入redis缓存（设置expire存活时间）
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    boolean set(final String key, String value, Long expire);


    /**
     * 读取redis缓存
     *
     * @param key
     * @return
     */
    String get(final String key);

    /**
     * 判断redis缓存中是否有对应的key
     *
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * redis根据key删除对应的value
     *
     * @param key
     * @return
     */
    boolean remove(final String key);

    /**
     * redis根据keys批量删除对应的value
     *
     * @param keys
     * @return
     */
    void remove(final String... keys);
}
