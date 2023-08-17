package com.zoom.zsbbs.mapper;/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 18:14
 *
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    @Select("SELECT count(*) FROM sys_follow WHERE followeduserid=#{followeduserid} AND followeruserid=#{followeruserid}")
    public int querySingleFollow(int followeduserid, int followeruserid);

    @Delete("DELETE FROM sys_follow WHERE followeduserid=#{followeduserid} AND followeruserid=#{followeruserid}")
    public int delfollow(int followeduserid, int followeruserid);



    @Select("SELECT count(*) FROM sys_follow WHERE followeruserid=#{userid}")
    public int getAllFollowtoCount(int userid);
    @Select("SELECT * FROM sys_follow WHERE followeruserid=#{userid} ORDER BY followtime DESC LIMIT #{startnum},#{pagesize}")
    public List<Follow> getAllFollowto(int userid, int startnum, int pagesize);

    @Select("SELECT count(*) FROM sys_follow WHERE followeduserid=#{userid}")
    public int getAllFollowerCount(int userid);
    @Select("SELECT * FROM sys_follow WHERE followeduserid=#{userid} ORDER BY followtime DESC LIMIT #{startnum},#{pagesize}")
    public List<Follow> getAllFollower(int userid, int startnum, int pagesize);
}
