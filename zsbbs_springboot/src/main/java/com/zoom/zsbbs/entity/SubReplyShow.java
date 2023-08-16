package com.zoom.zsbbs.entity;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/27 0:15
 *
 */

import java.util.List;

public class SubReplyShow {
    int subReplyCount;
    List<SubReply> subReplyList;

    public SubReplyShow(int subReplyCount, List<SubReply> subReplyList) {
        this.subReplyCount = subReplyCount;
        this.subReplyList = subReplyList;
    }

    public int getSubReplyCount() {
        return subReplyCount;
    }

    public List<SubReply> getSubReplyList() {
        return subReplyList;
    }

    public void setSubReplyCount(int subReplyCount) {
        this.subReplyCount = subReplyCount;
    }

    public void setSubReplyList(List<SubReply> subReplyList) {
        this.subReplyList = subReplyList;
    }

    @Override
    public String toString() {
        return "SubReplyShow{" +
                "subReplyCount=" + subReplyCount +
                ", subReplyList=" + subReplyList +
                '}';
    }
}
