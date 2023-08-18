<template>
    <div>
        <div style="height: 5px;"></div>
        <el-card class="FollowCard">
            <div class="FollowCardInner">
                <div class="FollowCardAvatar">
                    <el-avatar :size="80" :src="targetUserAvatar"/>
                </div>
                
                <div class="FollowCardContent">
                    <el-link type="primary" style="font-size: 30px;" @click="showUserInfo">{{targetUserName}}</el-link>
                    <text>{{targetUserDescription}}</text>
                </div>

                <div class="FollowCardTime">
                    <text style="font-size: 10px;">关注时间: {{p_followtime}}</text>
                </div>
                
                
            </div>
            
        </el-card>
    </div>

    <!-- 用户信息弹窗 -->
    <Comp_UserInfoForm v-if="showUserInfoForm" ref="uIF" :p_targetuserid="p_targetuserid"></Comp_UserInfoForm>
</template>

<script>
import axios from 'axios'

import Comp_UserInfoForm from "../../../user_info_form.vue"

export default {
    components:{
        Comp_UserInfoForm
    },
    props:{
        p_targetuserid: Number,
        p_followtime: String
        // p_targetusertype: Number
    },
    data(){
        return{
            targetUserAvatar: "",
            targetUserName: "",
            targetUserDescription: "",
            targetUserFollower: 0,
            targetUserFollowto: 0,

            showUserInfoForm: false,
        }
    },
    created(){

        this.getUserInfo()

    },
    methods:{
        //根据userid获取该用户信息
        getUserInfo(){
            var getUserInfoParam = new URLSearchParams

            getUserInfoParam.append("userid", this.p_targetuserid)

            var _this = this


            axios.post("/user/querybyuserid", getUserInfoParam)
                .then( function (response) {

                    _this.targetUserAvatar = response.data.avatar,
                    _this.targetUserName = response.data.username,
                    _this.targetUserDescription = response.data.description,
                    _this.targetUserFollower = response.data.follower,
                    _this.targetUserFollowto = response.data.followto

                    console.log(response.data)
                })
                .catch(function (error) {
                    console.log(error)
                })
        },

        //弹出弹窗，展示用户基本信息
        showUserInfo(){
            console.log("show user info")
            this.showUserInfoForm = true
            this.$nextTick(() => {
                this.$refs.uIF.init();          
            });
        },

        
    }
}
</script>

<style>
.FollowCard{
    height: 120px;
    width: 800px;
}
.FollowCardInner{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.FollowCardAvatar{
    display: flex;
    flex-direction: column;
    margin-left: 50px;
    margin-top: 0px;
}
.FollowCardContent{
    display: flex;
    flex-direction: column;
    width: 55%;
    align-items: flex-start;
    margin-left: 30px;
}
.FollowCardTime{
    display: flex;
}
</style>