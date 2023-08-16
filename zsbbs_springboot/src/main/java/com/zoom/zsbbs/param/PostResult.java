package com.zoom.zsbbs.param;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

public class PostResult {
    private boolean success;
    private int code;

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PostResult{" +
                "success=" + success +
                ", code=" + code +
                '}';
    }

    public static PostResult ok(){
        PostResult r = new PostResult();
        r.setSuccess(true);
        r.setCode(11000);
        return r;
    }

    public static PostResult error(){
        PostResult r = new PostResult();
        r.setSuccess(false);
        r.setCode(11001);
        return r;
    }
}
