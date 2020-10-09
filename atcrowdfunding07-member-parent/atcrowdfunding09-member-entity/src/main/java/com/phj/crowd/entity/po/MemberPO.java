package com.phj.crowd.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_member")
public class MemberPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    /**
     * 实名认证状态 0 - 未实名认证， 1 - 实名认证申 请中， 2 - 已实名认证
     */
    private Integer authstatus;

    /**
     *  0 - 个人， 1 - 企业
     */
    private Integer usertype;

    private String realname;

    private String cardnum;

    /**
     * 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
     */
    private Integer accttype;

}
