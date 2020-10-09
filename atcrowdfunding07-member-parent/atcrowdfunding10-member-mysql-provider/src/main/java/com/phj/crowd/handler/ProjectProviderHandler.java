package com.phj.crowd.handler;

import com.phj.crowd.entity.vo.DetailProjectVO;
import com.phj.crowd.entity.vo.PortalTypeVO;
import com.phj.crowd.entity.vo.ProjectVO;
import com.phj.crowd.service.ProjectService;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31637
 * @date 2020/9/3 14:18
 */
@RestController
public class ProjectProviderHandler {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/save/ProjectVO/Remote")
    public ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId) {
        try {
            projectService.saveProject(projectVO, memberId);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }

    }

    @RequestMapping("/get/PortalTypeVOList/Remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeVOListRemote() {
        try {
            return ResultEntity.success(projectService.getPortalTypeVOList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId) {
        try {
            DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);
            return ResultEntity.success(detailProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

}
