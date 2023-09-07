<template>
    <div class="ZsBBSTitle">
        <el-button type="primary" @click="ShowLeftPopup" class="MenuButton" text>
            <el-icon :size="40"><Menu /></el-icon>
        </el-button>

        <!-- <el-text class="Title">
            ZsBBS
        </el-text> -->
    </div>

    <div style="height: 3.5em;"></div>
    <!-- <h1>zoomeister</h1> -->
    <router-view></router-view>




    <van-popup
        v-model:show="showLeftPopup"
        position="left"
        :style="{ width: '50%', height: '100%' }"
        >
        <div style="height: 30px;"></div>
        <img class="LOGOImg" src="../../assets/img/Z/newZ.png"/>
        <div style="height: 20px;"></div>
        <!-- 弹出层 这里放menu -->
        <el-menu
            :default-active="$route.path"
            :router='true'
            @open="handleOpen"
            @close="handleClose"
        >
        <el-menu-item index="/zsbbs/mobile/main">
            <el-icon><HomeFilled /></el-icon>
			<span>主页</span>
        </el-menu-item>
        <el-menu-item index="/zsbbs/mobile/zoomeister">
            <el-icon><Avatar /></el-icon>
			<span>ZooMEISTER</span>
        </el-menu-item>
        <el-menu-item index="/zsbbs/mobile/forum/section">
            <el-icon><Management /></el-icon>
			<span>论坛</span>
        </el-menu-item>

        <div class="flex-grow" />

        <el-menu-item index="/zsbbs/mobile/me">
            <el-icon><User /></el-icon>
			<span>我</span>
        </el-menu-item>
      </el-menu>

    </van-popup>
</template>

<script> 
export default {
    created(){
        console.log(this.$route.path)
    },
    mounted(){
    },
    data(){
        return{
            showLeftPopup: false
        }
    },
    methods:{
        ShowLeftPopup(){
            this.showLeftPopup = !this.showLeftPopup
            console.log("开关侧边栏 " + this.showLeftPopup)
        }
    }
}
</script>

<style>
.LOGOImg{
    height: 4em;
    width: 4em;
}
.ZsBBSTitle{
    display: flex;
    flex-direction: row;
    background: white;
    width: 100%;
    position: fixed; 
    top: 0px; 
    left: 0px; 
    z-index: 100;
}
.MenuButton{
    height: 4em;
    width: 4em;
}
.Title{
    width: 100%;
    font-size: 3em;
    color: rgb(64,158,255);
}
.flex-grow {
  	flex-grow: 1;
}
</style>