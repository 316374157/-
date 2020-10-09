package com.phj.crowd.handler;

import com.phj.crowd.api.MySQLRemoteService;
import com.phj.crowd.entity.vo.PortalTypeVO;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 31637
 * @date 2020/8/24 11:10
 */
@RestController
public class PortalHandler {

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    /**
     * 获取首页数据（空方法，有时间了在填数据吧）
     * @return 首页数据集合
     */
    @RequestMapping("/")
    public ResultEntity<List<PortalTypeVO>> getHomePage(){
        return mySQLRemoteService.getPortalTypeVOListRemote();
    }



}
