package com.phj.crowd.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动生成时间字段
 * @author 31637
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充
     * @param metaObject 参数
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

    /**
     * 更新填充
     * @param metaObject 参数
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}