package com.phj.crowd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.Menu;
import com.phj.crowd.vo.MenuVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-08-07
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取全部的树形菜单
     * @return 前端需要的树形菜单
     */
    MenuVO getWholeTree();

}
