package com.zoom.zsbbs.service.impl;

import com.zoom.zsbbs.entity.Follow;
import com.zoom.zsbbs.entity.PostAuthor;
import com.zoom.zsbbs.entity.User;
import com.zoom.zsbbs.mapper.FollowMapper;
import com.zoom.zsbbs.mapper.UsertableMapper;
import com.zoom.zsbbs.param.LoginResult;
import com.zoom.zsbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsertableMapper usertableMapper;

    @Autowired
    private FollowMapper followMapper;

    @Override
    public User queryUserByUserid(int userid) {
        return usertableMapper.selectById(userid);
    }

    @Override
    public List<User> queryAllUser() {
        List<User> userList = usertableMapper.selectList(null);
        //System.out.println(userList);
        return userList;
    }

    @Override
    public int insertUser(User user) {
        return usertableMapper.insert(user);
    }

    @Override
    public int deleteUser(int userid) {
        return usertableMapper.deleteById(userid);
    }

    @Override
    public int updateAvatarByUserid(int userid, String avatar) {
        return usertableMapper.updateAvatarByUserid(userid, avatar);
    }

    @Override
    public int updateUsernameByUserid(int userid, String username) {
        return usertableMapper.updateUsernameByUserid(userid, username);
    }

    @Override
    public int updatePasswordByUserid(int userid, String password) {
        return usertableMapper.updatePasswordByUserid(userid, password);
    }

    @Override
    public int updateDescriptionByUserid(int userid, String description) {
        return usertableMapper.updateDescriptionByUserid(userid, description);
    }

    @Override
    public PostAuthor queryAuthorInfoByUserid(int userid) {
        return usertableMapper.queryAuthorByUserid(userid);
    }

    @Override
    public Boolean querySingleFollow(int followeduserid, int followeruserid) {
        if(followMapper.querySingleFollow(followeduserid, followeruserid) > 0){
            return true;
        }
        return false;
    }

    @Override
    public int addFollow(Follow follow) {
        return followMapper.insert(follow);
    }

    @Override
    public int delFollow(int followeduserid, int followeruserid) {
        return followMapper.delfollow(followeduserid, followeruserid);
    }

    @Override
    public List<Follow> getAllFollowto(int userid, int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return followMapper.getAllFollowto(userid, startnum, pagesize);
    }

    @Override
    public int getAllFollowtoCount(int userid) {
        return followMapper.getAllFollowtoCount(userid);
    }

    @Override
    public List<Follow> getAllFollower(int userid, int pagenum, int pagesize) {
        int startnum = (pagenum - 1) * pagesize;
        return followMapper.getAllFollower(userid, startnum, pagesize);
    }

    @Override
    public int getAllFollowerCount(int userid) {
        return followMapper.getAllFollowerCount(userid);
    }
}
