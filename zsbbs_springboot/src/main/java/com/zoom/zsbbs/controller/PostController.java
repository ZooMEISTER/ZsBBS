package com.zoom.zsbbs.controller;

import com.zoom.zsbbs.entity.*;
import com.zoom.zsbbs.entity.show.BookmarkShow;
import com.zoom.zsbbs.entity.show.PostShow;
import com.zoom.zsbbs.entity.show.ReplyShow;
import com.zoom.zsbbs.entity.show.SubReplyShow;
import com.zoom.zsbbs.exception.PostResultCode;
import com.zoom.zsbbs.param.PostResult;
import com.zoom.zsbbs.service.PostService;
import com.zoom.zsbbs.service.ReplyService;
import com.zoom.zsbbs.service.SubReplyService;
import com.zoom.zsbbs.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private SubReplyService subReplyService;

    //查询所有帖子信息
    //http://localhost:8088/post/queryallpost
    @RequestMapping(value = "/queryallpost", method = RequestMethod.POST)
    public PostShow queryAllPost(@RequestParam("useparam") boolean useparam,
                                 @RequestParam("posttype") int posttype,
                                 @RequestParam("postdesc") boolean postdesc,
                                 @RequestParam("sortby") String sortby,
                                 @RequestParam("pagenum") int pagenum,
                                 @RequestParam("pagesize") int pagesize,
                                 @RequestParam("userid") int userid,
                                 @RequestParam("sectionid") int sectionid
    ){

        if(useparam == true){
            //有条件的查询帖子
            if(posttype == 0){
                //有条件的查询广场上的所有帖子
                if(pagenum == 0){
                    //不用页码，直接返回广场上的所有帖子
                    if(sortby.equals("rt")){
                        //排序依据为最后回复时间
                        if(!postdesc){
                            //按升序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostByLatestreplytimeInAsc(sectionid));
                        }
                        else {
                            //按降序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostByLatestreplytimeInDesc(sectionid));
                        }
                    }
                    else if (sortby.equals("pt")) {
                        //排序依据为发布时间
                        if(!postdesc){
                            //按升序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostByPublishtimeInAsc(sectionid));
                        }
                        else {
                            //按降序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostByPublishtimeInDesc(sectionid));
                        }
                    }

                }
                else {
                    //返回对应页码的帖子
                    if(sortby.equals("rt")){
                        //排序依据为最后回复时间
                        if(!postdesc){
                            //按升序返回广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostAtPagenumByLatestreplytimeInAsc(pagenum, pagesize, sectionid));
                        }
                        else {
                            //按返回查询广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostAtPagenumByLatestreplytimeInDesc(pagenum, pagesize, sectionid));
                        }
                    }
                    else if (sortby.equals("pt")) {
                        //排序依据为发布时间
                        if(!postdesc){
                            //按升序返回广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostAtPagenumByPublishtimeInAsc(pagenum, pagesize, sectionid));
                        }
                        else {
                            //按返回查询广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPostAtPagenumByPublishtimeInDesc(pagenum, pagesize, sectionid));
                        }
                    }

                }
            }
            else if(posttype == 1){
                //有条件的查询广场上的某个用户发的帖子
                if(pagenum == 0){
                    //不用页码，直接返回广场上的所有帖子
                    if(sortby.equals("rt")){
                        //排序依据为最后回复时间
                        if(!postdesc){
                            //按升序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostByLatestreplytimeInAscByUserid(userid));
                        }
                        else {
                            //按降序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostByLatestreplytimeInDescByUserid(userid));
                        }
                    }
                    else if (sortby.equals("pt")) {
                        //排序依据为发布时间
                        if(!postdesc){
                            //按升序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostByPublishtimeInAscByUserid(userid));
                        }
                        else {
                            //按降序返回广场上的所有帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostByPublishtimeInDescByUserid(userid));
                        }
                    }
                }
                else {
                    //返回对应页码的帖子
                    if(sortby.equals("rt")){
                        //排序依据为最后回复时间
                        if(!postdesc){
                            //按升序返回广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostAtPagenumByLatestreplytimeInAscByUserid(pagenum, pagesize, userid));
                        }
                        else {
                            //按返回查询广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostAtPagenumByLatestreplytimeInDescByUserid(pagenum, pagesize, userid));
                        }
                    }
                    else if (sortby.equals("pt")) {
                        //排序依据为发布时间
                        if(!postdesc){
                            //按升序返回广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostAtPagenumByPublishtimeInAscByUserid(pagenum, pagesize, userid));
                        }
                        else {
                            //按返回查询广场上的对应页码帖子
                            return new PostShow(postService.getAllPostCountByUserid(userid), postService.queryAllPostAtPagenumByPublishtimeInDescByUserid(pagenum, pagesize, userid));
                        }
                    }


                }
            }
            else{
                //其他条件返回帖子
                return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPost());
            }
        }
        else {
            //无条件的返回所有帖子
            return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPost());
        }
        return new PostShow(postService.getAllPostCount(sectionid), postService.queryAllPost());
    }

    //查询所有帖子信息 按时间升序
    //http://localhost:8088/post/searchpost
    @RequestMapping(value = "/searchpost", method = RequestMethod.POST)
    public PostShow searchPost(@RequestParam("searchby") String searchby,
                                 @RequestParam("pagenum") int pagenum,
                                 @RequestParam("pagesize") int pagesize,
                                 @RequestParam("sectionid") int sectionid){
        return new PostShow(postService.getSearchPostCount(searchby, sectionid), postService.searchPost(searchby, sectionid, pagenum, pagesize));
    }


