package com.zoom.zsbbs.service;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 1:32
 *
 */

import com.zoom.zsbbs.entity.Reply;
import com.zoom.zsbbs.param.PostResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface ReplyService {
    int getReplyCountByPostid(int postid);
    List<Reply> queryReplyByReplypostid(int replypostid);

    List<Reply> queryReplyAtPagenumByReplypostid(int pagenum, int pagesize, int replypostid);

    int insertReply(Reply reply);

    PostResult deleteReplyByReplyid(int replyid);

    Reply getReplyByReplyid(int replyid);


    int getMyReplyCountByUserid(int userid);

    List<Reply> queryMyReplyAtPagenumByUserid(int pagenum, int pagesize, int userid);

    int getReplyMeCountByUserid(int userid);

    List<Reply> queryReplyMeAtPagenumByUserid(int pagenum, int pagesize, int userid);

    List<Reply> getReplyByPostid_Mobile(int postid, int pagenum, int pagesize);
}
