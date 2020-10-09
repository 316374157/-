package com.phj.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLaunchInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;
}
