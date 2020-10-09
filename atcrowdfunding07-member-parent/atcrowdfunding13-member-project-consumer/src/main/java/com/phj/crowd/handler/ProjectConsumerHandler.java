package com.phj.crowd.handler;

import com.phj.crowd.api.MySQLRemoteService;
import com.phj.crowd.config.OSSProperties;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.vo.*;
import com.phj.crowd.utils.CrowdUtil;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 31637
 * @date 2020/9/2 9:45
 */
@RestController
public class ProjectConsumerHandler {

    @Autowired
    OSSProperties ossProperties;
    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/upload/one/image")
    public ResultEntity<String> uploadOneImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return CrowdUtil.uploadFileToOss(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret(),
                multipartFile.getInputStream(), ossProperties.getBucketName(),
                ossProperties.getBucketDomain(), Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    @RequestMapping("/save/project/basicinfo")
    public ResultEntity<String> saveProjectBasicInfo(@RequestBody ProjectVO projectVO, HttpSession session) {
        session.setAttribute(CrowdConstant.ATTR_NAME_PROJECT, projectVO);
        return ResultEntity.success(null);
    }

    @RequestMapping("/save/project/return")
    public ResultEntity<String> saveProjectReturn(@RequestBody ReturnVO returnVO, HttpSession session) {
        try {
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_PROJECT);
            if (projectVO == null) {
                return ResultEntity.fail(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            if (returnVOList == null || returnVOList.size() == 0) {
                returnVOList = new ArrayList<>();
                projectVO.setReturnVOList(returnVOList);
            }
            returnVOList.add(returnVO);
            session.setAttribute(CrowdConstant.ATTR_NAME_PROJECT, projectVO);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/project/returnlist")
    public ResultEntity<List<ReturnVO>> getProjectReturnList(HttpSession session) {
        try {
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_PROJECT);
            if (projectVO == null) {
                return ResultEntity.fail(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            if (returnVOList == null || returnVOList.size() == 0) {
                returnVOList = new ArrayList<>();
                projectVO.setReturnVOList(returnVOList);
            }
            session.setAttribute(CrowdConstant.ATTR_NAME_PROJECT, projectVO);
            return ResultEntity.success(returnVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/project/over")
    public ResultEntity<String> saveProjectOver(HttpSession session,@RequestBody MemberConfirmInfoVO memberConfirmInfoVO) {
        // 1.从 Session 域读取之前临时存储的 ProjectVO 对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_PROJECT);
        // 2.如果 projectVO 为 null
        if (projectVO == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }
        // 3.将确认信息数据设置到 projectVO 对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
        // 4.从 Session 域读取当前登录的用户
        MemberVO memberVO = (MemberVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        Integer memberId = memberVO.getId();
        // 5.调用远程方法保存 projectVO 对象
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO, memberId);
        // 6.判断远程的保存操作是否成功
        String result = saveResultEntity.getResult();
        if (CrowdConstant.MESSAGE_FILED.equals(result)) {
            return saveResultEntity;
        }
        // 7.将临时的 ProjectVO 对象从 Session 域移除
        session.removeAttribute(CrowdConstant.ATTR_NAME_PROJECT);
        // 8.如果远程保存成功则跳转到最终完成页面
        return saveResultEntity;
    }

    @RequestMapping("/get/project/detail/{projectId}")
    public ResultEntity<DetailProjectVO> getProjectDetail(@PathVariable("projectId") Integer projectId) {
        return mySQLRemoteService.getDetailProjectVORemote(projectId);
    }

}