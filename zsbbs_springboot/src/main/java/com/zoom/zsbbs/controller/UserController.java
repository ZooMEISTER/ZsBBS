package com.zoom.zsbbs.controller;

import cn.hutool.crypto.SecureUtil;
import com.zoom.zsbbs.entity.PostAuthor;
import com.zoom.zsbbs.entity.User;
import com.zoom.zsbbs.service.UserService;
import com.zoom.zsbbs.utils.JWTUtils;
import com.zoom.zsbbs.param.LoginResult;
import com.zoom.zsbbs.exception.UserResultCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

    //用户密码md5加密的盐值
    private static String MD5_SALT = "nslbhlsvhwnonchbshcbgiyvsivsmcsncndjcdvvmdlvmjdnvbbvhrnapbhd";

    @Autowired
    private UserService userService;

    //返回某个作者的信息
    @RequestMapping(value = "/querybyuserid", method = RequestMethod.POST)
    public PostAuthor queryUserByUserid(@RequestParam("userid") int userid){
        return userService.queryAuthorInfoByUserid(userid);
    }

    //http://localhost:8088/user/queryalluser
    @RequestMapping(value = "/queryalluser", method = RequestMethod.GET)
    public List<User> queryAllUser(){
        return userService.queryAllUser();
    }

    //http://localhost:8088/user/register
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public LoginResult userRegister(User user){
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        List<User> userList = userService.queryAllUser();
        for(int i = 0;i < userList.size();++i){
            if(userList.get(i).getUsername().equals(user.getUsername())){
                return LoginResult.error(UserResultCode.REGISTER_FAIL_USER_ALREADY_EXIST);
            }
        }

        //对用户密码进行加密
        String md5Password = SecureUtil.md5(user.getPassword() + MD5_SALT);
        user.setPassword(md5Password);

        int insertUserRes = userService.insertUser(user);
        System.out.println(user.getUserid());
        String token = JWTUtils.generateToken(user.getUserid());
        return LoginResult.ok().data("userid", user.getUserid()).data("usertype", 0).data("token", token);
    }

    //http://localhost:8088/user/login
    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResult userLogin(User user){
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        List<User> userList = userService.queryAllUser();

        //对传进来的用户密码进行加密
        String md5Password = SecureUtil.md5(user.getPassword() + MD5_SALT);


        for(int i = 0;i < userList.size();++i){
            if(userList.get(i).getUsername().equals(user.getUsername())){
                //if(userList.get(i).getPassword().equals(user.getPassword())){
                if(userList.get(i).getPassword().equals(md5Password)){
                    //password is correct
                    String token = JWTUtils.generateToken(userList.get(i).getUserid());
                    return LoginResult.ok().data("userid", userList.get(i).getUserid())
                            .data("avatar", userList.get(i).getAvatar())
                            .data("usertype", userList.get(i).getUsertype())
                            .data("userpostcount", userList.get(i).getUserpostcount())
                            .data("userreplycount", userList.get(i).getUserreplycount())
                            .data("token", token);
                }
                else {
                    //password is wrong
                    return LoginResult.error(UserResultCode.LOGIN_FAIL_WRONG_PASSWORD);
                }
            }
        }
        return LoginResult.error(UserResultCode.LOGIN_FAIL_USER_NOT_EXIST);
    }

    //http://localhost:8088/user/autologin
    //用户打开网站时自动登录
    @RequestMapping(value = "/autologin", method = RequestMethod.POST)
    public LoginResult userAutoLogin(@RequestParam("token") String token){
        try{
            Claims claims = JWTUtils.getClaimsByToken(token);
            System.out.println("TOKEN: " + claims);

            Date now = new Date();

            System.out.println("SUB: " + Long.parseLong((String) claims.get("sub")));
            System.out.println("EXP: " + Long.parseLong(claims.get("exp").toString()));
            System.out.println("NOW: " + now.getTime());

            if(Long.parseLong(claims.get("exp").toString()) * 1000 <= now.getTime()){
                //token已过期
                return LoginResult.error(UserResultCode.LOGIN_FAIL_TOKEN_EXPIRED);
            }
            else{
                //token未过期
                //获取用户列表
                List<User> userList = userService.queryAllUser();
                for(int i = 0;i < userList.size();++i){
                    if(userList.get(i).getUserid() == Integer.parseInt(claims.get("sub").toString())){
                        //token正确，用户存在
                        String _token = JWTUtils.generateToken(userList.get(i).getUserid());
                        return LoginResult.ok().data("userid", userList.get(i).getUserid())
                                .data("avatar", userList.get(i).getAvatar())
                                .data("usertype", userList.get(i).getUsertype())
                                .data("username", userList.get(i).getUsername())
                                .data("password", userList.get(i).getPassword())
                                .data("userpostcount", userList.get(i).getUserpostcount())
                                .data("userreplycount", userList.get(i).getUserreplycount())
                                .data("token", _token);

                    }
                }
                return LoginResult.error(UserResultCode.LOGIN_FAIL_USER_NOT_EXIST);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return LoginResult.error(UserResultCode.LOGIN_FAIL_TOKEN_EXPIRED);
    }


    //更新头像
    @RequestMapping(value = "/update/avatar", method = RequestMethod.POST)
    public int userChangeAvatar(@RequestParam("userid") int userid, @RequestParam("avatar") String avatar){
        System.out.println(userid);
        System.out.println(avatar);

        if(userService.updateAvatarByUserid(userid, avatar) == 1){
            return UserResultCode.UPDATE_SECCESS;
        }
        else {
            return UserResultCode.UPDATE_FAIL;
        }
    }

    //更新用户名
    @RequestMapping(value = "/update/username", method = RequestMethod.POST)
    public int userChangeUsername(@RequestParam("userid") int userid, @RequestParam("username") String username){
        System.out.println(userid);
        System.out.println(username);

        List<User> userList = userService.queryAllUser();
        for(int i = 0;i < userList.size();++i){
            if(userList.get(i).getUsername().equals(username)){
                return UserResultCode.UPDATE_FAIL;
            }
        }

        if(userService.updateUsernameByUserid(userid, username) == 1){
            return UserResultCode.UPDATE_SECCESS;
        }
        else {
            return UserResultCode.UPDATE_FAIL;
        }

    }

    //更新密码
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public int userChangePassword(@RequestParam("userid") int userid, @RequestParam("password") String password){
        System.out.println(userid);
        System.out.println(password);

        //对用户密码进行加密
        String md5Password = SecureUtil.md5(password + MD5_SALT);

        if(userService.updatePasswordByUserid(userid, md5Password) == 1){
            return UserResultCode.UPDATE_SECCESS;
        }
        else{
            return UserResultCode.UPDATE_FAIL;
        }
    }
}
