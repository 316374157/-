package com.phj.crowd.handler;


import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Menu;
import com.phj.crowd.service.MenuService;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-08-07
 */
@RestController
@RequestMapping("/menu")
@PreAuthorize("hasRole('系统管理员') or hasRole('项目经理') or hasRole('部门经理')")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取完全的菜单树
     * @return 封装好的菜单树数据
     */
    @PreAuthorize("hasAuthority('menu:get')")
    @GetMapping("/getWholeTree")
    public ResultEntity getMenuTree(){
        return ResultEntity.success(menuService.getWholeTree());
    }

    /**
     * 添加节点
     * @param menu 要添加的节点
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('menu:add')")
    @PostMapping("/add")
    public ResultEntity addMenuNode(@RequestBody Menu menu){
        menuService.save(menu);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_ADD_SUCCESS);
    }

    /**
     * 更新节点
     * @param menu 要更新的节点
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('menu:update')")
    @PutMapping("/update")
    public ResultEntity updateMenuNode(@RequestBody Menu menu){
        menuService.updateById(menu);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 删除节点
     * @param id 要删除的节点id
     * @return 封装好的数据
     */
    @PreAuthorize("hasAuthority('menu:delete')")
    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteMenuNode(@PathVariable Integer id){
        menuService.removeById(id);
        return ResultEntity.success(null, CrowdConstant.MESSAGE_DELETE_SUCCESS);
    }

}
