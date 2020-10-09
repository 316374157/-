package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.Auth;
import com.phj.crowd.mapper.AuthMapper;
import com.phj.crowd.service.AuthService;
import com.phj.crowd.vo.AuthVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-08-17
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    @Override
    public List<AuthVO> getAuthTree() {
        List<Auth> authList = this.baseMapper.selectList(null);
        List<AuthVO> result = new ArrayList<>();
        List<AuthVO> authVOList = new ArrayList<>();
        Map<Integer,AuthVO> authMap = new HashMap<>();
        authList.forEach((item) ->{
            AuthVO node = new AuthVO();
            BeanUtils.copyProperties(item, node);
            authVOList.add(node);
            authMap.put(item.getId(),node);
        });
        for (AuthVO item : authVOList) {
            if(item.getCategoryId() == null){
                result.add(item);
                continue;
            }
            AuthVO father = authMap.get(item.getCategoryId());
            List<AuthVO> children = father.getChildren();
            children.add(item);
            father.setChildren(children);
        }
        return result;
    }

    @Override
    public List<String> getAuthByAdminId(Integer id) {
        return this.baseMapper.getAuthByAdminId(id);
    }
}
