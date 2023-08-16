package com.zoom.zsbbs.service.impl;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 22:33
 *
 */

import com.zoom.zsbbs.entity.SubReply;
import com.zoom.zsbbs.mapper.SubReplyMapper;
import com.zoom.zsbbs.service.SubReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubReplyServiceImpl implements SubReplyService {

    @Autowired
    SubReplyMapper subReplyMapper;

    @Override
    public SubReply getsubreplyBySubreplyid(int subreplyid) {
        return subReplyMapper.getsubreplyBySubreplyid(subreplyid);
    }

    @Override
    public int getsubReplyCountByReplyid(int replyid) {
        return subReplyMapper.getsubReplyCountByReplyid(replyid);
    }

    @Override
    public int addNewsubReply(SubReply subReply) {
        return subReplyMapper.insert(subReply);
    }

    @Override
    public int deletesubReplyBysubReplyid(int subreplyid) {
        return subReplyMapper.deleteById(subreplyid);
    }

    @Override
    public List<SubReply> queryAllsubReplyByReplyid(int replyid) {
        return subReplyMapper.queryAllsubReplyByReplyid(replyid);
    }

    @Override
    public List<SubReply> queryAllsubReplyAtPagenumByReplyid(int pagenum, int pagesize, int replyid) {
        int startnum = (pagenum - 1) * pagesize;
        return subReplyMapper.queryAllsubReplyAtPagenumByReplyid(startnum, pagesize, replyid);
    }

    @Override
    public int getMySubreplyCountByUserid(int userid) {
        return subReplyMapper.getMySubreplyCountByUserid(userid);
    }

    @Override
    public List<SubReply> queryMySubreplyAtPagenumByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return subReplyMapper.queryMySubreplyAtPagenumByUserid(startnum, pagesize, userid);
    }

    @Override
    public int getSubreplyMeCountByUserid(int userid) {
        return subReplyMapper.getSubreplyMeCountByUserid(userid);
    }

    @Override
    public List<SubReply> querySubreplyMeAtPagenumByUserid(int pagenum, int pagesize, int userid) {
        int startnum = (pagenum - 1) * pagesize;
        return subReplyMapper.querySubreplyMeAtPagenumByUserid(startnum, pagesize, userid);
    }
}
