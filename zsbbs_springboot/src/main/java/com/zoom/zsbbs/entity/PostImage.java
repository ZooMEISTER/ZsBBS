package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/29 12:49
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_postimage")
public class PostImage {
    @TableId(type = IdType.AUTO)
    int postimageid;
    int postimagepostid;
    String postimagepath;

    public int getPostimageid() {
        return postimageid;
    }

    public int getPostimagepostid() {
        return postimagepostid;
    }

    public String getPostimagepath() {
        return postimagepath;
    }

    public void setPostimageid(int postimageid) {
        this.postimageid = postimageid;
    }

    public void setPostimagepostid(int postimagepostid) {
        this.postimagepostid = postimagepostid;
    }

    public void setPostimagepath(String postimagepath) {
        this.postimagepath = postimagepath;
    }

    public PostImage(int postimagepostid, String postimagepath) {
        this.postimagepostid = postimagepostid;
        this.postimagepath = postimagepath;
    }


    @Override
    public String toString() {
        return "PostImage{" +
                "postimageid=" + postimageid +
                ", postimagepostid=" + postimagepostid +
                ", postimagepath='" + postimagepath + '\'' +
                '}';
    }
}
