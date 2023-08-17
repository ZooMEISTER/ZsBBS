package com.zoom.zsbbs.entity;/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 22:01
 *
 */

import java.util.List;

public class FollowShow {
    int followCount;
    List<Follow> followList;

    public FollowShow(int followCount, List<Follow> followList) {
        this.followCount = followCount;
        this.followList = followList;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }

    @Override
    public String toString() {
        return "FollowShow{" +
                "followCount=" + followCount +
                ", followList=" + followList +
                '}';
    }
}
