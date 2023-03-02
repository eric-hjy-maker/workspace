package com.heima.test;

import com.heima.jedis.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
    }

    @Test
    void testString() {
        jedis.set("name", "zhangsan");
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void testHash() {
        jedis.hset("user:1", "name", "zhangsan");
        jedis.hset("user:1", "age", "18");
        String name = jedis.hget("user:1", "name");
        System.out.println(name);

        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }

}
