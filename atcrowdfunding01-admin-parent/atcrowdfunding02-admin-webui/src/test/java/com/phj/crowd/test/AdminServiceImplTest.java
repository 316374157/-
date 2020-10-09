package com.phj.crowd.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.crowd.entity.Admin;
import com.phj.crowd.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 31637
 * @date 2020/8/4 8:54
 */
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    AdminService adminService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void loginTest(){
//        Admin admin = adminService.login(new Admin(null, "admin", "123456", null, null, null));
//        System.out.println(admin);
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    void insertTest() {
        List<Admin> list = new ArrayList<>();
        for (int i = 0; i < 238; i++) {
            {
                list.add(new Admin(null, "loginAcct" + i, "userPswd" + i, "userName" + i, "email@qq.com", null));
            }
        }
        adminService.saveBatch(list);
    }

    @Test
    void keyWordPageTest(){
        Page<Admin> adminPage = adminService.getAdminPage("6", 1, 10);
        System.out.println(adminPage);
    }
}
