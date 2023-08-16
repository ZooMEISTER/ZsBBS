<template>

    <!-- <div>
        <h1>My Reply</h1>
    </div> -->

    <div style="height: 50px;"/>

    <el-radio-group v-model="selectedReply" size="large" @change="changeReplyDisplay">
      <el-radio-button label="我的回复"/>
      <el-radio-button label="回复我的"/>
      <el-radio-button label="我的楼中楼回复"/>
      <el-radio-button label="回复我的楼中楼"/>
    </el-radio-group>
    
    <router-view></router-view>

</template>

<script>


export default {
    components:{
        
    },
    data(){
        return{
            selectedReply: "我的回复",
        }
    },
    methods:{
        changeReplyDisplay(){
            if(this.selectedReply == "我的回复"){
                this.$router.push('/zsbbs/me/reply/myreply')
            }
            else if(this.selectedReply == "我的楼中楼回复"){
                this.$router.push('/zsbbs/me/reply/mysubreply')
            }
            else if(this.selectedReply == "回复我的"){
                this.$router.push('/zsbbs/me/reply/replyme')
            }
            else if(this.selectedReply == "回复我的楼中楼"){
                this.$router.push('/zsbbs/me/reply/subreplyme')
            }
        }
    }
}
</script>

<style>

</style>