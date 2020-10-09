package com.phj.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31637
 * @date 2020/9/3 17:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO implements Serializable {
    private static final long serialVersionUID = -814977791007927279L;

    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 头图路径
     */
    private String headerPicturePath;
    /**
     * 筹集金额
     */
    private Long money;
    /**
     * 项目发起时间
     */
    private String deployDate;
    /**
     * 筹集天数
     */
    private Integer day;
    /**
     * 支持人数
     */
    private Integer supporter;

    /**
     * 百分比完成度
     */
    private Integer percentage;



}
