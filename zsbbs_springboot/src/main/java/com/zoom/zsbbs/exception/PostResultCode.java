package com.zoom.zsbbs.exception;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

public interface PostResultCode {
    public static int ADD_NEW_POST_SUCCESS = 11000;
    public static int ADD_NEW_POST_FAIL = 11001;
    public static int ADD_NEW_REPLY_SUCCESS = 11000;
    public static int ADD_NEW_REPLY_FAIL = 11001;
    public static int ADD_NEW_SUBREPLY_SUCCESS = 11000;
    public static int ADD_NEW_SUBREPLY_FAIL = 11001;
    public static int DELETE_SUBREPLY_SUCCESS = 11002;
    public static int DELETE_SUBREPLY_FAIL = 11003;
}
