package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 22:26
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("sys_subreply")
public class SubReply {
    @TableId(type = IdType.AUTO)
    private int subreplyid;
    private String subreplycontent;
    private int subreplyreplyid;
    private int subreplyauthorid;
    private int subreplytargetauthorid;
    private Date subreplytime;
    private int subreplysubreplyid;

    public int getSubreplyid() {
        return subreplyid;
    }

    public String getSubreplycontent() {
        return subreplycontent;
    }

    public int getSubreplyreplyid() {
        return subreplyreplyid;
    }

    public int getSubreplyauthorid() {
        return subreplyauthorid;
    }

    public int getSubreplytargetauthorid() {
        return subreplytargetauthorid;
    }

    public Date getSubreplytime() {
        return subreplytime;
    }

    public int getSubreplysubreplyid() {
        return subreplysubreplyid;
    }

    public void setSubreplyid(int subreplyid) {
        this.subreplyid = subreplyid;
    }

    public void setSubreplycontent(String subreplycontent) {
        this.subreplycontent = subreplycontent;
    }

    public void setSubreplyreplyid(int subreplyreplyid) {
        this.subreplyreplyid = subreplyreplyid;
    }

    public void setSubreplyauthorid(int subreplyauthorid) {
        this.subreplyauthorid = subreplyauthorid;
    }

    public void setSubreplytargetauthorid(int subreplytargetauthorid) {
        this.subreplytargetauthorid = subreplytargetauthorid;
    }

    public void setSubreplytime(Date subreplytime) {
        this.subreplytime = subreplytime;
    }

    public void setSubreplysubreplyid(int subreplysubreplyid) {
        this.subreplysubreplyid = subreplysubreplyid;
    }

    @Override
    public String toString() {
        return "SubReply{" +
                "subreplyid=" + subreplyid +
                ", subreplycontent='" + subreplycontent + '\'' +
                ", subreplyreplyid=" + subreplyreplyid +
                ", subreplyauthorid=" + subreplyauthorid +
                ", subreplytargetauthorid=" + subreplytargetauthorid +
                ", subreplytime=" + subreplytime +
                ", subreplysubreplyid=" + subreplysubreplyid +
                '}';
    }
}
