package com.heima.jedis.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        // 空闲最大连接数
        config.setMaxIdle(8);
        // 空闲最小连接数
        config.setMinIdle(0);
        // 最大等待时间
        config.setMaxWaitMillis(10000);
        jedisPool = new JedisPool(config, "localhost", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
