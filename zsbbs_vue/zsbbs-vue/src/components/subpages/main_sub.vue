<template >
    
    <div>
        <!-- <h1>主页</h1> -->
        <!-- <div>{{this.$store.state.s_token}}</div> -->
        <el-card class="welcomeCard">
            <template #header>
            <div class="card-header">
                <text class="welText">
                    WELCOME
                </text>
            </div>
            </template>
            <div style="display: flex; flex-direction: row;">
                <img class="ZImg" alt="Vue logo" src="../../assets/img/Z/newZ.png">

                <div style="height: 480px; width: 60%; display: flex;">
                    <text style="margin: auto; font-size: 20px; text-align: left;">
                        欢迎，这是ZooMEISTER的个人网站 <br>
                        网站内容包括了这个人↑的一些信息 <br>
                        还包括一个简易的论坛 <br>
                        <br>
                        想使用论坛相关功能，请先右上角登录 <br>
                        <br>
                        前端：Vue 3 + VueX + VueRouter + GSAP + Element UI <br>
                        后端：Springboot + Mybatis Plus + Mysql <br>
                        开发工具：VSCode + Idea + Navicat <br>
                        部署：阿里云ECS + CentOS + Nginx <br>
                        <br>
                        登录使用 JWT 方案 <br>
                        <br>
                        [ 若出现bug，请刷新页面 :) ] <br>
                    </text>
                </div>
            </div>
            
        </el-card>
        
    </div>
</template>

<script>
import axios from 'axios'

export default {
    created(){
        //console.log(typeof(msg))
    },
    data(){
        return{
            
        }
    },
    methods:{
    


    }
}
</script>

<style>
::-webkit-scrollbar {
    width: 1; 
    height: 3;
}

.welcomeCard{
    width: 60%;
    height: 700px;
    margin: auto;
    margin-top: 80px;
}
.welText{
    font-size: 50px;
}
.ZImg{
    height: 40%;
    width: 40%;
}
</style>