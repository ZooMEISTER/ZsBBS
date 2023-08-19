package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/8/19 15:15
 *
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("sys_section")
public class Section {
    @TableId(type = IdType.AUTO)
    private int sectionid;

    private String sectionavatar;
    private String sectionname;
    private String sectiondescription;
    private Date sectioncreatetime;

    public int getSectionid() {
        return sectionid;
    }

    public String getSectionavatar() {
        return sectionavatar;
    }

    public String getSectionname() {
        return sectionname;
    }

    public String getSectiondescription() {
        return sectiondescription;
    }

    public Date getSectioncreatetime() {
        return sectioncreatetime;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public void setSectionavatar(String sectionavatar) {
        this.sectionavatar = sectionavatar;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public void setSectiondescription(String sectiondescription) {
        this.sectiondescription = sectiondescription;
    }

    public void setSectioncreatetime(Date sectioncreatetime) {
        this.sectioncreatetime = sectioncreatetime;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionid=" + sectionid +
                ", sectionavatar='" + sectionavatar + '\'' +
                ", sectionname='" + sectionname + '\'' +
                ", sectiondescription='" + sectiondescription + '\'' +
                ", sectioncreatetime=" + sectioncreatetime +
                '}';
    }
}
