package com.phj.crowd.test;

import com.phj.crowd.entity.Role;
import com.phj.crowd.service.RoleService;
import com.phj.crowd.vo.AdminRoleFrom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 31637
 * @date 2020/8/7 9:20
 */
@SpringBootTest
class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @Test
    void insertTest(){
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < 235; i++) {
            list.add(new Role(null,"role"+i));
        }
        roleService.saveBatch(list);
    }

    @Test
    void selectAdminRole(){
        Map<String, Object> adminRole = roleService.getAdminRole(1);
        System.out.println(adminRole);
    }

    @Test
    void addAdminRoleTest(){
        AdminRoleFrom adminRoleFrom = new AdminRoleFrom();
        adminRoleFrom.setId(2);
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(1);
        roleIdList.add(2);
        roleIdList.add(3);
        roleIdList.add(9);
        roleIdList.add(6);
        adminRoleFrom.setRoleIdList(roleIdList);
        roleService.updateAdminRole(adminRoleFrom);
    }

}