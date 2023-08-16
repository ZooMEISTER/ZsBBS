package com.zoom.zsbbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@TableName("sys_reply")
public class Reply {

    @TableId(type = IdType.AUTO)
    private int replyid;
    private String replycontent;
    private int replypostid;
    private int replyauthorid;
    private int replypostauthorid;
    private Date replytime;
    private int floor;
    private int containimage;

    public int getReplyid() {
        return replyid;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public int getReplypostid() {
        return replypostid;
    }

    public int getReplyauthorid() {
        return replyauthorid;
    }

    public int getReplypostauthorid() {
        return replypostauthorid;
    }

    public Date getReplytime() {
        return replytime;
    }

    public int getFloor() {
        return floor;
    }

    public int getContainimage() {
        return containimage;
    }

    public void setReplyid(int replyid) {
        this.replyid = replyid;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

    public void setReplypostid(int replypostid) {
        this.replypostid = replypostid;
    }

    public void setReplyauthorid(int replyauthorid) {
        this.replyauthorid = replyauthorid;
    }

    public void setReplypostauthorid(int replypostsuthorid) {
        this.replypostauthorid = replypostsuthorid;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setContainimage(int containimage) {
        this.containimage = containimage;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyid=" + replyid +
                ", replycontent='" + replycontent + '\'' +
                ", replypostid=" + replypostid +
                ", replyauthorid=" + replyauthorid +
                ", replypostauthorid=" + replypostauthorid +
                ", replytime=" + replytime +
                ", floor=" + floor +
                ", containimage=" + containimage +
                '}';
    }
}
