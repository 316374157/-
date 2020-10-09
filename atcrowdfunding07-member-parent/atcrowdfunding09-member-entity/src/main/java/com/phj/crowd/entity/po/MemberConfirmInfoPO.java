package com.phj.crowd.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
@TableName("t_member_confirm_info")
public class MemberConfirmInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员 id
     */
    private Integer memberid;

    /**
     * 易付宝企业账号
     */
    private String paynum;

    /**
     * 法人身份证号
     */
    private String cardnum;

    /**
     * 项目id
     */
    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
    public String getPaynum() {
        return paynum;
    }

    public void setPaynum(String paynum) {
        this.paynum = paynum;
    }
    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "MemberConfirmInfoPO{" +
                "id=" + id +
                ", memberid=" + memberid +
                ", paynum='" + paynum + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
