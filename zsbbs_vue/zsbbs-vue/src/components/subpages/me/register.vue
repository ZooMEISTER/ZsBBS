<template>
    <div style="height: 50px;"/>
    <el-card style="width: 30%; margin: auto;">
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
export default {
    created(){
        // this.avatarfile = new File(["../../../assets/defaultavatar.png"], "da.png")
        // console.log("avatarfile" + this.avatarfile.raw)
    },
    data(){
        return{
            userid: -1,
            avatar: "data:image/png;base64,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",
            //avatarfile: "",
            usertype: -1,
            username: "",
            password: "",
            userpostcount: 0,
            userreplycount: 0,
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
            var _this = this

            var zg_u =  /^[0-9a-zA-Z]*$/;
            var zg_p = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]*$/;
            if(this.username == ""){
                this.$message.error("用户名不能为空")
            }
            else if(this.password == ""){
                this.$message.error("密码不能为空")
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
        },

        handleChange(file){
            console.log("ttttt")
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