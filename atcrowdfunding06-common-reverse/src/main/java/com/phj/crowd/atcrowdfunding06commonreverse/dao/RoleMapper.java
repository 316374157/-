package com.phj.crowd.atcrowdfunding06commonreverse.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.crowd.atcrowdfunding06commonreverse.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@Mapper
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
     * @param id 用户id
     * @param roleIdList 角色id
     */
    void addAdminRole(@Param("id") Integer id, @Param("roleIdList") List<Integer> roleIdList);

}
