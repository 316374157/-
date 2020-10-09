package com.phj.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 31637
 * @date 2020/8/20 15:41
 */
@MapperScan("com.phj.crowd.mapper")
@SpringBootApplication
public class CrowdMainTypeMysql {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainTypeMysql.class, args);
    }

}
