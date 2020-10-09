package com.phj.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31637
 * @date 2020/9/5 9:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO implements Serializable {
    private static final long serialVersionUID = -7963516931945701520L;

    private Integer id;
    private String receiveName;
    private String phoneNum;
    private String address;
    private Integer memberId;
}
