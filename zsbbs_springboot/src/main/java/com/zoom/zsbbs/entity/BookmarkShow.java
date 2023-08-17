package com.zoom.zsbbs.entity;/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 14:43
 *
 */

import java.util.List;

public class BookmarkShow {
    int bookmarkCount;
    List<Bookmark> bookmarkList;

    public BookmarkShow(int bookmarkCount, List<Bookmark> bookmarkList) {
        this.bookmarkCount = bookmarkCount;
        this.bookmarkList = bookmarkList;
    }

    public int getBookmarkCount() {
        return bookmarkCount;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkCount(int bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    @Override
    public String toString() {
        return "BookmarkShow{" +
                "bookmarkCount=" + bookmarkCount +
                ", bookmarkList=" + bookmarkList +
                '}';
    }
}
