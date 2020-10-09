package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.po.TypePO;
import com.phj.crowd.mapper.TypeMapper;
import com.phj.crowd.service.TypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-09-01
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, TypePO> implements TypeService {

}
