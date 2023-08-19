<template>
    <el-dialog class="UserInfoDialog" v-model="userInfoDialogVisiable">
        <!-- <text>{{p_targetuserid}}</text> -->
        <div class="UserInfoDialogInner">
            <div class="UserInfoDialogTitle">
                <div style="width: 50px;"></div>
                <el-avatar :size="180" :src="targetUserAvatar"/>
                <div style="width: 50px;"></div>
                <div style="display: flex; flex-direction: column; text-align: left;">
                    <text class="UserInfoName">{{targetUserName}}</text>
                    <text class="UserInfoPostCount">发帖数: {{targetUserPostCount}}</text>
                    <text class="UserInfoDescription">简介: {{targetUserDescription}}</text>
                </div>
                <div style="height: 100%; width: 15%;">
                    <el-button v-if="!isLoggedUserFollowThisUser" class="FollowButton" type="primary" @click="userFollow">关注</el-button>
                    <el-button v-else class="FollowButton" type="primary" @click="userFollow" plain>取消关注</el-button>
                </div>
            </div>
            <div style="height: 50px"></div>
            <text style="font-size: 20px;">Ta 的帖子</text>
            <div style="height: 20px"></div>
            <div class="UserInfoDialogContent">
                <el-scrollbar height="1000px">
                    <div style="height: 10px"></div>
                    <Comp_ShowAllPost 
                        :p_postType=4
                        :p_targetUserId="p_targetuserid"
                        ref="ref_Comp_ShowAllPost"
                    ></Comp_ShowAllPost>
                    <div style="height: 10px"></div>
                </el-scrollbar>
                
            </div>
        </div>
    </el-dialog>
</template>

<script>
import axios from 'axios'

import Comp_ShowAllPost from "./subpages/forum/post/showpost.vue"

export default {
    components:{
        Comp_ShowAllPost
    },
    props:{
        p_targetuserid: Number,
    },
    data(){
        return{
            userInfoDialogVisiable: false,

            targetUserAvatar: "",
            targetUserName: "",
            targetUserPostCount: "",
            targetUserDescription: "",
            targetUserFollower: 0,  //关注目标用户的用户数
            targetUserFollowto: 0,  //目标用户关注的用户数

            //表示已登录用户是否关注了这个用户
            isLoggedUserFollowThisUser: false,
        }
    },
    mounted(){
        //先判断用户是否登录
        if(this.$store.state.s_userid > 0){
            //查询数据库，查找已登录用户是否关注了这个用户
            var queryFollowParam = new URLSearchParams

            queryFollowParam.append("followeduserid", this.p_targetuserid)
            queryFollowParam.append("followeruserid", this.$store.state.s_userid)
            var _this = this

            axios.post('/user/querysinglefollow', 
                queryFollowParam
            )
            .then(function (response) {
                if(response.data == true){
                    _this.isLoggedUserFollowThisUser = true
                }
                else{
                    _this.isLoggedUserFollowThisUser = false
                }

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        }
        
    },
    methods:{
        init(){
            //显示弹窗
            this.userInfoDialogVisiable = true


            //加载用户数据
            //包括用户的头像，名称，简介
            //和用户所发的帖子
            //发送获取用户信息的请求
            //查询目标用户的 userid 为 this.p_targetuserid
            var queryUserParam = new URLSearchParams

            queryUserParam.append("userid", this.p_targetuserid)
            var _this = this

            axios.post('/user/querybyuserid', 
                queryUserParam
            )
            .then(function (response) {
                _this.targetUserAvatar = response.data.avatar
                _this.targetUserName = response.data.username
                _this.targetUserPostCount = response.data.userpostcount
                _this.targetUserDescription = response.data.description
                _this.targetUserFollower = response.data.follower
                _this.targetUserFollowto = respong.data.followto

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

            this.$nextTick(() => {
                console.log("loading posts")
                this.$refs.ref_Comp_ShowAllPost.getAllPosts("pt", true)
            })
            
        },

        //用户点击关注或取消关注按钮
        userFollow(){
            //先判断用户是否登录
            if(this.$store.state.s_userid > 0){
                if(this.$store.state.s_userid != this.p_targetuserid){
                    if(this.isLoggedUserFollowThisUser){
                        //用户未关注，开始关注
                        //向后端发送开始关注请求
                        var delFollowParam = new URLSearchParams

                        delFollowParam.append("followeduserid", this.p_targetuserid)
                        delFollowParam.append("followeruserid", this.$store.state.s_userid)
                        var _this = this

                        axios.post('/user/delfollow', 
                            delFollowParam
                        )
                        .then(function (response) {
                            if(response.data > 0){
                                _this.$message.info("取消关注成功")
                            }
                            
                            _this.isLoggedUserFollowThisUser = false

                            //console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                        
                    }
                    else{
                        //用户已关注，取消关注
                        //向后端发送取消关注请求
                        var addFollowParam = new URLSearchParams

                        addFollowParam.append("followeduserid", this.p_targetuserid)
                        addFollowParam.append("followeruserid", this.$store.state.s_userid)
                        var _this = this

                        axios.post('/user/addfollow', 
                            addFollowParam
                        )
                        .then(function (response) {
                            if(response.data > 0){
                                _this.$message.success("关注成功")
                            }

                            _this.isLoggedUserFollowThisUser = true

                            //console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                        
                    }
                }
                else{
                    this.$message.info("不可以关注自己 :)")
                }
            }
            else{
                this.$message.info("请先登录")
            }
            
        }
    }
    
}
</script>

<style>
.UserInfoDialog{
    height: 1350px;
    width: 800px;
}
.UserInfoDialogInner{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
}



.UserInfoDialogTitle{
    height: 30%;
    width: 100%;
    display: flex;
    flex-direction: row;
    /* background: blue; */
    align-items: center;
}
.UserInfoName{
    font-size: 50px;
    color: rgb(78, 196, 243);
}
.UserInfoPostCount{
    font-size: 15px;
    color: rgb(70, 70, 70);
}
.UserInfoDescription{
    font-size: 25px;
    color: black;
    width: 300px;
    background: rgb(248, 248, 248);
}
.FollowButton{
    margin: auto;
}


.UserInfoDialogContent{
    height: 70%;
    width: 100%;
    display: flex;
    flex-direction: column;
    /* background: red; */
}
</style>