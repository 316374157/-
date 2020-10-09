package com.phj.crowd.handler;

import com.phj.crowd.utils.ResultEntity;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 31637
 * @date 2020/8/24 10:34
 */
@RestController
public class RedisHandler {
    private final StringRedisTemplate stringRedisTemplate;

    public RedisHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping("/set/Redis/KeyValue/Remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value){
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    };


    @RequestMapping("/set/Redis/KeyValue/WithTimeout/Remote")
    public ResultEntity<String> setRedisKeyValueWithTimeoutRemote(@RequestParam("key") String key
            ,@RequestParam("value") String value
            ,@RequestParam("time") Long time){
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value,time, TimeUnit.MILLISECONDS);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    };


    @RequestMapping("/get/Redis/StringValue/By/Key")
    public ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key){
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            return ResultEntity.success(operations.get(key));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    };

    @RequestMapping("/remove/Redis/By/Key")
    public ResultEntity<String> removeRedisByKey(@RequestParam("key") String key){
        try {
            stringRedisTemplate.delete(key);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    };

}
