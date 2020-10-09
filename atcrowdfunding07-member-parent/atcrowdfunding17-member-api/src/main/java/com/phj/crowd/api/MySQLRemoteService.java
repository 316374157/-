package com.phj.crowd.api;

import com.phj.crowd.entity.po.MemberPO;
import com.phj.crowd.entity.vo.*;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 31637
 * @date 2020/8/21 16:51
 */
@FeignClient("phj-crowd-mysql")
public interface MySQLRemoteService {
    /**
     * 通过账号登录的远程api
     * @param loginacct 账号
     * @return 成功或者失败的返回数据
     */
    @RequestMapping("/get/memberpo/by/loginacct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    /**
     * 注册用户
     * @param memberPO 要注册的用户
     * @return 成功或者失败的返回数据
     */
    @RequestMapping("/save/Member/remote")
    public ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO);

    @RequestMapping("/save/ProjectVO/Remote")
    public ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/PortalTypeVOList/Remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeVOListRemote();

    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

    @RequestMapping("/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("returnId") Integer returnId);

    @RequestMapping("/get/address/vo/remote")
    public ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/add/address/vo/remote")
    public ResultEntity<String> addAddressVORemote(@RequestBody AddressVO addressVO, @RequestParam("memberId")Integer memberId);

    @RequestMapping("/save/order/remote")
    public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);
}
