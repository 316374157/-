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
public class AuthVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer categoryId;
    private String name;
    @JsonProperty("label")
    private String title;
    private List<AuthVO> children = new ArrayList<>();

}
