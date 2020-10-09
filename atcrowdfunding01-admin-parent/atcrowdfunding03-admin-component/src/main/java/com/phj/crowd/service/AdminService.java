package com.phj.crowd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.Admin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-07-29
 */
public interface AdminService extends IService<Admin> {
    /**
     * 管理员登录的方法
     * @param admin 要登录的管理员
     * @return null表示无此管理员，登陆失败；其他为登录成功

    Admin login(Admin admin);
     */

    /**
     * 分页查询管理员数据，并可以附带模糊查询的条件
     * @param keyWord 查询条件
     * @param pageNum 当前页面
     * @param pageSize 每页数据数目
     * @return 分页数据
     */
    Page<Admin> getAdminPage(String keyWord,Integer pageNum,Integer pageSize);

    /**
     * 更新管理员
     * @param admin 要更新的管理员信息
     */
    void updateAdmin(Admin admin);

    /**
     * 添加管理员
     * @param admin 要添加的管理员信息
     */
    void addAdmin(Admin admin);


}
