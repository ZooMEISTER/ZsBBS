package com.zoom.zsbbs.mapper;/*
 *    Created by ZooMEISTER
 *    on 2023/6/30 13:12
 *
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.PostImage;
import com.zoom.zsbbs.entity.ReplyImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyImageUploadMapper extends BaseMapper<ReplyImage> {
    @Select("SELECT * FROM sys_replyimage WHERE replyimagereplyid=#{replyid}")
    List<ReplyImage> queryReplyImageByReplyid(int replyid);
}
