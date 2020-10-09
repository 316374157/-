package com.phj.crowd.test;

import com.phj.crowd.service.AuthService;
import com.phj.crowd.utils.CrowdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 31637
 * @date 2020/8/17 11:12
 */
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    void codeMessageTest(){
        System.out.println(CrowdUtil.codeMessage());
    }

    @Test
    void getMenuTreeTest(){
        System.out.println(authService.getAuthTree());
    }
}