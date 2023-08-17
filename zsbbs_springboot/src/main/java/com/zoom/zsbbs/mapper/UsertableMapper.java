package com.zoom.zsbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.PostAuthor;
import com.zoom.zsbbs.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Mapper
public interface UsertableMapper extends BaseMapper<User> {
    @Select("select * from sys_usertable")
    public List<User> queryAllUser();

    @Update("UPDATE sys_usertable SET avatar = #{avatar} WHERE userid = #{userid}")
    public int updateAvatarByUserid(int userid, String avatar);

    @Update("UPDATE sys_usertable SET username = #{username} WHERE userid = #{userid}")
    public int updateUsernameByUserid(int userid, String username);

    @Update("UPDATE sys_usertable SET password = #{password} WHERE userid = #{userid}")
    public int updatePasswordByUserid(int userid, String password);

    @Update("UPDATE sys_usertable SET description = #{description} WHERE userid = #{userid}")
    public int updateDescriptionByUserid(int userid, String description);

    @Select("SELECT * FROM sys_usertable WHERE userid = #{userid}")
    public PostAuthor queryAuthorByUserid(int userid);
}
