package com.phj.crowd.test;

import com.phj.crowd.entity.po.MemberPO;
import com.phj.crowd.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 31637
 * @date 2020/8/21 15:52
 */
@SpringBootTest
public class MysqlTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    MemberMapper memberMapper;

    @Test
    void connectionTest() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void insertTest(){
        MemberPO memberPO = new MemberPO(null, "2017001", "123456", "张三", "zhangsan@qq.com", 0, 0, "张三", "000000000", 1);
        memberMapper.insert(memberPO);
    }
}
