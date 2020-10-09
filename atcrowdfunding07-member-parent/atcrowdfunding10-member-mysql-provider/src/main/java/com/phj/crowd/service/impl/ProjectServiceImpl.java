package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.po.*;
import com.phj.crowd.entity.vo.*;
import com.phj.crowd.mapper.ProjectMapper;
import com.phj.crowd.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectPO> implements ProjectService {

    @Autowired
    ProjectItemPicService projectItemPicService;
    @Autowired
    ReturnService returnService;
    @Autowired
    MemberLaunchInfoService memberLaunchInfoService;
    @Autowired
    MemberConfirmInfoService memberConfirmInfoService;

    @Transactional
    @Override
    public void saveProject(ProjectVO projectVO, Integer memberId) {

        /**
         * 保存项目信息
         */
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(projectVO, projectPO);
        projectPO.setMemberid(memberId);
        projectPO.setCreatedate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        projectPO.setStatus(0);
        projectPO.setCompletion(0);
        projectPO.setSupporter(0);
        projectPO.setSupportmoney(0L);
        projectPO.setFollower(0);
        this.baseMapper.insert(projectPO);
        Integer projectId = projectPO.getId();

        /**
         * 保存类型信息
         */
        this.baseMapper.insertTypeRelationship(projectId,projectVO.getTypeIdList());
        /**
         * 保存标签信息
         */
        this.baseMapper.insertTagRelationship(projectId,projectVO.getTagIdList());

        /**
         * 保存项目发起人信息
         */
        MemberLaunchInfoVO memberLaunchInfoVO = projectVO.getMemberLaunchInfoVO();
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        BeanUtils.copyProperties(memberLaunchInfoVO, memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberid(memberId);
        memberLaunchInfoPO.setProjectId(projectId);
        memberLaunchInfoService.save(memberLaunchInfoPO);
        /**
         * 保存会员确定信息
         */
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO, memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberid(memberId);
        memberConfirmInfoPO.setProjectId(projectId);
        memberConfirmInfoService.save(memberConfirmInfoPO);

        /**
         * 保存详细图片信息
         */
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        List<ProjectItemPicPO> projectItemPicPOList = new ArrayList<>();
        detailPicturePathList.forEach(item ->{
            ProjectItemPicPO projectItemPicPO = new ProjectItemPicPO();
            projectItemPicPO.setItemPicPath(item);
            projectItemPicPO.setProjectid(projectId);
            projectItemPicPOList.add(projectItemPicPO);
        });
        projectItemPicService.saveBatch(projectItemPicPOList);

        /**
         * 保存回报结果信息
         */
        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        List<ReturnPO> returnList = new ArrayList<>();
        returnVOList.forEach(item ->{
            ReturnPO returnPO = new ReturnPO();
            BeanUtils.copyProperties(item, returnPO);
            returnPO.setProjectid(projectId);
            returnList.add(returnPO);
        });
        returnService.saveBatch(returnList);

    }

    @Override
    public List<PortalTypeVO> getPortalTypeVOList() {
        return this.baseMapper.selectPortalTypeVOList();
    }

    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {
        DetailProjectVO detailProjectVO = this.baseMapper.selectDetailProjectVO(projectId);

        Integer status = detailProjectVO.getStatus();
        switch (status) {
            case 0:
                detailProjectVO.setStatusText("审核中");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
                break;
            case 2:
                detailProjectVO.setStatusText("众筹成功");
                break;
            case 3:
                detailProjectVO.setStatusText("已关闭");
                break;
            default:
                break;
        }

        try {
            String deployDate = detailProjectVO.getDeployDate();
            long deployDateTime = new SimpleDateFormat("yyyy-MM-dd").parse(deployDate).getTime();

            Date date = new Date();
            long time = date.getTime();

            long pastDays = (time - deployDateTime) / 1000 / 60 / 60 / 24;
            Integer day = detailProjectVO.getDay();

            Integer lastDay = (int) (day - pastDays);
            detailProjectVO.setLastDay(lastDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return detailProjectVO;
    }

}
