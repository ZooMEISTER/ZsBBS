package com.zoom.zsbbs.service;

import com.zoom.zsbbs.entity.Bookmark;
import com.zoom.zsbbs.entity.Post;
import com.zoom.zsbbs.entity.Reply;
import com.zoom.zsbbs.param.PostResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Service
public interface PostService {
    int getAllPostCount();
    int getAllPostCountByUserid(int userid);


    List<Post> queryAllPost();

    List<Post> searchPost(String searchby, int pagenum, int pagesize);
    int getSearchPostCount(String searchby);


    List<Post> queryAllPostByLatestreplytimeInAsc();
    List<Post> queryAllPostByLatestreplytimeInDesc();
    List<Post> queryAllPostAtPagenumByLatestreplytimeInAsc(int pagenum, int pagesize);
    List<Post> queryAllPostAtPagenumByLatestreplytimeInDesc(int pagenum, int pagesize);


    List<Post> queryAllPostByPublishtimeInAsc();
    List<Post> queryAllPostByPublishtimeInDesc();
    List<Post> queryAllPostAtPagenumByPublishtimeInAsc(int pagenum, int pagesize);
    List<Post> queryAllPostAtPagenumByPublishtimeInDesc(int pagenum, int pagesize);




    List<Post> queryAllPostByLatestreplytimeInAscByUserid(int userid);
    List<Post> queryAllPostByLatestreplytimeInDescByUserid(int userid);
    List<Post> queryAllPostAtPagenumByLatestreplytimeInAscByUserid(int pagenum, int pagesize, int userid);
    List<Post> queryAllPostAtPagenumByLatestreplytimeInDescByUserid(int pagenum, int pagesize, int userid);


    List<Post> queryAllPostByPublishtimeInAscByUserid(int userid);
    List<Post> queryAllPostByPublishtimeInDescByUserid(int userid);
    List<Post> queryAllPostAtPagenumByPublishtimeInAscByUserid(int pagenum, int pagesize, int userid);
    List<Post> queryAllPostAtPagenumByPublishtimeInDescByUserid(int pagenum, int pagesize, int userid);



    int insertPost(Post post);

    Post queryPostInfoByPostid(int postid);

    PostResult deletePostByPostid(int postid);

    PostResult addNewPost(String string_post);

    boolean queryIsThisPostBookmarkedUserid(int postid, int userid);

    int addBookmark(Bookmark bookmark);

    int delBookmark(int postid, int userid);

    int getAllBookmarkedPostCountByUserid(int userid);
    List<Bookmark> queryAllBookmarkedPostByUserid(int userid, int pagenum, int pagesize);

    int addVisitCountByPostid(int postid);
}
