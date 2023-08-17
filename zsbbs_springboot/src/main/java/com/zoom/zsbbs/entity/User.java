package com.zoom.zsbbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@TableName("sys_usertable")
public class User {
    @TableId(type = IdType.AUTO)
    private int userid;

    private String avatar;

    //0 for user, 1 for admin
    private int usertype;
    private String username;
    private String password;
    private String description;
    private int userpostcount;
    private int userreplycount;


    public int getUserid() {
        return userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getDescription() {
        return description;
    }
    public int getUserpostcount() {
        return userpostcount;
    }

    public int getUserreplycount() {
        return userreplycount;
    }



    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserpostcount(int userpostcount) {
        this.userpostcount = userpostcount;
    }

    public void setUserreplycount(int userreplycount) {
        this.userreplycount = userreplycount;
    }



    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", avatar='" + avatar + '\'' +
                ", usertype=" + usertype +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userpostcount=" + userpostcount +
                ", userreplycount=" + userreplycount +
                ", description='" + description + '\'' +
                '}';
    }
}
