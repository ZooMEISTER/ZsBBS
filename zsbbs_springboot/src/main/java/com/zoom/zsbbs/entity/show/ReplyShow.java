package com.zoom.zsbbs.entity.show;

import com.zoom.zsbbs.entity.Reply;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/27 11:05
 *
 */
public class ReplyShow {
    int replyCount;
    List<Reply> replyList;

    public ReplyShow(int replyCount, List<Reply> replyList) {
        this.replyCount = replyCount;
        this.replyList = replyList;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "ReplyShow{" +
                "replyCount=" + replyCount +
                ", replyList=" + replyList +
                '}';
    }
}
