package com.phj.crowd.handler;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.po.MemberPO;
import com.phj.crowd.service.MemberService;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-08-21
 */
@RestController
public class MemberProviderHandler {

    private final MemberService memberService;

    public MemberProviderHandler(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/get/memberpo/by/loginacct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
        try {
            MemberPO memberPO = memberService.getOne(new QueryWrapper<MemberPO>().eq("loginacct", loginacct));
            return ResultEntity.success(memberPO);
        } catch (Exception e) {
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/Member/remote")
    public ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO){
        try {
            memberService.save(memberPO);
            return ResultEntity.success(null);
        } catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                return ResultEntity.fail(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return ResultEntity.fail(e.getMessage());
        }
    }


}
