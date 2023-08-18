<template>
    <el-menu
        :default-active="$route.path"
        class="el-menu-vertical"
        @open="handleOpen"
        @close="handleClose"
        :router='true'
      >
        <el-menu-item index="/zsbbs/me/profile">
            
            <el-icon><User /></el-icon>
            <span>个人资料</span>
            
        </el-menu-item>
        <el-menu-item index="/zsbbs/me/follow">

            <el-icon><CircleCheck /></el-icon>
            <span>我的关注</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/post">
            
            <el-icon><Document /></el-icon>
            <span>我的帖子</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/bookmark">
            
            <el-icon><Star /></el-icon>
            <span>我的收藏</span>

        </el-menu-item>
        <el-menu-item index="/zsbbs/me/reply/myreply">
            
            <el-icon><ChatLineSquare /></el-icon>
            <span>我的回复</span>

        </el-menu-item>
    </el-menu>

    <router-view></router-view>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

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