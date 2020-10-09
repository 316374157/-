package com.phj.crowd.exception;

/**
 * 访问被拒绝的异常
 * @author 31637
 * @date 2020/8/5 15:42
 */
public class AccessForbiddenException extends RuntimeException {

    public AccessForbiddenException(String message) {
        super(message);
    }
}
