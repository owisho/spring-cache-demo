package com.example.spring.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    /**
     * 测试3.1.0版本jedis默认超时时间
     */
    @Test
    public void TestJedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(0);
        System.out.printf("socket time out=%d\n", jedis.getClient().getSoTimeout());
        System.out.printf("connect time out=%d\n", jedis.getClient().getConnectionTimeout());
    }

    @Test
    public void TestJedisConnect() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(0);
        System.out.printf("test=%s\n", jedis.get("test"));
    }
}
