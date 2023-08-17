package com.zoom.zsbbs.entity;/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 18:12
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("sys_follow")
public class Follow {
    @TableId(type = IdType.AUTO)
    private int followid;

    private int followeduserid;
    private int followeruserid;

    private Date followtime;

    public int getFollowid() {
        return followid;
    }

    public void setFollowid(int followid) {
        this.followid = followid;
    }

    public int getFolloweduserid() {
        return followeduserid;
    }

    public void setFolloweduserid(int followeduserid) {
        this.followeduserid = followeduserid;
    }

    public int getFolloweruserid() {
        return followeruserid;
    }

    public void setFolloweruserid(int followeruserid) {
        this.followeruserid = followeruserid;
    }

    public Date getFollowtime() {
        return followtime;
    }

    public void setFollowtime(Date followtime) {
        this.followtime = followtime;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followid=" + followid +
                ", followeduserid=" + followeduserid +
                ", followeruserid=" + followeruserid +
                ", followtime=" + followtime +
                '}';
    }
}
