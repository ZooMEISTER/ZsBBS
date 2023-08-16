package com.zoom.zsbbs.service.impl;

import com.zoom.zsbbs.entity.PostAuthor;
import com.zoom.zsbbs.entity.User;
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

    @Override
    public User queryUserByUserid(int userid) {
        return usertableMapper.selectById(userid);
    }

    @Override
    public List<User> queryAllUser() {
        List<User> userList = usertableMapper.selectList(null);
        System.out.println(userList);
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
    public PostAuthor queryAuthorInfoByUserid(int userid) {
        return usertableMapper.queryAuthorByUserid(userid);
    }
}
