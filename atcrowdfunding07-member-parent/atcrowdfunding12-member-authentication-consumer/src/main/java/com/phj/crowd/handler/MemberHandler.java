package com.phj.crowd.handler;

import com.phj.crowd.api.MySQLRemoteService;
import com.phj.crowd.api.RedisRemoteService;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.po.MemberPO;
import com.phj.crowd.entity.vo.MemberVO;
import com.phj.crowd.utils.CrowdUtil;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 31637
 * @date 2020/8/25 16:52
 */
@RestController
public class MemberHandler {

    private final MySQLRemoteService mySQLRemoteService;
    private final RedisRemoteService redisRemoteService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberHandler(MySQLRemoteService mySQLRemoteService, RedisRemoteService redisRemoteService) {
        this.mySQLRemoteService = mySQLRemoteService;
        this.redisRemoteService = redisRemoteService;
    }

    /**
     * 注册账号
     * @param memberVO 要注册的用户信息
     * @return 注册信息提示
     */
    @RequestMapping("/auth/do/member/register")
    public ResultEntity<String> register(@RequestBody MemberVO memberVO){
        ResultEntity<String> redisData = redisRemoteService.getRedisStringValueByKey(CrowdConstant.REDIS_CODE_PREFIX + memberVO.getPhoneNum());
        // 获取不到验证码
        if(CrowdConstant.MESSAGE_FILED.equals(redisData.getResult())){
            return ResultEntity.fail(CrowdConstant.MESSAGE_PHONE_ERROR);
        }
        // 将验证码转换成小写，然后对比
        if(!memberVO.getCode().toLowerCase().equals(redisData.getData().toString().toLowerCase())){
            return ResultEntity.fail(CrowdConstant.MESSAGE_CODE_ERROR);
        }
        // 对比成功后删除redis中存的验证码
        redisRemoteService.removeRedisByKey(CrowdConstant.REDIS_CODE_PREFIX + memberVO.getPhoneNum());
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO, memberPO);
        // 加秘密码
        memberPO.setUserpswd(passwordEncoder.encode(memberPO.getUserpswd()));
        memberPO.setAuthstatus(0);
        return mySQLRemoteService.saveMemberRemote(memberPO);
    }

    /**
     * 获取验证码
     * @param phoneNum 手机号
     * @return 验证码或者错误提示
     */
    @RequestMapping("/auth/member/send/code/Message")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
        String code = CrowdUtil.codeMessage();
        try {
            redisRemoteService.setRedisKeyValueWithTimeoutRemote(CrowdConstant.REDIS_CODE_PREFIX + phoneNum, code, (long) (1000 * 60 * 10));
            return ResultEntity.success(code,CrowdConstant.MESSAGE_CODE_TIME);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    /**
     * 登录
     * @param memberVO 要登录的用户
     * @return 成功或者失败的信息
     */
    @RequestMapping("/auth/member/login")
    public ResultEntity login(@RequestBody MemberVO memberVO, HttpSession session){
        ResultEntity<MemberPO> dataUserResult = mySQLRemoteService.getMemberPOByLoginAcctRemote(memberVO.getLoginacct());
        // 获取不到用户信息
        if(CrowdConstant.MESSAGE_FILED.equals(dataUserResult.getResult())){
            return dataUserResult;
        }
        MemberPO memberPO = dataUserResult.getData();
        // 判断密码是否正确,错误返回错误信息
        if (!passwordEncoder.matches(memberVO.getUserpswd(),memberPO.getUserpswd())) {
            return ResultEntity.fail(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        memberPO.setUserpswd(null);
        BeanUtils.copyProperties(memberPO, memberVO);
        session.setAttribute(CrowdConstant.ATTR_NAME_MEMBER, memberVO);
        return ResultEntity.success(memberVO);
    }

    /**
     * 登出
     * @return 成功或者失败的信息
     */
    @RequestMapping("/auth/member/logout")
    public ResultEntity logout(HttpSession session){
        session.removeAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        session.removeAttribute(CrowdConstant.ATTR_NAME_PROJECT);
        session.invalidate();
        return ResultEntity.success(null);
    }

}
