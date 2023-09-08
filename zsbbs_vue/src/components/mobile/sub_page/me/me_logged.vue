<template>
    <el-avatar shape="square" :size="150" :fit="fit" :src="avatar" /><br>
    <el-text class="UserInfoUsername" type="primary">{{username}}</el-text><br>
    <el-text class="UserInfoUserDescription">{{description}}</el-text><br>

    <div style="height: 30px;"/>

    <el-text style="font-size: 20px; color: black;">我的</el-text>
    <div style="height: 10px;"/>
    <div class="MeFunctionDiv">
        <div style="width: 1%;"></div>
        <el-card class="MeFunctionCard" shadow="hover" @click="goToSeeMyFollowto">
            <el-icon><CircleCheck /></el-icon>
            <br>
            <el-text>关注</el-text>
        </el-card>
        <div style="width: 1%;"></div>
        <el-card class="MeFunctionCard" shadow="hover" @click="goToSeeMyPost">
            <el-icon><Document /></el-icon>
            <br>
            <el-text>帖子</el-text>
        </el-card>
        <div style="width: 1%;"></div>
        <el-card class="MeFunctionCard" shadow="hover" @click="goToSeeMyBookmark">
            <el-icon><Star /></el-icon>
            <br>
            <el-text>收藏</el-text>
        </el-card>
        <div style="width: 1%;"></div>
        <el-card class="MeFunctionCard" shadow="hover" @click="goToSeeMyReply">
            <el-icon><ChatLineSquare /></el-icon>
            <br>
            <el-text>回复</el-text>
        </el-card>
        <div style="width: 1%;"></div>
    </div>

    <div style="height: 10px;"/>
    <div style="margin: 20px;">
        <van-button round block type="danger" @click="logoutMethod">
            退出登录
        </van-button>
    </div>
</template>

<script>
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
import { showConfirmDialog } from 'vant';

export default {
    created(){
        this.getLoggedUserInfo()
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
            follower: 0,
            followto: 0,
        }
    },
    methods:{
        //获取登陆用户的信息
        getLoggedUserInfo(){
            var queryUserParam = new URLSearchParams

            queryUserParam.append("userid", this.$store.state.s_userid)
            
            var _this = this

            axios.post('/user/querybyuserid', 
                queryUserParam
            )
            .then(function (response) {
                
                _this.userid = response.data.userid
                _this.avatar = response.data.avatar
                _this.usertype = response.data.usertype
                _this.username = response.data.username
                _this.userpostcount = response.data.userpostcount
                _this.userreplycount = response.data.userreplycount
                _this.description = response.data.description
                _this.follower = response.data.follower
                _this.followto = response.data.followto
                
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },

        // 用户登出方法
        logoutMethod(){
            showConfirmDialog({
                title: '登出',
                message:
                    '您确定要登出 ?',
            })
            .then(() => {
                // on confirm
                this.userid = -1
                this.avatar = "",
                this.usertype = -1
                this.username = ""
                this.password = ""
                this.description = ""
                this.$store.commit('syncUserDataToStore',{
                    userid: this.userid,
                    avatar: this.avatar,
                    usertype: this.usertype,
                    username: this.username,
                    password: this.password,
                    description: this.description
                })
                this.$store.commit('syncStoreToken',{
                    token: "token",
                })

                //删除之前登录的token
                if(this.$cookies.isKey("zsbbsLoginToken")){
                    this.$cookies.remove("zsbbsLoginToken")
                }
                this.$router.push('/zsbbs/mobile/main')
                this.$message.info('您已登出')
            })
            .catch(() => {
                // on cancel
            });

		},

        // "我的" 板块
        // 查看我的关注
        goToSeeMyFollowto(){
            this.$message.info("我的关注")
        },
        // 查看我的帖子
        goToSeeMyPost(){
            this.$message.info("我的帖子")
        },
        // 查看我的收藏
        goToSeeMyBookmark(){
            this.$message.info("我的收藏")
        },
        // 查看我的回复
        goToSeeMyReply(){
            this.$message.info("我的回复")
        },

    }
}
</script>

<style>
.UserInfoUsername{
    font-size: 30px;
    
}
.UserInfoUserDescription{

}
.MeFunctionDiv{
    display: flex;
    flex-direction: row;

    width: 100%;
}
.MeFunctionCard{
    width: 25%;
}
</style>