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
 * @since 2020-08-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phoneNum;

    private String code;

    /**
     * 实名认证状态 0 - 未实名认证， 1 - 实名认证申 请中， 2 - 已实名认证
     */
    private Integer authstatus;


}
