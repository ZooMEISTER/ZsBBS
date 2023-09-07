package com.zoom.zsbbs.service;

import com.zoom.zsbbs.entity.Bookmark;
import com.zoom.zsbbs.entity.Post;
import com.zoom.zsbbs.entity.Reply;
import com.zoom.zsbbs.entity.Section;
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
    int getAllPostCount(int sectionid);
    int getAllPostCountByUserid(int userid);


    List<Post> queryAllPost();

    List<Post> searchPost(String searchby, int sectionid, int pagenum, int pagesize);
    int getSearchPostCount(String searchby, int sectionid);


    List<Post> queryAllPostByLatestreplytimeInAsc(int sectionid);
    List<Post> queryAllPostByLatestreplytimeInDesc(int sectionid);
    List<Post> queryAllPostAtPagenumByLatestreplytimeInAsc(int pagenum, int pagesize, int sectionid);
    List<Post> queryAllPostAtPagenumByLatestreplytimeInDesc(int pagenum, int pagesize, int sectionid);


    List<Post> queryAllPostByPublishtimeInAsc(int sectionid);
    List<Post> queryAllPostByPublishtimeInDesc(int sectionid);
    List<Post> queryAllPostAtPagenumByPublishtimeInAsc(int pagenum, int pagesize, int sectionid);
    List<Post> queryAllPostAtPagenumByPublishtimeInDesc(int pagenum, int pagesize, int sectionid);




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

    List<Section> queryAllSection();

    Section querySingleSection(int sectionid);

    int getSectionPostCountBySectionid(int sectionid);

    List<Post> getSectionPost_Mobile(int sectionid, int pagenum, int pagesize);
}
