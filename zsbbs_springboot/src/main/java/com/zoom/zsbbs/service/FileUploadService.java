package com.zoom.zsbbs.service;

import com.zoom.zsbbs.entity.PostImage;
import com.zoom.zsbbs.entity.ReplyImage;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/29 12:52
 *
 */


@Service
public interface FileUploadService {
    int addPostImage(PostImage postImage);
    int addReplyImage(ReplyImage replyImage);


    List<PostImage> queryPostImageByPostid(int postid);
    List<ReplyImage> queryReplyImageByReplyid(int replyimagereplyid);
}
