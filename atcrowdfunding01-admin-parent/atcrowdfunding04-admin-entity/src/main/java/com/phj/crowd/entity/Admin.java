package com.phj.crowd.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author phj
 * @since 2020-07-29
 */
@TableName("t_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank
    private String loginAcct;

    @NotBlank
    private String userPswd;

    private String userName;

    private String email;

    private String createTime;
}
