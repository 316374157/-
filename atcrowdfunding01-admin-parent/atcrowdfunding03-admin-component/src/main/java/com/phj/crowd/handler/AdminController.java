package com.phj.crowd.handler;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Admin;
import com.phj.crowd.service.AdminService;
import com.phj.crowd.utils.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-07-29
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('系统管理员') or hasRole('项目经理') or hasRole('部门经理') or hasRole('数据库管理员')")
public class AdminController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminService adminService;

    public AdminController(AdminService adminService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminService = adminService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 登录
     * @param admin 要登录的管理员账号
     * @param bindingResult 错误信息
     * @param session 会话，用来保存信息
     * @return 成功数据
    @PostMapping("/do/login")
    public ResultEntity doLogin(@RequestBody Admin admin, BindingResult bindingResult,HttpSession session){
        if (bindingResult.hasErrors()){
            log.error("【管理员登录】参数错误 admin={}",admin);
            return ResultEntity.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        AdminVO data = new AdminVO();
        BeanUtils.copyProperties(adminService.login(admin), data);
        session.setAttribute(CrowdConstant.ATTR_NAME_ADMIN_INFO, data);
        return ResultEntity.success(data);
    }*/

    /**
     * 登出
     * @param session 会话
     * @return 返回空数据

    @GetMapping("/logout")
    public ResultEntity logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.invalidate();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //清除认证
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResultEntity.success(new Admin());
    }
     */


    /**
     * 带有查询条件的分页查询
     * @param keyWord 查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页数据数目
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('user:get')")
    @GetMapping("/getAdminPage")
    public ResultEntity getAdminPage(String keyWord,Integer pageNum,Integer pageSize){
        Page<Admin> adminPage = adminService.getAdminPage(keyWord, pageNum,pageSize);
        return ResultEntity.success(adminPage);
    }

    /**
     * 删除选中用户（根据id）
     * @param id 用户id
     * @return 封装好的返回数据
     */
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/delete")
    public ResultEntity deleteAdminById(@RequestBody List<Integer> id){
        adminService.removeByIds(id);
        return ResultEntity.success(null,CrowdConstant.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * 通过id更新用户信息
     * @param admin 用户的新信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/update")
    public ResultEntity updateAdminById(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 添加管理员信息
     * @param admin 要添加的管理员信息
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping("/add")
    public ResultEntity addAdmin(@RequestBody Admin admin){
        String encode = bCryptPasswordEncoder.encode(admin.getUserPswd());
        admin.setUserPswd(encode);
        adminService.addAdmin(admin);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_ADD_SUCCESS);
    }
}
