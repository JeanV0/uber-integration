package br.com.jean.uberintegration.service.drivers;

import br.com.jean.uberintegration.controller.cache.Drivers;
import br.com.jean.uberintegration.entity.Driver;
import br.com.jean.uberintegration.repository.DriverRepository;
import br.com.jean.uberintegration.service.redis.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    DriverRepository repository;


    RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setRepository(DriverRepository repository) {
        this.repository = repository;
    }

    @Cacheable("Drivers")
    public List<Driver> getDriver() {
        return this.repository.findAll();
    }

    @Scheduled(cron = "10 * * * * *")
    @CacheEvict("Drivers")
    public void clearCache() {
        System.out.println("Clear cache drivers");
    }

    public List<Driver> getDriverByRedis() throws JsonProcessingException {
        String value = this.redisService.getKeyValue("Drivers_Redis");
        if (value == null || value == "") {
            List<Driver> drivers = this.repository.findAll();
            this.redisService.saveKeyValue("Drivers_Redis", objectMapper.writeValueAsString(drivers));
            return drivers;
        }
        return objectMapper.readValue(value, new TypeReference<List<Driver>>() {});

    }
}
