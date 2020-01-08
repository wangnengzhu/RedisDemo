package com.wang.redisdemo.test;

import com.wang.redisdemo.config.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * Description
 * Author: wnz
 * Time: 2020/1/8 16:49
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = RedisUtil.cliSingle();
        // 切换DB
//        jedis.select(2);
        // 存值 setex 等于 set+expire
        jedis.set("my","123");
        jedis.expire("my", 20);
        //  取值
        String my = jedis.get("my");
        System.out.println(my);
        // 有则追加值 无则新增
        jedis.append("w","aaaaaa");

        // 删除所有DB数据
        /*jedis.flushAll();*/
        // 删除当前DB数据
        /*jedis.flushDB();*/

        jedis.close();
    }
}
