package com.phj.crowd.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phj.crowd.entity.po.AddressPO;
import com.phj.crowd.entity.po.OrderPO;
import com.phj.crowd.entity.po.OrderProjectPO;
import com.phj.crowd.entity.vo.AddressVO;
import com.phj.crowd.entity.vo.OrderProjectVO;
import com.phj.crowd.entity.vo.OrderVO;
import com.phj.crowd.service.AddressService;
import com.phj.crowd.service.OrderProjectService;
import com.phj.crowd.service.OrderService;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 31637
 * @date 2020/9/5 8:44
 */
@RestController
public class OrderProviderHandler {

    @Autowired
    OrderProjectService orderProjectService;
    @Autowired
    AddressService addressService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("returnId") Integer returnId){
        try {
            OrderProjectVO orderProjectVO = orderProjectService.getOrderProjectVO(returnId);
            return ResultEntity.success(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/address/vo/remote")
    public ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId){
        List<AddressPO> addressPOList = null;
        try {
            addressPOList = addressService.list(new QueryWrapper<AddressPO>().eq("member_id", memberId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
        List<AddressVO> addressVOList = new ArrayList<>();
        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO, addressVO);
            addressVOList.add(addressVO);
        }
        return ResultEntity.success(addressVOList);
    }

    @RequestMapping("/add/address/vo/remote")
    public ResultEntity<String> addAddressVORemote(@RequestBody AddressVO addressVO, @RequestParam("memberId")Integer memberId){
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO, addressPO);
        addressPO.setMemberId(memberId);
        try {
            addressService.save(addressPO);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @Transactional
    @RequestMapping("/save/order/remote")
    public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO){

        try {
            OrderPO orderPO = new OrderPO();
            BeanUtils.copyProperties(orderVO, orderPO);
            OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
            OrderProjectPO orderProjectPO = new OrderProjectPO();
            BeanUtils.copyProperties(orderProjectVO, orderProjectPO);
            orderService.save(orderPO);
            orderProjectPO.setOrderId(orderPO.getId());
            orderProjectService.save(orderProjectPO);
            return ResultEntity.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }
}
