package com.zoom.zsbbs.entity;


/*
 *    Created by ZooMEISTER
 *    on 2023/6/30 00:50
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("sys_replyimage")
public class ReplyImage {
    @TableId(type = IdType.AUTO)
    int replyimageid;
    int replyimagereplyid;
    String replyimagepath;

    public int getReplyimageid() {
        return replyimageid;
    }

    public int getReplyimagereplyid() {
        return replyimagereplyid;
    }

    public String getReplyimagepath() {
        return replyimagepath;
    }

    public void setReplyimageid(int replyimageid) {
        this.replyimageid = replyimageid;
    }

    public void setReplyimagereplyid(int replyimagereplyid) {
        this.replyimagereplyid = replyimagereplyid;
    }

    public void setReplyimagepath(String replyimagepath) {
        this.replyimagepath = replyimagepath;
    }

    public ReplyImage(int replyimagereplyid, String replyimagepath) {
        this.replyimagereplyid = replyimagereplyid;
        this.replyimagepath = replyimagepath;
    }

    @Override
    public String toString() {
        return "ReplyImage{" +
                "replyimageid=" + replyimageid +
                ", replyimagereplyid=" + replyimagereplyid +
                ", replyimagepath='" + replyimagepath + '\'' +
                '}';
    }
}
