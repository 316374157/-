package com.phj.crowd.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phj.crowd.entity.Admin;
import com.phj.crowd.entity.Role;
import com.phj.crowd.service.AdminService;
import com.phj.crowd.service.AuthService;
import com.phj.crowd.service.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 31637
 * @date 2020/8/18 17:39
 */
@Component
public class CrowdUserDetailsServiceImpl implements UserDetailsService {


    private final AdminService adminService;
    private final RoleService roleService;
    private final AuthService authService;

    public CrowdUserDetailsServiceImpl(AdminService adminService, RoleService roleService, AuthService authService) {
        this.adminService = adminService;
        this.roleService = roleService;
        this.authService = authService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("login_acct", username));
        List<Role> adminRoles = roleService.getAdminRoles(admin.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        adminRoles.forEach(item ->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + item.getName());
            authorities.add(simpleGrantedAuthority);
        });
        List<String> authList = authService.getAuthByAdminId(admin.getId());
        authList.forEach(item ->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(item);
            authorities.add(simpleGrantedAuthority);
        });
        return new SecurityAdmin(admin,authorities);
    }



}
