package com.zoom.zsbbs.service;

import com.zoom.zsbbs.entity.Follow;
import com.zoom.zsbbs.entity.PostAuthor;
import com.zoom.zsbbs.entity.User;
import com.zoom.zsbbs.param.LoginResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Service
public interface UserService {
    //查询单个用户信息
    User queryUserByUserid(int userid);

    //返回所有用户
    List<User> queryAllUser();

    //用户插入
    int insertUser(User user);

    //用户删除
    int deleteUser(int userid);

    //更改用户头像
    int updateAvatarByUserid(int userid, String avatar);

    //更改用户名
    int updateUsernameByUserid(int userid, String username);

    //更改用户密码
    int updatePasswordByUserid(int userid, String password);

    //更改用户简介
    int updateDescriptionByUserid(int userid, String description);

    //通过userid查询作者信息
    PostAuthor queryAuthorInfoByUserid(int userid);

    Boolean querySingleFollow(int followeduserid, int followeruserid);
    int addFollow(Follow follow);
    int delFollow(int followeduserid, int followeruserid);


    List<Follow> getAllFollowto(int userid, int pagenum, int pagesize);
    int getAllFollowtoCount(int userid);

    List<Follow> getAllFollower(int userid, int pagenum, int pagesize);
    int getAllFollowerCount(int userid);
}
