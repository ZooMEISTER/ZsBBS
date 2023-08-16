package com.zoom.zsbbs.service;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 22:32
 *
 */

import com.zoom.zsbbs.entity.SubReply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubReplyService {
    SubReply getsubreplyBySubreplyid(int subreplyid);
    int getsubReplyCountByReplyid(int replyid);
    int addNewsubReply(SubReply subReply);
    int deletesubReplyBysubReplyid(int subreplyid);
    List<SubReply> queryAllsubReplyByReplyid(int replyid);
    List<SubReply> queryAllsubReplyAtPagenumByReplyid(int pagenum, int pagesize, int replyid);


    int getMySubreplyCountByUserid(int userid);
    List<SubReply> queryMySubreplyAtPagenumByUserid(int pagenum, int pagesize, int userid);
    int getSubreplyMeCountByUserid(int userid);
    List<SubReply> querySubreplyMeAtPagenumByUserid(int pagenum, int pagesize, int userid);
}
