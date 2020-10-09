package com.phj.crowd.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.Role;
import com.phj.crowd.vo.AdminRoleFrom;
import com.phj.crowd.vo.RoleAuthFrom;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-08-06
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询角色数据，并可以附带模糊查询的条件
     * @param keyWord 查询条件
     * @param pageNum 当前页面
     * @param pageSize 每页数据数目
     * @return 分页数据
     */
    Page<Role> getRolePage(String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 根据管理员id查询该管理员所有的角色和未拥有的角色
     * @param id 管理员id
     * @return 拥有的角色和未拥有的角色
     */
    Map<String, Object> getAdminRole(Integer id);

    /**
     * 更新管理员的角色信息
     * @param adminRoleFrom 用户角色信息
     */
    void updateAdminRole(AdminRoleFrom adminRoleFrom);

    /**
     * 更新角色的权限信息
     * @param roleAuthFrom 角色权限信息
     */
    void updateRoleAuth(RoleAuthFrom roleAuthFrom);

    /**
     * 根据角色id获取角色拥有的权限
     * @param id 角色id
     * @return 角色拥有的权限id集合
     */
    List<Integer> getSelectAuth(Integer id);

    /**
     * 根据管理员id获取管理员的角色信息
     * @param id 管理员id
     * @return 角色信息列表
     */
    List<Role> getAdminRoles(Integer id);

}
