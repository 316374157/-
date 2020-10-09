package com.phj.crowd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.crowd.entity.Role;
import com.phj.crowd.vo.AdminRoleFrom;
import com.phj.crowd.vo.RoleAuthFrom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phj
 * @since 2020-08-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取该用户已经拥有的角色id
     * @param id 用户id
     * @return 用户已经拥有的角色id列表
     */
    @Select("select role_id from inner_admin_role where admin_id = #{id}")
    List<Integer> selectAdminAssignRole(Integer id);

    /**
     * 根据id删除该用户的所有角色信息
     * @param id 用户id
     */
    @Delete("delete from inner_admin_role where admin_id = #{id}")
    void deleteAdminRole(Integer id);

    /**
     * 根据id添加该用户的角色信息
     * @param adminRoleFrom 角色信息
     */
    void addAdminRole(AdminRoleFrom adminRoleFrom);

    /**
     * 根据id删除该角色的权限信息
     * @param id 角色id
     */
    @Delete("delete from inner_role_auth where role_id = #{id}")
    void deleteRoleAuth(Integer id);

    /**
     * 添加角色的权限信息
     * @param roleAuthFrom 角色权限信息
     */
    void addRoleAuth(RoleAuthFrom roleAuthFrom);

    /**
     * 根据角色id获取角色拥有的权限
     * @param id 角色id
     * @return 角色拥有的权限id集合
     */
    @Select("select auth_id from inner_role_auth where role_id = #{id}")
    List<Integer> getSelectAuth(Integer id);

    /**
     * 根据用户id获取该用户已经拥有的角色信息
     * @param id 用户id
     * @return 用户已经拥有的角色列表
     */
    @Select("select * from t_role left join inner_admin_role on id = inner_admin_role.role_id  where inner_admin_role.admin_id = #{id}")
    List<Role> selectAdminRoles(Integer id);
}
