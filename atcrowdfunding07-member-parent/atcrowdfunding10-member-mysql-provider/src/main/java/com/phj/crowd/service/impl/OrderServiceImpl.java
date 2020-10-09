package com.phj.crowd.service.impl;

import com.phj.crowd.entity.po.OrderPO;
import com.phj.crowd.entity.vo.OrderVO;
import com.phj.crowd.mapper.OrderMapper;
import com.phj.crowd.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-09-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderPO> implements OrderService {

}
