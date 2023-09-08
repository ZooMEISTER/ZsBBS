package com.zoom.zsbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
    //返回某个帖子的回复数量
    @Select("SELECT count(*) FROM sys_reply WHERE replypostid = #{replypostid}")
    int getReplyCountByPostid(int postid);

    //返回某个帖子的所有回复的内容
    @Select("SELECT * FROM sys_reply WHERE replypostid = #{replypostid}")
    List<Reply> queryReplyByReplypostid(int replypostid);

    //返回某个帖子按页码查找的回复内容
    @Select("SELECT * FROM sys_reply WHERE replypostid = #{replypostid} LIMIT #{startnum},#{pagesize}")
    List<Reply> queryReplyAtPagenumByReplypostid(int startnum, int pagesize, int replypostid);


    //根据replyid返回reply对象
    @Select("SELECT * FROM sys_reply WHERE replyid=#{replyid}")
    Reply getReplyByReplyid(int replyid);



    //返回我的回复的数量
    @Select("SELECT count(*) FROM sys_reply WHERE replyauthorid=#{userid} ORDER BY replytime DESC")
    int getMyReplyCountByUserid(int userid);

    //返回我的回复按页码部分的内容
    @Select("SELECT * FROM sys_reply WHERE replyauthorid=#{userid} ORDER BY replytime DESC LIMIT #{startnum},#{pagesize}")
    List<Reply> queryMyReplyAtPagenumByUserid(int startnum, int pagesize, int userid);

    //返回回复我的回复的数量
    @Select("SELECT count(*) FROM sys_reply WHERE replypostauthorid=#{userid} AND replyauthorid!=#{userid} ORDER BY replytime DESC")
    int getReplyMeCountByUserid(int userid);

    //返回回复我的回复按页码部分的内容
    @Select("SELECT * FROM sys_reply WHERE replypostauthorid=#{userid} AND replyauthorid!=#{userid} ORDER BY replytime DESC LIMIT #{startnum},#{pagesize}")
    List<Reply> queryReplyMeAtPagenumByUserid(int startnum, int pagesize, int userid);

    //返回帖子对应页码的回复 移动端
    @Select("SELECT * FROM sys_reply WHERE replypostid=#{postid} ORDER BY replyid ASC LIMIT #{startnum},#{pagesize}")
    List<Reply> getReplyByPostid_Mobile(int postid, int startnum, int pagesize);
}
