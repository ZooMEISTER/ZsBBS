<template>
    <div style="height: 50px;"/>
    <el-card style="width: 600px; margin: auto;">
        <template #header>
            <div>
                <h1>注册</h1>
            </div>
        </template>
        <div>
            <div style="height:20px;"/>
            <el-form :model="form" label-width="120px" style="width:500px;margin:auto">
                <el-form-item label="头像 ">
                    <!-- <el-image style="width: 150px; height: 150px" :src="avatar" :fit="fit" /> -->
                    <el-upload
                        class="avatar-uploader"
                        action=""
                        :auto-upload="false"
                        :show-file-list="false"

                        :on-change="handleChange"
                        >
                        <img v-if="avatar" :src="avatar" class="avatar" />
                        <img v-else src="../../../assets/defaultavatar.png" class="avatar"/>
                    </el-upload>
                </el-form-item>
                <el-form-item label="用户名 ">
                    <el-input v-model="username" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="密码 ">
                    <el-input v-model="password" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="确认密码 ">
                    <el-input v-model="password2" placeholder="确认密码"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="loginMethod">注册</el-button>
                    <el-button @click="clearInput">清空</el-button>
                </el-form-item>
            </el-form>
            <div style="height:40px;"/>
        </div>
    </el-card>
</template>

<script>
import { Plus } from '@element-plus/icons-vue'
import axios from 'axios'
import default_avatar_base64 from "../../../assets/default_avatar.js"

export default {
    created(){
        this.avatar = default_avatar_base64

        console.log("this.avatar " + this.avatar)
    },
    data(){
        return{
            userid: -1,
            avatar: "",
            //avatarfile: "",
            usertype: -1,
            username: "",
            password: "",
            password2: "",
            userpostcount: 0,
            userreplycount: 0,
            description: "这个人很懒，没有简介",
            token: "",
        }
    },
    methods:{
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
        
        containSpace(str){
            if(str.indexOf(" ") == -1) return false
            else return true
        },
        //获取头像数据后再来注册
        loginMethod(){
            console.log(this.avatar)
            console.log(this.username)
            console.log(this.password)
            var registerParam = new URLSearchParams
            registerParam.append("avatar", this.avatar)
            registerParam.append("username", this.username)
            registerParam.append("password", this.password)
            registerParam.append("description", this.description)
            var _this = this

            var zg_u =  /^[0-9a-zA-Z]*$/;
            var zg_p = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]*$/;
            if(this.username == ""){
                this.$message.error("用户名不能为空")
            }
            else if(this.password == ""){
                this.$message.error("密码不能为空")
            }
            else if(this.password != this.password2){
                this.$message.error("两次输入的密码不同")
            }
            else if(this.containSpace(this.username)){
                this.$message.error("用户名不能包含空格")
            }
            else if(this.containSpace(this.password)){
                this.$message.error("密码不能包含空格")
            }
            else if(this.username.length < 4){
                this.$message.error("用户名至少需要4个字符")
            }
            else if(this.password.length < 6){
                this.$message.error("密码至少需要6位")
            }
            else if(!zg_u.test(this.username)){
                this.$message.error("用户名仅能包含字母和数字")
            }
            else if(!zg_p.test(this.password)){
                this.$message.error("密码仅能且必须包含字母和数字")
            }
            else{
                //注册用户信息请求
                axios.post('/user/register', 
                    registerParam
                )
                .then(function (response) {
                    if(response.data.code == 10000){
                        //注册成功
                        _this.userid = response.data.data.userid
                        _this.usertype = response.data.data.usertype
                        _this.$store.commit('syncStoreToken', {token: response.data.data.token})
                        _this.token = response.data.data.token
                        _this.logInSuccess()
                        _this.$router.push('/zsbbs/main')
                    }
                    else if(response.data.code == 10003){
                        //用户已存在
                        _this.$message.error("用户名已被占用")
                    }
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
        },
        //清空输入框
        clearInput(){
            this.username = ""
            this.password = ""
            this.password2 = ""
        },

        handleChange(file){
            console.log(file)

            //头像 格式 大小 检查
            const isJPG = (file.raw.type === 'image/jpeg') || (file.raw.type === 'image/png');
            const isLt400k = file.raw.size / 1024 <= 400;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 或 png 格式!');
                return;
            }
            if (!isLt400k) {
                this.$message.error('上传头像图片大小不能超过 400KB!');
                return;
            }
            
            //将头像转为base64 并保存到avatar变量中
            //this.avatarfile = file
            var _this = this

            var reader = new FileReader();
            var blobFile = new Blob([file.raw])
            reader.readAsDataURL(blobFile)
            reader.onload = function () {
                console.log(this.result)
                _this.avatar = this.result
            }

        },

    }
}
</script>


<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>