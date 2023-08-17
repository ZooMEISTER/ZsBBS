package com.zoom.zsbbs.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zoom.zsbbs.entity.Bookmark;
import com.zoom.zsbbs.entity.Post;
import com.zoom.zsbbs.entity.Reply;
import com.zoom.zsbbs.mapper.BookmarkMapper;
import com.zoom.zsbbs.mapper.PostMapper;
import com.zoom.zsbbs.mapper.ReplyMapper;
import com.zoom.zsbbs.param.PostResult;
import com.zoom.zsbbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private BookmarkMapper bookmarkMapper;

    @Override
    public int getAllPostCount() {
        return postMapper.getAllPostCount();
    }

    @Override
    public int getAllPostCountByUserid(int userid) {
        return postMapper.getAllPostCountByUserid(userid);
    }

    @Override
    public List<Post> queryAllPost() {
        //List<Post> postList = postMapper.selectList(null);
        List<Post> postList = postMapper.queryAllPost();
        System.out.println(postList);
        return postList;
    }

    @Override
    public List<Post> searchPost(String searchby, int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.searchPost(searchby, startnum, pagesize);
    }

    @Override
    public int getSearchPostCount(String searchby) {
        return postMapper.getSearchPostCount(searchby);
    }

    @Override
    public List<Post> queryAllPostByLatestreplytimeInAsc() {
        return postMapper.queryAllPostByLatestreplytimeInAsc();
    }

    @Override
    public List<Post> queryAllPostByLatestreplytimeInDesc() {
        return postMapper.queryAllPostByLatestreplytimeInDesc();
    }

    @Override
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInAsc(int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByLatestreplytimeInAsc(startnum, pagesize);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInDesc(int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByLatestreplytimeInDesc(startnum, pagesize);
    }

    @Override
    public List<Post> queryAllPostByPublishtimeInAsc() {
        return postMapper.queryAllPostByPublishtimeInAsc();
    }

    @Override
    public List<Post> queryAllPostByPublishtimeInDesc() {
        return postMapper.queryAllPostByPublishtimeInDesc();
    }

    @Override
    public List<Post> queryAllPostAtPagenumByPublishtimeInAsc(int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByPublishtimeInAsc(startnum, pagesize);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByPublishtimeInDesc(int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByPublishtimeInDesc(startnum, pagesize);
    }

    @Override
    public List<Post> queryAllPostByLatestreplytimeInAscByUserid(int userid) {
        return postMapper.queryAllPostByLatestreplytimeInAscByUserid(userid);
    }

    @Override
    public List<Post> queryAllPostByLatestreplytimeInDescByUserid(int userid) {
        return postMapper.queryAllPostByLatestreplytimeInDescByUserid(userid);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInAscByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByLatestreplytimeInAscByUserid(startnum, pagesize, userid);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInDescByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByLatestreplytimeInDescByUserid(startnum, pagesize, userid);
    }

    @Override
    public List<Post> queryAllPostByPublishtimeInAscByUserid(int userid) {
        return postMapper.queryAllPostByPublishtimeInAscByUserid(userid);
    }

    @Override
    public List<Post> queryAllPostByPublishtimeInDescByUserid(int userid) {
        return postMapper.queryAllPostByPublishtimeInDescByUserid(userid);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByPublishtimeInAscByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByPublishtimeInAscByUserid(startnum, pagesize, userid);
    }

    @Override
    public List<Post> queryAllPostAtPagenumByPublishtimeInDescByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return postMapper.queryAllPostAtPagenumByPublishtimeInDescByUserid(startnum, pagesize, userid);
    }

    @Override
    public int insertPost(Post post) {
        //System.out.println("post.authorid " +  post.getAuthorid());
        postMapper.insert(post);
        return post.getPostid();
    }

    @Override
    public Post queryPostInfoByPostid(int postid) {
        return postMapper.selectById(postid);
    }

    @Override
    public PostResult deletePostByPostid(int postid){
        if(postMapper.deleteById(postid) > 0){
            return PostResult.ok();
        }
        else {
            return PostResult.error();
        }
    }

    @Override
    public PostResult addNewPost(String string_post) {
//        System.out.println("json_post " + string_post);
//        Gson gson = new Gson();
//        JsonObject json_post = new JsonParser().parse(string_post).getAsJsonObject();
//        Post post = new Post(
//                String.valueOf(json_post.get("title")).substring(1, String.valueOf(json_post.get("title")).length() - 1),
//                String.valueOf(json_post.get("content")).substring(1, String.valueOf(json_post.get("content")).length() - 1),
//                Integer.parseInt(String.valueOf(json_post.get("authorid"))),
//                Integer.parseInt(String.valueOf(json_post.get("replycount")))
//                Integer.parseInt(String.valueOf(json_post.get("replycount")));
//
//        System.out.println(post.getTitle());
//        System.out.println(post.getContent());
//        System.out.println(post.getAuthorid());
//        System.out.println(post.getReplycount());
//
//        int publishPostRes = postMapper.insert(post);
//        if(post.getPostid() > 0){
//            return PostResult.ok();
//        }
//        else {
//            return PostResult.error();
//        }
        return PostResult.ok();
    }

    @Override
    public boolean queryIsThisPostBookmarkedUserid(int postid, int userid) {
        if(bookmarkMapper.queryIsThisPostBookmarkedUserid(postid, userid) >= 1){
            return true;
        }
        return false;
    }

    @Override
    public int addBookmark(Bookmark bookmark) {
        return bookmarkMapper.insert(bookmark);
    }

    @Override
    public int delBookmark(int postid, int userid) {
        return bookmarkMapper.delBookmark(postid, userid);
    }

    @Override
    public int getAllBookmarkedPostCountByUserid(int userid) {
        return bookmarkMapper.getAllBookmarkedPostCountByUserid(userid);
    }

    @Override
    public List<Bookmark> queryAllBookmarkedPostByUserid(int userid, int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return bookmarkMapper.queryAllBookmarkedPostByUserid(userid, startnum, pagesize);
    }

}
