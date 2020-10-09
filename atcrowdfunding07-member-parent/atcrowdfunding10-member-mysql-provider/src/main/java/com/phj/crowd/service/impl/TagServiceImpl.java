package com.phj.crowd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.crowd.entity.po.TagPO;
import com.phj.crowd.mapper.TagMapper;
import com.phj.crowd.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, TagPO> implements TagService {

}