//    //查询所有帖子信息 按时间升序
//    //http://localhost:8088/post/queryallpostbylatestreplytimeasc
//    @RequestMapping(value = "/queryallpostbylatestreplytimeasc", method = RequestMethod.POST)
//    public List<Post> queryAllPostByLatestreplytimeASC(@RequestParam("pagenum") int startnum, @RequestParam("pagesize") int pagesize){
//
//    }
//
//    //查询所有帖子信息 按时间降序
//    //http://localhost:8088/post/queryallpostbylatestreplytimedesc
//    @RequestMapping(value = "/queryallpostbylatestreplytimedesc", method = RequestMethod.POST)
//    public List<Post> queryAllPostByLatestreplytimeDESC(@RequestParam("pagenum") int startnum, @RequestParam("pagesize") int pagesize){
//
//    }

//    //查询对应页码的所有帖子信息 按时间升序
//    //http://localhost:8088/post/queryallpostatpagenumbylatestreplytimeasc
//    @RequestMapping(value = "/queryallpostatpagenumbylatestreplytimeasc", method = RequestMethod.POST)
//    public List<Post> queryAllPostAtPagenumByLatestreplytimeASC(@RequestParam("pagenum") int startnum, @RequestParam("pagesize") int pagesize){
//        return ;
//    }
//
//    //查询对应页码的所有帖子信息 按时间降序
//    //http://localhost:8088/post/queryallpostatpagenumbylatestreplytimedesc
//    @RequestMapping(value = "/queryallpostatpagenumbylatestreplytimedesc", method = RequestMethod.POST)
//    public List<Post> queryAllPostAtPagenumByLatestreplytimeDESC(@RequestParam("pagenum") int startnum, @RequestParam("pagesize") int pagesize){
//        return ;
//    }

    //发帖的文字内容
    //http://localhost:8088/post/publishnewpost
