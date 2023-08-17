package com.zoom.zsbbs.mapper;

/*
 *    Created by ZooMEISTER
 *    on 2023/8/17 12:08
 *
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.Bookmark;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BookmarkMapper extends BaseMapper<Bookmark> {
    @Select("SELECT count(*) FROM sys_bookmark WHERE bookmarkpostid=#{postid} AND bookmarkby=#{userid}")
    public int queryIsThisPostBookmarkedUserid(int postid, int userid);

    @Delete("DELETE FROM sys_bookmark WHERE bookmarkpostid=#{postid} AND bookmarkby=#{userid}")
    public int delBookmark(int postid, int userid);

    @Select("SELECT count(*) FROM sys_bookmark WHERE bookmarkby=#{userid}")
    public int getAllBookmarkedPostCountByUserid(int userid);
    @Select("Select * FROM sys_bookmark WHERE bookmarkby=#{userid} ORDER BY bookmarktime DESC LIMIT #{startnum},#{pagesize}")
    public List<Bookmark> queryAllBookmarkedPostByUserid(int userid, int startnum, int pagesize);
}
