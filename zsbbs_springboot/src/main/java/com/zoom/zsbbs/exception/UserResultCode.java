package com.zoom.zsbbs.exception;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

public interface UserResultCode {

    public static int UPDATE_SECCESS = 10000;
    public static int UPDATE_FAIL = 10004;
    public static int LOGIN_SECCESS = 10000;
    public static int LOGIN_FAIL_USER_NOT_EXIST = 10001;
    public static int LOGIN_FAIL_WRONG_PASSWORD = 10002;
    public static int REGISTER_FAIL_USER_ALREADY_EXIST = 10003;
    public static int LOGIN_FAIL_TOKEN_EXPIRED = 10005;
}
