package com.phj.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 31637
 * @date 2020/8/15 12:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleFrom {

    @NotNull
    private Integer id;
    private List<Integer> roleIdList;

}
