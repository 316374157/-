package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Admin;
import com.phj.crowd.exception.LoginAcctAlreadyInUseException;
import com.phj.crowd.mapper.AdminMapper;
import com.phj.crowd.service.AdminService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-07-29
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    /**
     *
     * @Override
     *     public Admin login(Admin admin) {
     *         // 1.设置查询条件的map
     *         Map<String,Object> whereMap = new HashMap<>();
     *         String columnName = "login_acct";
     *         whereMap.put(columnName, admin.getLoginAcct());
     *         // 2.查询
     *         List<Admin> admins = this.baseMapper.selectByMap(whereMap);
     *         if(admins == null || admins.size() == 0 ){
     *             throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
     *         }
     *         if(admins.size() > 1){
     *             throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
     *         }
     *         Admin newAdmin = admins.get(0);
     *         if(newAdmin == null){
     *             throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
     *         }
     *         // 3.加密密码
     *         String source = CrowdUtil.md5(admin.getUserPswd());
     *         // 4.对比密码
     *         if(Objects.equals(source, newAdmin.getUserPswd())){
     *             return newAdmin;
     *         }
     *         throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
     *     }
     */


    @Override
    public Page<Admin> getAdminPage(String keyWord, Integer pageNum, Integer pageSize) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>()
                .like("login_acct", keyWord)
                .or().like("user_name", keyWord)
                .or().like("email", keyWord);
        return this.baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @Override
    public void updateAdmin(Admin admin) {
        this.baseMapper.updateById(admin);
    }

    @Override
    public void addAdmin(Admin admin) {
        admin.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            this.baseMapper.insert(admin);
        }catch (Exception e){
            e.printStackTrace();
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 类型说明是账号重复导 致的
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            // 为了不掩盖问题，如果当前捕获到的不是 DuplicateKeyException 类型的异常，则 把当前捕获到的异常对象继续向上抛出
            throw e;
        }
    }
}
