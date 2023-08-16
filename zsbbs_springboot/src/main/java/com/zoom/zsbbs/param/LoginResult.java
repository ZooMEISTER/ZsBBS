package com.zoom.zsbbs.param;

import com.zoom.zsbbs.exception.UserResultCode;

import java.util.HashMap;
import java.util.Map;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

public class LoginResult {
    private boolean success;
    private int code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static LoginResult ok(){
        LoginResult r = new LoginResult();
        r.setSuccess(true);
        r.setMessage("Login Success");
        r.setCode(UserResultCode.LOGIN_SECCESS);
        return r;
    }

    public static LoginResult error(int resCode){
        LoginResult r = new LoginResult();
        r.setSuccess(false);
        r.setMessage("Login Fail");
        r.setCode(resCode);
        return r;
    }

    public LoginResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public LoginResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
