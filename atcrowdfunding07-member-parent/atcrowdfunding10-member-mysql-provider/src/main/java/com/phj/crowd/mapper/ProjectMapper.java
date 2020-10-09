package com.phj.crowd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.crowd.entity.po.ProjectPO;
import com.phj.crowd.entity.vo.DetailProjectVO;
import com.phj.crowd.entity.vo.PortalProjectVO;
import com.phj.crowd.entity.vo.PortalTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
public interface ProjectMapper extends BaseMapper<ProjectPO> {

    void insertTypeRelationship(@Param("projectId") Integer projectId,@Param("typeIdList") List<Integer> typeIdList);

    void insertTagRelationship(@Param("projectId") Integer projectId,@Param("tagIdList") List<Integer> tagIdList);

    List<PortalTypeVO> selectPortalTypeVOList();

    List<PortalProjectVO> selectPortalProjectVOList();

    DetailProjectVO selectDetailProjectVO(@Param("projectId") Integer projectId);

}
