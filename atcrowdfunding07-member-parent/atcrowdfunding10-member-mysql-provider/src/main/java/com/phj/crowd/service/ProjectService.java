package com.phj.crowd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.po.ProjectPO;
import com.phj.crowd.entity.vo.DetailProjectVO;
import com.phj.crowd.entity.vo.PortalTypeVO;
import com.phj.crowd.entity.vo.ProjectVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
public interface ProjectService extends IService<ProjectPO> {
    /**
     * 保存项目信息
     * @param projectVO 项目信息
     * @param memberId 用户id
     */
    void saveProject(ProjectVO projectVO, Integer memberId);

    /**
     * 获取首页数据
     * @return 首页数据
     */
    List<PortalTypeVO> getPortalTypeVOList();

    /**
     * 获取项目详情数据
     * @param projectId 项目id
     * @return 项目详情数据
     */
    DetailProjectVO getDetailProjectVO(Integer projectId);
}
