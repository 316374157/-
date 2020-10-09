package com.phj.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 31637
 * @date 2020/9/3 17:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortalTypeVO implements Serializable {

    private static final long serialVersionUID = 6606206643052163348L;
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类介绍
     */
    private String remark;

    private List<PortalProjectVO> portalProjectVOList;
}
