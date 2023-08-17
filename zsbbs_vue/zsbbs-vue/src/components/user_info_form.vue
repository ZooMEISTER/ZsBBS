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

import Comp_ShowAllPost from "./subpages/forum/showpost.vue"

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
        }
    },
    mounted(){
        
        
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

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

            this.$nextTick(() => {
                console.log("loading posts")
                this.$refs.ref_Comp_ShowAllPost.getAllPosts("pt", true)
            })
            
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
}


.UserInfoDialogContent{
    height: 70%;
    width: 100%;
    display: flex;
    flex-direction: column;
    /* background: red; */
}
</style>