package com.phj.crowd.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author phj
 * @since 2020-08-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer pid;

    @JsonProperty("label")
    private String name;

    private String url;

    private String icon;

    private List<MenuVO> children = new ArrayList<>();


}
