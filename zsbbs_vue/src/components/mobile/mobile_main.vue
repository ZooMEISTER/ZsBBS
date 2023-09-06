<template>
    <div class="ZsBBSTitle">
        <el-button type="primary" @click="ShowLeftPopup" class="MenuButton" text>
            <el-icon :size="40"><Menu /></el-icon>
        </el-button>

        <el-text class="Title">
            ZsBBS
        </el-text>
    </div>

    <!-- <h1>zoomeister</h1> -->
    <router-view></router-view>




    <van-popup
        v-model:show="showLeftPopup"
        position="left"
        :style="{ width: '50%', height: '100%' }"
        >
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
        <el-menu-item index="3" disabled>
            <el-icon><Management /></el-icon>
			<span>论坛</span>
        </el-menu-item>
      </el-menu>

    </van-popup>
</template>

<script> 
export default {
    created(){
        console.log(this.$route.path)
        // if(this.$route.path == "/zsbbs/main" || this.$route.path == "/"){
        //     this.$router.push("/zsbbs/mobile/main")
        // }
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
.ZsBBSTitle{
    display: flex;
    flex-direction: row;
    width: 100%;
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
</style>