package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        //插入一条string类型数据
        redisTemplate.opsForValue().set("name", "李四");
        //读取一条string类型数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name: "+name);
    }
}
