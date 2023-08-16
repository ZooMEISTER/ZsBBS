package com.zoom.zsbbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@TableName("sys_post")
public class Post {
    @TableId(type = IdType.AUTO)
    private int postid;

    private String title;
    private String content;
    private int authorid;
    private int replycount;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date publishtime;
    private int maxfloor;
    private Date latestreplytime;
    private int containimage;

    public int getPostid() {
        return postid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getAuthorid() {
        return authorid;
    }

    public int getReplycount() {
        return replycount;
    }

    public java.util.Date getPublishtime() {
        return publishtime;
    }

    public int getMaxfloor() {
        return maxfloor;
    }

    public Date getLatestreplytime() {
        return latestreplytime;
    }

    public int getContainimage() {
        return containimage;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(int authorid) {
        this.authorid = authorid;
    }

    public void setReplyCount(int replycount) {
        this.replycount = replycount;
    }

    public void setPublishTime(java.util.Date publishtime) {
        this.publishtime = publishtime;
    }


    public void setMaxfloor(int maxfloor) {
        this.maxfloor = maxfloor;
    }

    public void setLatestreplytime(Date latestreplytime) {
        this.latestreplytime = latestreplytime;
    }

    public void setContainimage(int containimage) {
        this.containimage = containimage;
    }

    public Post() {
    }

    public Post(String title, String content, int authorid, int replycount, int containimage) {
        this.title = title;
        this.content = content;
        this.authorid = authorid;
        this.replycount = replycount;
        this.containimage = containimage;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postid=" + postid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorid=" + authorid +
                ", replycount=" + replycount +
                ", publishtime=" + publishtime +
                ", maxfloor=" + maxfloor +
                ", latestreplytime=" + latestreplytime +
                ", containimage=" + containimage +
                '}';
    }
}
