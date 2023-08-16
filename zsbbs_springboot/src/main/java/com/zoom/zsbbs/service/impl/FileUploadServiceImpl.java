package com.zoom.zsbbs.service.impl;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/29 12:52
 *
 */

import com.zoom.zsbbs.entity.PostImage;
import com.zoom.zsbbs.entity.ReplyImage;
import com.zoom.zsbbs.mapper.PostImageUploadMapper;
import com.zoom.zsbbs.mapper.ReplyImageUploadMapper;
import com.zoom.zsbbs.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private PostImageUploadMapper postImageUploadMapper;

    @Autowired
    private ReplyImageUploadMapper replyImageUploadMapper;

    @Override
    public int addPostImage(PostImage postImage) {
        return postImageUploadMapper.insert(postImage);
    }

    @Override
    public int addReplyImage(ReplyImage replyImage) {
        return replyImageUploadMapper.insert(replyImage);
    }


    @Override
    public List<PostImage> queryPostImageByPostid(int postid) {
        return postImageUploadMapper.queryPostImageByPostid(postid);
    }

    @Override
    public List<ReplyImage> queryReplyImageByReplyid(int replyimagereplyid) {
        return replyImageUploadMapper.queryReplyImageByReplyid(replyimagereplyid);
    }
}
