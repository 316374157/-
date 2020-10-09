package com.phj.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.crowd.entity.Auth;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phj
 * @since 2020-08-17
 */
public interface AuthMapper extends BaseMapper<Auth> {
    /**
     * 根据用户id查询用户所有的权限信息
     * @param adminId 用户id
     * @return 用户所有的权限名字
     */
    List<String> getAuthByAdminId(Integer adminId);
}
