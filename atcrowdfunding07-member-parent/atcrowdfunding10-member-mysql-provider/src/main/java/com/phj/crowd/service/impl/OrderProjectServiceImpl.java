package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.po.OrderProjectPO;
import com.phj.crowd.entity.vo.OrderProjectVO;
import com.phj.crowd.mapper.OrderProjectMapper;
import com.phj.crowd.service.OrderProjectService;
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
public class OrderProjectServiceImpl extends ServiceImpl<OrderProjectMapper, OrderProjectPO> implements OrderProjectService {

    @Override
    public OrderProjectVO getOrderProjectVO(Integer returnId) {
        return this.baseMapper.selectOrderProjectVO(returnId);
    }
}
