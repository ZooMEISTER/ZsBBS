<template>
    <div style="z-index: 10">
        <TopNavigateBar class="TopNavigateBar"
            :p_userid="this.$store.state.s_userid"
            :p_avatar="this.$store.state.s_avatar"
            :p_usertype="this.$store.state.s_usertype"
            :p_username="this.$store.state.s_username"
        ></TopNavigateBar>
    </div>
    <div style="top: 0px; height: 60px;"/>
        <router-view></router-view>
        <div style="width: 100%; height: 100px;">
        </div>
        <div style="width: 100%; height: 30px;">
            by ZooMEISTER
        </div>
</template>

<script>
import axios from 'axios'

import TopNavigateBar from "./components/top-navigate-bar.vue"

export default {
    name: 'App',
    created(){
        //this.$message.info("-- APP CREATED --")

        //在这里执行用户的自动登录
        this.autoLogin()
    },
    components: {
        TopNavigateBar,
    },
    data(){
        return{
            userid: -1,
            avatar: "",
            usertype: -1,
            username: "",
            password: "",
            userpostcount: 0,
            userreplycount: 0,
            description: "",
            token: "",

            //当前激活的路由路径
            activeIndex: "",
        }
    },
    methods:{
        //登陆成功
        logInSuccess(){
            //成功登陆后与store同步数据
            this.$store.commit('syncUserDataToStore',{
                userid: this.userid,
                avatar: this.avatar,
                usertype: this.usertype,
                username: this.username,
                password: this.password,
                userpostcount: this.userpostcount,
                userreplycount: this.userreplycount,
                description: this.description,
            })

            //将返回的token写入到cookie
            this.$cookies.set("zsbbsLoginToken", this.token, -1)
        },
        autoLogin(){
            //JWT
            if(this.$cookies.isKey("zsbbsLoginToken")){
                //存在之前登录留下的token
                //尝试自动登录
                //将cookie中的token发送给后端
                //发送自动登录请求
                var loginParam = new URLSearchParams
                loginParam.append("token", this.$cookies.get("zsbbsLoginToken"))
                
                var _this = this
                axios.post('/user/autologin', 
                    loginParam
                )
                .then(function (response) {
                    if(response.data.code == 10000){
                        //登陆成功
                        console.log(response)
                        _this.userid = response.data.data.userid
                        _this.avatar = response.data.data.avatar
                        _this.usertype = response.data.data.usertype
                        _this.username = response.data.data.username
                        _this.password = response.data.data.password
                        _this.userpostcount = response.data.data.userpostcount
                        _this.userreplycount = response.data.data.userreplycount
                        _this.description = response.data.data.description
                        _this.token = response.data.data.token
                        _this.$store.commit('syncStoreToken', {token: _this.token})
                        _this.logInSuccess()
                        //_this.$router.push('/zsbbs/main')
                    }
                    else if(response.data.code == 10001){
                        _this.$message.error("用户不存在")
                        this.$cookies.remove("zsbbsLoginToken")
                    }
                    else if(response.data.code == 10002){
                        _this.$message.error("密码错误")
                    }
                    else if(response.data.code == 10005){
                        _this.$message.error("TOKEN已过期")
                        this.$cookies.remove("zsbbsLoginToken")
                    }
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
            else{
                //不存在之前登录留下的token
                //不执行自动登录

            }
        },
    }
}
</script>

<style>
.TopNavigateBar{
    position: fixed; 
    top: 0px; 
    left: 0px; 
    width: 100%; 
    z-index: 100;
}
</style>