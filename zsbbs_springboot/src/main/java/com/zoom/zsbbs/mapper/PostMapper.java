package com.zoom.zsbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.Post;
import com.zoom.zsbbs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    @Select("SELECT count(*) FROM sys_post")
    public int getAllPostCount();
    @Select("SELECT count(*) FROM sys_post WHERE authorid=#{userid}")
    int getAllPostCountByUserid(int userid);


    @Select("SELECT * FROM sys_post")
    public List<Post> queryAllPost();

    @Select("SELECT * FROM sys_post WHERE POSITION(#{searchby} IN title) ORDER BY publishtime DESC LIMIT #{startnum},#{pagesize}")
    public List<Post> searchPost(String searchby, int startnum, int pagesize);

    @Select("SELECT count(*) FROM sys_post WHERE POSITION(#{searchby} IN title)")
    public int getSearchPostCount(String searchby);




    @Select("SELECT * FROM sys_post ORDER BY latestreplytime ASC")
    public List<Post> queryAllPostByLatestreplytimeInAsc();
    @Select("SELECT * FROM sys_post ORDER BY latestreplytime DESC")
    public List<Post> queryAllPostByLatestreplytimeInDesc();
    @Select("SELECT * FROM sys_post ORDER BY latestreplytime ASC LIMIT #{startnum},#{pagesize}")
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInAsc(int startnum, int pagesize);
    @Select("SELECT * FROM sys_post ORDER BY latestreplytime DESC LIMIT #{startnum},#{pagesize}")
    public List<Post> queryAllPostAtPagenumByLatestreplytimeInDesc(int startnum, int pagesize);


    @Select("SELECT * FROM sys_post ORDER BY publishtime ASC")
    public List<Post> queryAllPostByPublishtimeInAsc();
    @Select("SELECT * FROM sys_post ORDER BY publishtime DESC")
    public List<Post> queryAllPostByPublishtimeInDesc();
    @Select("SELECT * FROM sys_post ORDER BY publishtime ASC LIMIT #{startnum},#{pagesize}")
    public List<Post> queryAllPostAtPagenumByPublishtimeInAsc(int startnum, int pagesize);
    @Select("SELECT * FROM sys_post ORDER BY publishtime DESC LIMIT #{startnum},#{pagesize}")
    public List<Post> queryAllPostAtPagenumByPublishtimeInDesc(int startnum, int pagesize);




    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY latestreplytime ASC")
    List<Post> queryAllPostByLatestreplytimeInAscByUserid(int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY latestreplytime DESC")
    List<Post> queryAllPostByLatestreplytimeInDescByUserid(int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY latestreplytime ASC LIMIT #{startnum},#{pagesize}")
    List<Post> queryAllPostAtPagenumByLatestreplytimeInAscByUserid(int startnum, int pagesize, int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY latestreplytime DESC LIMIT #{startnum},#{pagesize}")
    List<Post> queryAllPostAtPagenumByLatestreplytimeInDescByUserid(int startnum, int pagesize, int userid);


    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY publishtime ASC")
    List<Post> queryAllPostByPublishtimeInAscByUserid(int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY publishtime DESC")
    List<Post> queryAllPostByPublishtimeInDescByUserid(int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY publishtime ASC LIMIT #{startnum},#{pagesize}")
    List<Post> queryAllPostAtPagenumByPublishtimeInAscByUserid(int startnum, int pagesize, int userid);
    @Select("SELECT * FROM sys_post WHERE authorid=#{userid} ORDER BY publishtime DESC LIMIT #{startnum},#{pagesize}")
    List<Post> queryAllPostAtPagenumByPublishtimeInDescByUserid(int startnum, int pagesize, int userid);

}
