package com.phj.crowd.constant;


/**
 * @author 31637
 * @date 2020/7/25 11:05
 */
public class CrowdConstant {
    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_ADMIN_INFO = "adminInfo";
    public static final String ATTR_NAME_MEMBER = "member";
    public static final String ATTR_NAME_PROJECT = "project";
    public static final String MESSAGE_SUCCESS = "SUCCESS";
    public static final String MESSAGE_FILED = "FILED";


    public static final String MESSAGE_LOGIN_FAILED = "抱歉！账号密码错误！请重新输入";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！该账号已存在！";
    public static final String MESSAGE_LOGOUT_ERROR = "用户不存在！";
    public static final String MESSAGE_ACCESS_FORBIDDEN = "请登录之后再访问";
    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误：登录账号不唯一";
    public static final String MESSAGE_DELETE_SUCCESS = "删除成功";
    public static final String MESSAGE_UPDATE_SUCCESS = "修改成功";
    public static final String MESSAGE_ADD_SUCCESS = "添加成功";
    public static final String MESSAGE_OPERATION_SUCCESS = "操作成功";
    public static final String MESSAGE_PHONE_ERROR = "手机号码错误或验证码过期，请重新发送！";
    public static final String MESSAGE_CODE_ERROR = "验证码错误，请检查手机号或验证码！";
    public static final String MESSAGE_CODE_TIME = "验证码将在10分钟后过期";
    public static final String MESSAGE_TEMPLE_PROJECT_MISSING = "项目不存在！";

    public static final String REDIS_CODE_PREFIX = "REDIS_CODE_PREFIX_";

}
