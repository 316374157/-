package com.phj.crowd.api;

import com.phj.crowd.utils.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31637
 * @date 2020/8/24 10:11
 */
@FeignClient("phj-crowd-redis")
public interface RedisRemoteService {
    /**
     * 存入Redis（不带超时时间）
     * @param key 键
     * @param value 值
     * @return 处理结果
     */
    @RequestMapping("/set/Redis/KeyValue/Remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);

    /**
     * 存入Redis（带超时时间）
     * @param key 键
     * @param value 值
     * @param time 超时时间 (以毫秒为单位)
     * @return 处理结果
     */
    @RequestMapping("/set/Redis/KeyValue/WithTimeout/Remote")
    public ResultEntity<String> setRedisKeyValueWithTimeoutRemote(@RequestParam("key") String key
            ,@RequestParam("value") String value
            ,@RequestParam("time") Long time);

    /**
     * 通过key获取存入的字符串的值
     * @param key key
     * @return 处理结果
     */
    @RequestMapping("/get/Redis/StringValue/By/Key")
    public ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key);

    /**
     * 通过key删除字符串数据
     * @param key key
     * @return 处理结果
     */
    @RequestMapping("/remove/Redis/By/Key")
    public ResultEntity<String> removeRedisByKey(@RequestParam("key") String key);
}
