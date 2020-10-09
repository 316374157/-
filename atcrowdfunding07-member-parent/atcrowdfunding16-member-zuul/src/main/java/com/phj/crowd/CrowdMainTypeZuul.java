package com.phj.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 31637
 * @date 2020/8/20 15:41
 */
@EnableZuulProxy
@SpringBootApplication
public class CrowdMainTypeZuul {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainTypeZuul.class, args);
    }

}
