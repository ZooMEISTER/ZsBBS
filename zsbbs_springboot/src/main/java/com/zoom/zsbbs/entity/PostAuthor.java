package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

//查询帖子作者时返回的对象
public class PostAuthor {
    private int userid;
    private String avatar;
    private int usertype;
    private String username;
    private int userpostcount;
    private int userreplycount;

    public int getUserid() {
        return userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public int getUsertype() {
        return usertype;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public void setUserpostcount(int userpostcount) {
        this.userpostcount = userpostcount;
    }

    public void setUserreplycount(int userreplycount) {
        this.userreplycount = userreplycount;
    }

    @Override
    public String toString() {
        return "PostAuthor{" +
                "userid=" + userid +
                ", avatar='" + avatar + '\'' +
                ", usertype=" + usertype +
                ", username='" + username + '\'' +
                ", userpostcount=" + userpostcount +
                ", userreplycount=" + userreplycount +
                '}';
    }
}
