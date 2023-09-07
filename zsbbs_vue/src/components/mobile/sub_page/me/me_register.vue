<template>
    <div style="height: 10px;"></div>
    <text style="font-size: 2em;">注册</text>
    <div style="height: 10px;"></div>

    <!-- 用户注册时的头像 -->
    <van-uploader 
        v-model="avatarfile" 
        :before-read="beforeReadAvatar"
        :after-read="afterReadAvatar" 
        :max-count="1"
        :max-size="400 * 1024" 
        @oversize="onOversize"
        preview-size="15rem"
    />

    <div>
        <van-cell-group inset>
            <van-field
                v-model="username"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
            />
            <van-field
                v-model="password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
            />
            <van-field
                v-model="password2"
                type="password"
                name="确认密码"
                label="确认密码"
                placeholder="确认密码"
                :rules="[{ required: true, message: '请再次输入密码' }]"
            />
        </van-cell-group>
        <div style="margin: 20px;">
            <van-button round block type="primary" @click="registerMethod">
                注册
            </van-button>
            <div style="height: 10px;"></div>
            <van-button round block type="default" @click="clearInput">
                清空
            </van-button>
            <div style="height: 10px;"></div>
            <van-button round block type="primary" @click="goToLoginMethod" plain>
                已有帐号 ? &nbsp;&nbsp; 前往登录
            </van-button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import default_avatar_base64 from "../../../../assets/default_avatar.js"

export default {
    created(){
        this.avatar = default_avatar_base64
    },
    data(){
        return{
            userid: -1,
            avatar: "",
            avatarfile: [],
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
        registerMethod(){
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
                        _this.$router.push('/zsbbs/mobile/main')
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
        //上传头像前
        beforeReadAvatar(file){
            const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 或 png 格式!');
                this.avatarfile = []
                return false;
            }
            return true;
        },
        //上传头像后
        afterReadAvatar(file){
            console.log(file)
            console.log(this.avatarfile)

            //将头像转为base64 并保存到avatar变量中
            var _this = this

            var reader = new FileReader();
            var blobFile = new Blob([file.file])
            reader.readAsDataURL(blobFile)
            reader.onload = function () {
                console.log(this.result)
                _this.avatar = this.result
            }
        },
        //头像过大
        onOversize(){
            this.$message.error('上传头像图片大小不能超过 400KB!');
            this.avatarfile = []
        },

        //跳转到登陆页面
        goToLoginMethod(){
            this.$router.push("/zsbbs/mobile/me")
        }
    }
}
</script>

<style>

</style>