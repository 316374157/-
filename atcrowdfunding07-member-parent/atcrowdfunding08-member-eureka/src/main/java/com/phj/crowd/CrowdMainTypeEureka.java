package com.phj.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer 启用Eureka的服务器功能
 * @author 31637
 * @date 2020/8/20 15:41
 */
@EnableEurekaServer
@SpringBootApplication
public class CrowdMainTypeEureka {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainTypeEureka.class, args);
    }

}
