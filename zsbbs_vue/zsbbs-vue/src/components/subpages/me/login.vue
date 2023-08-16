<template>
    <div style="height: 100px;"/>
    <el-card style="width: 30%; margin: auto;">
        <template #header>
            <div>
                <h1>登录</h1>
            </div>
        </template>
        <div>
            <div style="height:50px;"/>
            <el-form :model="form" label-width="120px" style="width:500px;margin:auto">
                <el-form-item label="用户名 ">
                    <el-input v-model="username" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="密码 ">
                    <el-input v-model="password" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="loginMethod">登录</el-button>
                    <el-button @click="clearInput">清空</el-button>
                </el-form-item>
            </el-form>
            <div style="height:50px;"/>
        </div>
    </el-card>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            userid: -1,
            avatar: "",
            usertype: -1,
            username: "",
            password: "",
            userpostcount: 0,
            userreplycount: 0,
            token: "",
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
            })

            //将返回的token写入到cookie
            this.$cookies.set("zsbbsLoginToken", this.token, -1)
        },
        containSpace(str){
            if(str.indexOf(" ") == -1) return false
            else return true
        },
        //登录方法
        loginMethod(){
            console.log(this.username)
            console.log(this.password)
            var loginParam = new URLSearchParams
            loginParam.append("username", this.username)
            loginParam.append("password", this.password)
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
                //发送登录请求
                axios.post('/user/login', 
                    loginParam
                )
                .then(function (response) {
                    if(response.data.code == 10000){
                        //登陆成功
                        console.log(response)
                        _this.userid = response.data.data.userid
                        _this.avatar = response.data.data.avatar
                        _this.usertype = response.data.data.usertype
                        _this.userpostcount = response.data.data.userpostcount
                        _this.userreplycount = response.data.data.userreplycount
                        _this.token = response.data.data.token
                        _this.$store.commit('syncStoreToken', {token: _this.token})
                        _this.logInSuccess()
                        _this.$router.push('/zsbbs/main')
                    }
                    else if(response.data.code == 10001){
                        _this.$message.error("用户不存在")
                    }
                    else if(response.data.code == 10002){
                        _this.$message.error("密码错误")
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
        }
    }
}
</script>

<style>

</style>