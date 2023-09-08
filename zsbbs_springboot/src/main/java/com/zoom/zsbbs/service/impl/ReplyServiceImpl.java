package com.zoom.zsbbs.service.impl;

import com.zoom.zsbbs.entity.Reply;
import com.zoom.zsbbs.mapper.ReplyMapper;
import com.zoom.zsbbs.param.PostResult;
import com.zoom.zsbbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 1:30
 *
 */

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public int getReplyCountByPostid(int postid) {
        return replyMapper.getReplyCountByPostid(postid);
    }

    @Override
    public List<Reply> queryReplyByReplypostid(int replypostid) {
        return replyMapper.queryReplyByReplypostid(replypostid);
    }

    @Override
    public List<Reply> queryReplyAtPagenumByReplypostid(int pagenum, int pagesize, int replypostid) {
        int startnum = (pagenum - 1) * pagesize;
        return replyMapper.queryReplyAtPagenumByReplypostid(startnum, pagesize, replypostid);
    }

    @Override
    public PostResult deleteReplyByReplyid(int replyid){
        if(replyMapper.deleteById(replyid) > 0){
            return PostResult.ok();
        }
        else {
            return PostResult.error();
        }
    }

    @Override
    public Reply getReplyByReplyid(int replyid) {
        return replyMapper.selectById(replyid);
    }

    @Override
    public int getMyReplyCountByUserid(int userid) {
        return replyMapper.getMyReplyCountByUserid(userid);
    }

    @Override
    public List<Reply> queryMyReplyAtPagenumByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return replyMapper.queryMyReplyAtPagenumByUserid(startnum, pagesize, userid);
    }

    @Override
    public int getReplyMeCountByUserid(int userid) {
        return replyMapper.getReplyMeCountByUserid(userid);
    }

    @Override
    public List<Reply> queryReplyMeAtPagenumByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return replyMapper.queryReplyMeAtPagenumByUserid(startnum, pagesize, userid);
    }

    @Override
    public int insertReply(Reply reply) {
        replyMapper.insert(reply);
        return reply.getReplyid();
    }

    @Override
    public List<Reply> getReplyByPostid_Mobile(int postid, int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return replyMapper.getReplyByPostid_Mobile(postid, startnum, pagesize);
    }
}
