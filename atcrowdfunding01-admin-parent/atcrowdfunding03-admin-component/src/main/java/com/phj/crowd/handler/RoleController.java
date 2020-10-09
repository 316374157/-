package com.phj.crowd.handler;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Role;
import com.phj.crowd.service.RoleService;
import com.phj.crowd.utils.ResultEntity;
import com.phj.crowd.vo.AdminRoleFrom;
import com.phj.crowd.vo.RoleAuthFrom;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('系统管理员') or hasRole('项目经理') or hasRole('部门经理')")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 带有查询条件的分页查询
     * @param keyWord 查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页数据数目
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('role:get')")
    @GetMapping("/getRolePage")
    public ResultEntity getRolePage(String keyWord, Integer pageNum, Integer pageSize){
        Page<Role> rolePage = roleService.getRolePage(keyWord, pageNum, pageSize);
        return ResultEntity.success(rolePage);
    }

    /**
     * 通过id查询用户所有的角色和未拥有的角色
     * @param id 管理员id
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('role:get')")
    @GetMapping("/getAdminRole/{id}")
    public ResultEntity getAdminRole(@PathVariable Integer id){
        Map<String,Object> roleMap = roleService.getAdminRole(id);
        return ResultEntity.success(roleMap);
    }


    /**
     * 删除选中角色（根据id）
     * @param id 角色id
     * @return 封装好的返回数据
     */
    @PreAuthorize("hasAuthority('role:delete')")
    @DeleteMapping("/delete")
    public ResultEntity deleteRoleById(@RequestBody List<Integer> id){
        roleService.removeByIds(id);
        return ResultEntity.success(null,CrowdConstant.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * 通过id更新角色信息
     * @param role 角色的新信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping("/update")
    public ResultEntity updateRoleById(@RequestBody Role role){
        roleService.updateById(role);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 添加角色信息
     * @param role 要添加的角色信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping("/add")
    public ResultEntity addRole(@RequestBody Role role){
        roleService.save(role);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_ADD_SUCCESS);
    }
    /**
     * 更改管理员的角色信息
     * @param adminRoleFrom 要更改角色信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("/updateAdminRole")
    public ResultEntity updateAdminRole(@Validated @RequestBody AdminRoleFrom adminRoleFrom, BindingResult bindingResult){
        roleService.updateAdminRole(adminRoleFrom);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_OPERATION_SUCCESS);
    }

    /**
     * 更改角色的权限信息
     * @param roleAuthFrom 要更改的角色权限信息
     * @param bindingResult 数据校验结果
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('auth:update')")
    @PostMapping("/updateRoleAuth")
    public ResultEntity updateRoleAuth(@Validated @RequestBody RoleAuthFrom roleAuthFrom, BindingResult bindingResult){
        roleService. updateRoleAuth(roleAuthFrom);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_OPERATION_SUCCESS);
    }
    /**
     * 获取该角色拥有的权限
     * @param id 要更改的角色权限信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('auth:get')")
    @GetMapping("/getSelectAuth/{id}")
    public ResultEntity getSelectAuth(@PathVariable Integer id){
        return ResultEntity.success(roleService. getSelectAuth(id), CrowdConstant.MESSAGE_OPERATION_SUCCESS);
    }
}
