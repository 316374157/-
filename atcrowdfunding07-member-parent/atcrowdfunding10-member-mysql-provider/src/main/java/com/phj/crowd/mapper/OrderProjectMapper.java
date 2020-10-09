package com.phj.crowd.mapper;

import com.phj.crowd.entity.po.OrderProjectPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.crowd.entity.vo.OrderProjectVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phj
 * @since 2020-09-05
 */
public interface OrderProjectMapper extends BaseMapper<OrderProjectPO> {

    OrderProjectVO selectOrderProjectVO(Integer returnId);
}
