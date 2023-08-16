<template>
  <div>
    <el-menu
    default-active="/zsbbs/main"
    class="el-menu"
    mode="horizontal"
    :ellipsis="false"
	:router='true'
    @select="handleSelect"
	style="height:60px;"
	ref="menuRef"
    >
		<div style="width:20px"/>
		<el-menu-item index="/zsbbs/main" style="height:100%">主页</el-menu-item>
		<el-menu-item index="/zsbbs/news_blank" style="height:100%">ZooMEISTER</el-menu-item>
		<el-menu-item index="/zsbbs/forum" style="height:100%">论坛</el-menu-item>
		<div class="flex-grow" />

		<div v-if="p_userid != -1" style="margin:auto">
			<el-text style="margin:auto;text-align:center;" type="primary" size="large">欢迎 ! </el-text>
			<el-text style="margin:auto" type="success" size="large">{{p_username}}</el-text>
		</div>
		<div style="width:20px"/>
		<div v-if="p_userid != -1" style="margin:auto">
			<el-avatar :size="40" :src="p_avatar" />
		</div>

		<el-sub-menu index="4" style="height:100%">
			<template #title>我</template>
			<div v-if="p_usertype === -1">
				<el-menu-item index="/zsbbs/me/login">登录</el-menu-item>
				<el-menu-item index="/zsbbs/me/register">注册</el-menu-item>
			</div>
			<div v-else-if="p_usertype === 0">
				<el-menu-item index="/zsbbs/me/profile">个人资料</el-menu-item>
				<el-menu-item index="/zsbbs/me/post">我的帖子</el-menu-item>
				<el-menu-item index="/zsbbs/me/reply/myreply">我的回复</el-menu-item>
				<el-menu-item @click="logoutMethod">登出</el-menu-item>
			</div>
			<div v-else-if="p_usertype === 1">
				<el-menu-item index="/zsbbs/me/profile">个人资料</el-menu-item>
				<el-menu-item index="/zsbbs/me/post">我的帖子</el-menu-item>
				<el-menu-item index="/zsbbs/me/reply/myreply">我的回复</el-menu-item>
				<el-menu-item @click="logoutMethod">登出</el-menu-item>
			</div>
		</el-sub-menu>
		<div style="width:20px"/>
    </el-menu>
  </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus'
import {ref} from 'vue'


export default{
	mounted(){
		this.menuRef = document.getElementById('menuRef')
		console.log(this.menuRef)
	},
	props: { 
		p_userid: Number,
		p_avatar: String,
		p_usertype: Number,
		p_username: String,
		p_password: String
	},
  	data(){
		return{
			userid: -1,
			avatar: "",
			usertype: -1,
			username: "",
			password: "",

			menuRef: "",
		}
	},
	methods: {
		logoutMethod(){
			ElMessageBox.confirm(
                    '您确定要登出 ?',
                    '登出',
                    {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        type: 'warning',
                        center: false,
                    })
                    .then(() => {
						this.userid = -1
						this.avatar = "",
						this.usertype = -1
						this.username = ""
						this.password = ""
						this.$store.commit('syncUserDataToStore',{
							userid: this.userid,
							avatar: this.avatar,
							usertype: this.usertype,
							username: this.username,
							password: this.password
						})
						this.$store.commit('syncStoreToken',{
							token: "token",
						})

						//删除之前登录的token
						if(this.$cookies.isKey("zsbbsLoginToken")){
							this.$cookies.remove("zsbbsLoginToken")
						}
						this.$router.push('/zsbbs/main')
						this.$message.info('您已登出')
                    })
                    .catch(() => {
                        //this.$message.info('cancel logout')
                    })
		},
		// handleSelect(key, keypath){
		// 	if(key == "logout"){
				
		// 	}
		// },
	}
}
</script>

<style>
.flex-grow {
  	flex-grow: 1;
}
</style>