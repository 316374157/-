package com.phj.crowd.config;

import com.phj.crowd.utils.ResultEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常控制
 * @author 31637
 * @date 2020/8/3 12:49
 */
@RestControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 其他异常
     * @param e 异常
     * @return 封装好的数据
     */
    @ExceptionHandler(value = Exception.class)
    public ResultEntity exceptionHandler(Exception e){
        return ResultEntity.fail(e.getMessage());
    }

}
