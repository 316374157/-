package com.phj.crowd.service;

import com.phj.crowd.entity.po.OrderProjectPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.crowd.entity.vo.OrderProjectVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-09-05
 */
public interface OrderProjectService extends IService<OrderProjectPO> {

    OrderProjectVO getOrderProjectVO(Integer returnId);

}
