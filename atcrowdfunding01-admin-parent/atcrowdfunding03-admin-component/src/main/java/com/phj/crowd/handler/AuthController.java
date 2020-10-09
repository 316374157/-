package com.phj.crowd.handler;


import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Auth;
import com.phj.crowd.service.AuthService;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/auth")
@PreAuthorize("hasRole('系统管理员') or hasRole('项目经理') or hasRole('部门经理')")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    /**
     * 获取完全的菜单树
     * @return 封装好的菜单树数据
     */
    @PreAuthorize("hasAuthority('auth:get')")
    @GetMapping("/getAuthTree")
    public ResultEntity getAuthTree(){
        return ResultEntity.success(authService.getAuthTree());
    }

    /**
     * 添加节点
     * @param auth 要添加的节点
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('auth:add')")
    @PostMapping("/add")
    public ResultEntity addAuthNode(@RequestBody Auth auth){
        authService.save(auth);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_ADD_SUCCESS);
    }

    /**
     * 更新节点
     * @param auth 要更新的节点
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('auth:update')")
    @PutMapping("/update")
    public ResultEntity updateAuthNode(@RequestBody Auth auth){
        authService.updateById(auth);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 删除节点
     * @param id 要删除的节点id
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('auth:delete')")
    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteAuthNode(@PathVariable Integer id){
        authService.removeById(id);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_DELETE_SUCCESS);
    }

}
