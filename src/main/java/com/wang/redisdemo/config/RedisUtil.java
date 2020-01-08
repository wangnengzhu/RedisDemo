package com.wang.redisdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description
 * Author: wnz
 * Time: 2020/1/8 16:24
 */
@Configuration
public class RedisUtil {


    private static String host = "192.168.5.17";

    private static String port = "6379";

    private static String pass = "redis";

    @Value("${redis.host}")
    public void setHost(String host) {
        RedisUtil.host = host;
    }
    @Value("${redis.port}")
    public void setPort(String port) {
        RedisUtil.port = port;
    }
    @Value("${redis.pass}")
    public void setPass(String pass) {
        RedisUtil.pass = pass;
    }

    /**
     * Description:     单个连接
     * Author wnz
     * Date  2020/1/8 16:42
     */
    public static Jedis cliSingle(){
        Jedis jedis = new Jedis(host,Integer.valueOf(port));
        jedis.auth(pass);
        return jedis;
    }

    /**
     * Description:   使用连接池连接
     * Author wnz
     * Date  2020/1/8 16:47
     */
    public static Jedis cliPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数
        jedisPoolConfig.setMaxTotal(10);
        // 最大空闲连接数
        jedisPoolConfig.setMaxIdle(2);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,Integer.valueOf(port));
        Jedis jedis = jedisPool.getResource();
        jedis.auth(pass);
        return jedis;
    }
}
