<template>
    <el-avatar shape="square" :size="150" :fit="fit" :src="$store.state.s_avatar" /><br>
    <text class="UserInfoText">userid: {{$store.state.s_userid}}</text><br>
    <text class="UserInfoText">username: {{$store.state.s_username}}</text><br>
    <text class="UserInfoText">usertype: {{$store.state.s_usertype}}</text><br>
    <text class="UserInfoText">description: {{$store.state.s_description}}</text><br>

    <div style="margin: 20px;">
        <van-button round block type="danger" @click="logoutMethod">
            退出登录
        </van-button>
    </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus'
import { showConfirmDialog } from 'vant';

export default {
    data(){
        return{
            userid: -1,
			avatar: "",
			usertype: -1,
			username: "",
			password: "",
            description: "",
        }
    },
    methods:{
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
    }
}
</script>

<style>
.UserInfoText{
    width: 100%; 
    word-wrap: break-word;
}
</style>