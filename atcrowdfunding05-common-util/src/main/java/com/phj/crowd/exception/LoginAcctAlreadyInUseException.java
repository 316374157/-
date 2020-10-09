package com.phj.crowd.exception;

/**
 * @author 31637
 * @date 2020/8/6 16:38
 */
public class LoginAcctAlreadyInUseException extends RuntimeException {
    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }
}
