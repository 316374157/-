package com.phj.crowd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 31637
 * @date 2020/8/20 15:41
 */
@EnableFeignClients
@SpringBootApplication
public class CrowdMainTypePay {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainTypePay.class, args);
    }

}
