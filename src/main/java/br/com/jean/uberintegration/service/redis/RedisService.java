package br.com.jean.uberintegration.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveKeyValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getKeyValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
