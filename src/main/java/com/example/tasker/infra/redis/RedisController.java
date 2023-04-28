package com.example.tasker.infra.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    private final RedisRepository redisRepository;

    public RedisController(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @GetMapping("/redis/{key}")
    public Object getFromRedis(@PathVariable String key) {
        return redisRepository.find(key);
    }

    @GetMapping("/redis/{key}/{value}")
    public void setToRedis(@PathVariable String key, @PathVariable Object value) {
        redisRepository.save(key, value);
    }

    @GetMapping("/redis/delete/{key}")
    public void deleteFromRedis(@PathVariable String key) {
        redisRepository.delete(key);
    }
}

