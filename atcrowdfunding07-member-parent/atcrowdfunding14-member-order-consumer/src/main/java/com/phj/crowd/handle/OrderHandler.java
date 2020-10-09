package com.phj.crowd.handle;

import com.phj.crowd.api.MySQLRemoteService;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.vo.AddressVO;
import com.phj.crowd.entity.vo.MemberVO;
import com.phj.crowd.entity.vo.OrderProjectVO;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 31637
 * @date 2020/9/5 8:31
 */
@RestController
public class OrderHandler {

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/confirm/return/info/{returnId}")
    public ResultEntity<OrderProjectVO> showReturnConfirmInfo(@PathVariable("returnId") Integer returnId) {
        return mySQLRemoteService.getOrderProjectVORemote(returnId);
    }


    @RequestMapping("/get/address/vo/list")
    public ResultEntity<List<AddressVO>> getAddressVOList(HttpSession session) {
        // 1.获取当前已登录用户的 id
        MemberVO memberLoginVO = (MemberVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        Integer memberId = memberLoginVO.getId();
        // 2.查询目前的收货地址数据并返回
        return mySQLRemoteService.getAddressVORemote(memberId);
    }

    @RequestMapping("/add/address/vo")
    public ResultEntity<String> addAddressVO(@RequestBody AddressVO addressVO, HttpSession session) {
        // 1.获取当前已登录用户的 id
        MemberVO memberLoginVO = (MemberVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        Integer memberId = memberLoginVO.getId();
        // 2.插入新的收货地址数据并返回
        return mySQLRemoteService.addAddressVORemote(addressVO,memberId);
    }

}
