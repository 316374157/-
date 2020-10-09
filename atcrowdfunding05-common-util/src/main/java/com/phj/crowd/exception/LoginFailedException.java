package com.phj.crowd.exception;

/**
 * 自定义的登陆失败的异常
 * @author 31637
 * @date 2020/7/31 17:33
 */
public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message) {
        super(message);
    }
}
