package com.phj.crowd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.Auth;
import com.phj.crowd.vo.AuthVO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-08-17
 */
public interface AuthService extends IService<Auth> {
    /**
     * 获取权限树集合
     * @return 权限树集合
     */
    List<AuthVO> getAuthTree();

    /**
     * 获取用户拥有的权限信息
     * @param id 用户id
     * @return 用户的权限信息集合
     */
    List<String> getAuthByAdminId(Integer id);

}
