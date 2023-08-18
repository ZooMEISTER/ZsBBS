package com.zoom.zsbbs.entity.show;

import com.zoom.zsbbs.entity.Post;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 17:16
 *
 *      查询帖子的返回对象，包括所有帖子的数量和一个post list
 */
public class PostShow {
    private int count;
    private List<Post> postList;

    public PostShow(int count, List<Post> postList) {
        this.count = count;
        this.postList = postList;
    }

    public int getCount() {
        return count;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "PostShow{" +
                "count=" + count +
                ", postList=" + postList +
                '}';
    }
}
