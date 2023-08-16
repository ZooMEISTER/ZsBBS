package com.zoom.zsbbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.PostImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/29 12:48
 *
 */

@Mapper
public interface PostImageUploadMapper extends BaseMapper<PostImage> {
    @Select("SELECT * FROM sys_postimage WHERE postimagepostid=#{postid}")
    List<PostImage> queryPostImageByPostid(int postid);
}
