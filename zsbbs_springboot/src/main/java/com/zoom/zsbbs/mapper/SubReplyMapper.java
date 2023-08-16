package com.zoom.zsbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.SubReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/26 22:31
 *
 */
@Mapper
public interface SubReplyMapper extends BaseMapper<SubReply> {
    //通过subreplyid获取subreply
    @Select("SELECT * FROM sys_subreply WHERE subreplyid=#{subreplyid}")
    public SubReply getsubreplyBySubreplyid(int subreplyid);

    //通过replyid获取该reply的所有subreply的数量
    @Select("SELECT count(*) FROM sys_subreply WHERE subreplyreplyid=#{replyid}")
    public int getsubReplyCountByReplyid(int replyid);

    //通过replyid获取该reply的所有subreply
    @Select("SELECT * FROM sys_subreply WHERE subreplyreplyid=#{replyid}")
    public List<SubReply> queryAllsubReplyByReplyid(int replyid);

    //通过relyid获取该reply对应页码的subreply
    @Select("SELECT * FROM sys_subreply WHERE subreplyreplyid=#{replyid} LIMIT #{startnum},#{pagesize}")
    public List<SubReply> queryAllsubReplyAtPagenumByReplyid(int startnum, int pagesize, int replyid);

    //获取我发出的所有subreply的数量
    @Select("SELECT count(*) FROM sys_subreply WHERE subreplyauthorid=#{userid}")
    public int getMySubreplyCountByUserid(int userid);

    //获取我发出的所有subreply在对应页码的subreply
    @Select("SELECT * FROM sys_subreply WHERE subreplyauthorid=#{userid} ORDER BY subreplytime DESC LIMIT #{startnum},#{pagesize}")
    public List<SubReply> queryMySubreplyAtPagenumByUserid(int startnum, int pagesize, int userid);

    //获取回复我的所有subreply的数量
    @Select("SELECT count(*) FROM sys_subreply WHERE subreplytargetauthorid=#{userid}")
    public int getSubreplyMeCountByUserid(int userid);

    //获取回复我的所有subreply在对应页码的subreply
    @Select("SELECT * FROM sys_subreply WHERE subreplytargetauthorid=#{userid} AND subreplyauthorid!=#{userid} ORDER BY subreplytime DESC LIMIT #{startnum},#{pagesize}")
    public List<SubReply> querySubreplyMeAtPagenumByUserid(int startnum, int pagesize, int userid);
}