//    @RequestMapping(value = "/publishnewpost", method = RequestMethod.POST)
//    public PostResult addNewPost(@RequestBody String string_post){
//        return postService.addNewPost(string_post);
//    }
    @RequestMapping(value = "/publishnewpost", method = RequestMethod.POST)
    public int addNewPost(Post post, @RequestParam("authorid") int authorid){
        System.out.println("post.authorid " +  authorid);
        post.setAuthorId(authorid);
        return postService.insertPost(post);
    }

    //删帖
    //http://localhost:8088/post/deletepost
    @RequestMapping(value = "/deletepost", method = RequestMethod.POST)
    public PostResult deletePostByPostid(int postid){
        return postService.deletePostByPostid(postid);
    }

    //删回复
    //http://localhost:8088/post/deletereply
    @RequestMapping(value = "/deletereply", method = RequestMethod.POST)
    public PostResult deleteReplyByReplyid(int replyid){
        return replyService.deleteReplyByReplyid(replyid);
    }

    //查询作者信息
    //http://localhost:8088/post/query/author
    @RequestMapping(value = "/query/author", method = RequestMethod.POST)
    public PostAuthor queryAuthorInfoByUserid(@RequestParam("userid") int userid){
        return userService.queryAuthorInfoByUserid(userid);
    }

    //根据replyid返回该reply
    //http://localhost:8088/post/query/sreply
    @RequestMapping(value = "/query/sreply", method = RequestMethod.POST)
    public Reply getReplyByReplyid(@RequestParam("replyid") int replyid){
        return replyService.getReplyByReplyid(replyid);
    }

    //查询帖子信息
    //http://localhost:8088/post/query/post
    @RequestMapping(value = "/query/post", method = RequestMethod.POST)
    public Post queryPostInfoByPostid(@RequestParam("postid") int postid){
        return postService.queryPostInfoByPostid(postid);
    }

    //查询某个帖子的所有回复
    //http://localhost:8088/post/query/reply
    @RequestMapping(value = "/query/reply", method = RequestMethod.POST)
    public List<Reply> queryReplyByReplypostid(@RequestParam("replypostid") int replypostid){
        return replyService.queryReplyByReplypostid(replypostid);
    }

    //按页码返回某个帖子的所有回复
    //http://localhost:8088/post/query/replybypagenum
    @RequestMapping(value = "/query/replybypagenum", method = RequestMethod.POST)
    public ReplyShow queryReplyAtPagenumByReplypostid(@RequestParam("pagenum") int pagenum,
                                                      @RequestParam("pagesize") int pagesize,
                                                      @RequestParam("replypostid") int replypostid){
        return new ReplyShow(replyService.getReplyCountByPostid(replypostid), replyService.queryReplyAtPagenumByReplypostid(pagenum, pagesize, replypostid));
    }

    //按页码返回所有与用户相关的回复
    //http://localhost:8088/post/query/userreplybypagenum
    @RequestMapping(value = "/query/userreplybypagenum", method = RequestMethod.POST)
    public ReplyShow queryUserRelatedReplyAtPagenumByUserid(@RequestParam("replytype") int replytype,
                                                            @RequestParam("pagenum") int pagenum,
                                                            @RequestParam("pagesize") int pagesize,
                                                            @RequestParam("userid") int userid){

        if(replytype == 0){
            //返回 用户发的回复
            return new ReplyShow(replyService.getMyReplyCountByUserid(userid), replyService.queryMyReplyAtPagenumByUserid(pagenum, pagesize, userid));
        }
        else if(replytype == 1){
            //返回 回复用户的回复
            return new ReplyShow(replyService.getReplyMeCountByUserid(userid), replyService.queryReplyMeAtPagenumByUserid(pagenum, pagesize, userid));
        }

        //默认 返回 用户发的回复
        return new ReplyShow(replyService.getMyReplyCountByUserid(userid), replyService.queryMyReplyAtPagenumByUserid(pagenum, pagesize, userid));
    }

    //添加新的回复
    //http://localhost:8088/post/addnewreply
    @RequestMapping(value = "/addnewreply", method = RequestMethod.POST)
    public int addNewReply(Reply reply){
        System.out.println(reply.toString());
        //return PostResult.ok();
        int insertReplyRes = replyService.insertReply(reply);
        return insertReplyRes;
//        if(insertReplyRes > 0){
//            return PostResult.ok();
//        }
//        else {
//            return PostResult.error();
//        }
    }

    //根据subreplyid返回该subreply
    //http://localhost:8088/post/query/targetsubreply
    @RequestMapping(value = "/query/targetsubreply", method = RequestMethod.POST)
    public SubReply getsubreplyBySubreplyid(@RequestParam("subreplyid") int subreplyid) {
        return subReplyService.getsubreplyBySubreplyid(subreplyid);
    }

    //添加subreply
    //http://localhost:8088/post/addnewsubreply
    @RequestMapping(value = "/addnewsubreply", method = RequestMethod.POST)
    public int addNewsubReply(SubReply subReply){
        int res = subReplyService.addNewsubReply(subReply);
        if(res > 0){
            return PostResultCode.ADD_NEW_SUBREPLY_SUCCESS;
        }
        else {
            return PostResultCode.ADD_NEW_SUBREPLY_FAIL;
        }
    }

    //删除subreply
    //http://localhost:8088/post/deletesubreply
    @RequestMapping(value = "/deletesubreply", method = RequestMethod.POST)
    public int deletesubReplyBysubReplyid(@RequestParam("subreplyid") int subreplyid){
        int res = subReplyService.deletesubReplyBysubReplyid(subreplyid);
        if(res > 0){
            return PostResultCode.DELETE_SUBREPLY_SUCCESS;
        }
        else {
            return PostResultCode.DELETE_SUBREPLY_FAIL;
        }
    }

    //根据replyid 返回该reply的所有楼中楼subreply
    //http://localhost:8088/post/query/subreply
    @RequestMapping(value = "/query/subreply", method = RequestMethod.POST)
    public List<SubReply> queryAllsubReplyByReplyid(@RequestParam("subreplyreplyid") int replyid){
        return subReplyService.queryAllsubReplyByReplyid(replyid);
    }

    //根据replyid pagenum 返回某个reply对应页数的subreply
    @RequestMapping(value = "/query/subeplybypagenum", method = RequestMethod.POST)
    public SubReplyShow queryAllsubReplyAtPagenumByReplyid(@RequestParam("pagenum") int pagenum,
                                                           @RequestParam("pagesize") int pagesize,
                                                           @RequestParam("subreplyreplyid") int replyid){
        return new SubReplyShow(subReplyService.getsubReplyCountByReplyid(replyid), subReplyService.queryAllsubReplyAtPagenumByReplyid(pagenum, pagesize, replyid));
    }

    //按页码返回所有与用户相关的次要回复
    //http://localhost:8088/post/query/usersubreplybypagenum
    @RequestMapping(value = "/query/usersubreplybypagenum", method = RequestMethod.POST)
    public SubReplyShow queryUserRelatedSubReplyAtPagenumByUserid(@RequestParam("subreplytype") int subreplytype,
                                                                    @RequestParam("pagenum") int pagenum,
                                                                    @RequestParam("pagesize") int pagesize,
                                                                    @RequestParam("userid") int userid){

        if(subreplytype == 0){
            //返回 用户发的楼中楼回复
            return new SubReplyShow(subReplyService.getMySubreplyCountByUserid(userid), subReplyService.queryMySubreplyAtPagenumByUserid(pagenum, pagesize, userid));
        }
        else if(subreplytype == 1){
            //返回 回复用户的楼中楼回复
            return new SubReplyShow(subReplyService.getSubreplyMeCountByUserid(userid), subReplyService.querySubreplyMeAtPagenumByUserid(pagenum, pagesize, userid));
        }

        //默认 返回 用户发的楼中楼回复
        return new SubReplyShow(subReplyService.getMySubreplyCountByUserid(userid), subReplyService.queryMySubreplyAtPagenumByUserid(pagenum, pagesize, userid));
    }

    //根据postid和userid判断这个帖子是否被收藏
    //http://localhost:8088/post/query/isthisbookmarked
    @RequestMapping(value = "/query/isthisbookmarked", method = RequestMethod.POST)
    public boolean queryIsThisPostBookmarkedUserid(@RequestParam("postid") int postid,
                                                   @RequestParam("userid") int userid){

        return postService.queryIsThisPostBookmarkedUserid(postid, userid);
    }

    //根据postid和userid添加收藏
    //http://localhost:8088/post/addbookmark
    @RequestMapping(value = "/addbookmark", method = RequestMethod.POST)
    public int addBookmark(Bookmark bookmark){
        return postService.addBookmark(bookmark);
    }

    //根据postid和userid取消收藏
    //http://localhost:8088/post/delbookmark
    @RequestMapping(value = "/delbookmark", method = RequestMethod.POST)
    public int delBookmark(Bookmark bookmark){
        return postService.delBookmark(bookmark.getBookmarkpostid(), bookmark.getBookmarkby());
    }

    //根据userid查询该用户收藏的所有帖子
    //http://localhost:8088/post/query/allbookmarkedpost
    @RequestMapping(value = "/query/allbookmarkedpost", method = RequestMethod.POST)
    public BookmarkShow queryAllBookmarkedPostByUserid(@RequestParam("userid") int userid,
                                                       @RequestParam("pagenum") int pagenum,
                                                       @RequestParam("pagesize") int pagesize){
        return new BookmarkShow(postService.getAllBookmarkedPostCountByUserid(userid), postService.queryAllBookmarkedPostByUserid(userid, pagenum, pagesize));
    }

    //将目标帖子的访问次数加1
    //http://localhost:8088/post/addvisitcount
    @RequestMapping(value = "/addvisitcount", method = RequestMethod.POST)
    public int addVisitCountByPostid(@RequestParam("postid") int postid){
        return postService.addVisitCountByPostid(postid);
    }

    //查询所有的板块
    //http://localhost:8088/post/query/allsection
    @RequestMapping(value = "/query/allsection", method = RequestMethod.POST)
    public List<Section> queryAllSection(){
        return postService.queryAllSection();
    }

    //查询某个sectionid指定的section的信息
    //http://localhost:8088/post/query/singlesection
    @RequestMapping(value = "/query/singlesection", method = RequestMethod.POST)
    public Section querySingleSection(@RequestParam("sectionid") int sectionid){
        return postService.querySingleSection(sectionid);
    }

    //查询某个section下面的帖子数量
    //http://localhost:8088/post/getsectionpostcount
    @RequestMapping(value = "/getsectionpostcount", method = RequestMethod.POST)
    public int getSectionPostCountBySectionid(@RequestParam("sectionid") int sectionid){
        return postService.getSectionPostCountBySectionid(sectionid);
    }

    //给移动端用 查询某个section下的帖子信息
    //http://localhost:8088/post/getsectionpost_mobile
    @RequestMapping(value = "/getsectionpost_mobile", method = RequestMethod.POST)
    public List<Post> getSectionPostBySectionid_Mobile(@RequestParam("sectionid") int sectionid,
                                                       @RequestParam("pagenum") int pagenum,
                                                       @RequestParam("pagesize") int pagesize){
        return postService.getSectionPost_Mobile(sectionid, pagenum, pagesize);
    }

    //给移动端用 查询某个帖子的所有回复
    //http://localhost:8088/post/getreplybypostid_mobile
    @RequestMapping(value = "/getreplybypostid_mobile", method = RequestMethod.POST)
    public List<Reply> getReplyByPostid_Mobile(@RequestParam("postid") int postid,
                                               @RequestParam("pagenum") int pagenum,
                                               @RequestParam("pagesize") int pagesize){
        return replyService.getReplyByPostid_Mobile(postid, pagenum, pagesize);
    }
}
