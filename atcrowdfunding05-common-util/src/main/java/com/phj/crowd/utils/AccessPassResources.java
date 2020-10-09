package com.phj.crowd.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 31637
 * @date 2020/8/26 22:56
 */
public class AccessPassResources {

    public static final Set<String> PASS_RES_SET = new HashSet<>();

    static {
        PASS_RES_SET.add("/");
        PASS_RES_SET.add("/auth/member/login");
        PASS_RES_SET.add("/auth/member/logout");
        PASS_RES_SET.add("/auth/do/member/register");
        PASS_RES_SET.add("/auth/member/send/code/Message");
    }
}
