package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.Menu;
import com.phj.crowd.mapper.MenuMapper;
import com.phj.crowd.service.MenuService;
import com.phj.crowd.vo.MenuVO;
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
 * @since 2020-08-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public MenuVO getWholeTree() {
        List<Menu> menuList = this.baseMapper.selectList(null);
        MenuVO root = new MenuVO();
        List<MenuVO> menuVOList = new ArrayList<>();
        Map<Integer,MenuVO> menuMap = new HashMap<>();
        menuList.forEach((item) ->{
            MenuVO node = new MenuVO();
            BeanUtils.copyProperties(item, node);
            menuVOList.add(node);
            menuMap.put(item.getId(),node);
        });
        for (MenuVO item : menuVOList) {
            if(item.getPid() == null){
                root = item;
                continue;
            }
            MenuVO father = menuMap.get(item.getPid());
            List<MenuVO> children = father.getChildren();
            children.add(item);
            father.setChildren(children);
        }
        return root;
    }
}
