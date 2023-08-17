<template>
    <el-menu
        default-active="2"
        class="el-menu-vertical"
        @open="handleOpen"
        @close="handleClose"
        :router='true'
      >
        <el-menu-item index="/zsbbs/me/profile">

            <span>个人资料</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/post">
            
            <span>我的帖子</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/bookmark">
            
            <span>我的收藏</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/reply/myreply">
            
            <span>我的回复</span>

        </el-menu-item>
    </el-menu>

    <router-view></router-view>
</template>

<script>
export default {
    
}
</script>

<style>
.el-menu-vertical{
    width: 10%;
    height: 100%;
    position: fixed;
}
</style>