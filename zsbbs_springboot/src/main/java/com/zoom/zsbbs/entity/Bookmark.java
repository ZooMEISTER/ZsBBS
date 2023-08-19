package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 12:02
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("sys_bookmark")
public class Bookmark {
    @TableId(type = IdType.AUTO)
    private int bookmarkid;

    private int bookmarkpostid;
    private String bookmarkposttitle;
    private String bookmarkpostcontent;
    private int bookmarkpostauthorid;
    private int bookmarkby;
    private Date bookmarktime;
    private int bookmarkpostsectionid;

    public int getBookmarkid() {
        return bookmarkid;
    }

    public int getBookmarkpostid() {
        return bookmarkpostid;
    }

    public String getBookmarkposttitle() {
        return bookmarkposttitle;
    }

    public String getBookmarkpostcontent() {
        return bookmarkpostcontent;
    }

    public int getBookmarkpostauthorid() {
        return bookmarkpostauthorid;
    }

    public int getBookmarkby() {
        return bookmarkby;
    }

    public Date getBookmarktime() {
        return bookmarktime;
    }

    public int getBookmarkpostsectionid() {
        return bookmarkpostsectionid;
    }

    public void setBookmarkid(int bookmarkid) {
        this.bookmarkid = bookmarkid;
    }

    public void setBookmarkpostid(int bookmarkpostid) {
        this.bookmarkpostid = bookmarkpostid;
    }

    public void setBookmarkposttitle(String bookmarkposttitle) {
        this.bookmarkposttitle = bookmarkposttitle;
    }

    public void setBookmarkpostcontent(String bookmarkpostcontent) {
        this.bookmarkpostcontent = bookmarkpostcontent;
    }

    public void setBookmarkpostauthorid(int bookmarkpostauthorid) {
        this.bookmarkpostauthorid = bookmarkpostauthorid;
    }

    public void setBookmarkby(int bookmarkby) {
        this.bookmarkby = bookmarkby;
    }

    public void setBookmarktime(Date bookmarktime) {
        this.bookmarktime = bookmarktime;
    }

    public void setBookmarkpostsectionid(int bookmarkpostsectionid) {
        this.bookmarkpostsectionid = bookmarkpostsectionid;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "bookmarkid=" + bookmarkid +
                ", bookmarkpostid=" + bookmarkpostid +
                ", bookmarkposttitle='" + bookmarkposttitle + '\'' +
                ", bookmarkpostcontent='" + bookmarkpostcontent + '\'' +
                ", bookmarkpostauthorid=" + bookmarkpostauthorid +
                ", bookmarkby=" + bookmarkby +
                ", bookmarktime=" + bookmarktime +
                ", bookmarkpostsectionid=" + bookmarkpostsectionid +
                '}';
    }
}
