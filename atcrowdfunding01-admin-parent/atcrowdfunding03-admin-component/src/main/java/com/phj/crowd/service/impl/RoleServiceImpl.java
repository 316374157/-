package com.phj.crowd.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.Role;
import com.phj.crowd.mapper.RoleMapper;
import com.phj.crowd.service.RoleService;
import com.phj.crowd.vo.AdminRoleFrom;
import com.phj.crowd.vo.RoleAuthFrom;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-08-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Page<Role> getRolePage(String keyWord, Integer pageNum, Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>()
                .like("name", keyWord);
        return this.baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Override
    public Map<String, Object> getAdminRole(Integer id) {
        Map<String, Object> listMap = new HashMap<>();
        List<Integer> adminAssignRole = this.baseMapper.selectAdminAssignRole(id);
        List<Role> adminAllRole = this.baseMapper.selectList(null);
        listMap.put("adminAssignRole",adminAssignRole);
        listMap.put("adminAllRole",adminAllRole);
        return listMap;
    }

    @Override
    public void updateAdminRole(AdminRoleFrom adminRoleFrom) {
        this.baseMapper.deleteAdminRole(adminRoleFrom.getId());
        if(adminRoleFrom.getRoleIdList().size() > 0){
            this.baseMapper.addAdminRole(adminRoleFrom);
        }

    }

    @Override
    public void updateRoleAuth(RoleAuthFrom roleAuthFrom) {
        this.baseMapper.deleteRoleAuth(roleAuthFrom.getId());
        if(roleAuthFrom.getAuthIdList().size() > 0){
            this.baseMapper.addRoleAuth(roleAuthFrom);
        }
    }

    @Override
    public List<Integer> getSelectAuth(Integer id) {
        return this.baseMapper.getSelectAuth(id);
    }

    @Override
    public List<Role> getAdminRoles(Integer id) {
        return this.baseMapper.selectAdminRoles(id);
    }
}
