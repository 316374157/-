package com.phj.crowd.test;

import com.phj.crowd.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 31637
 * @date 2020/8/7 17:38
 */
@SpringBootTest
class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    void getTreeTest(){
        System.out.println(menuService.getWholeTree());
    }
}