package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.po.MemberPO;
import com.phj.crowd.mapper.MemberMapper;
import com.phj.crowd.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-08-21
 */
@Transactional
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberPO> implements MemberService {

}
