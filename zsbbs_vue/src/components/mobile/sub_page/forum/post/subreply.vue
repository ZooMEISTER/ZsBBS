<template>

    <div v-if="p_subreplysubreplyid==0">
        <!-- 是对普通回复的楼中楼回复 -->

        <div class="SubReplyAvatarAndNameDiv">
            <div style="width: 10px;"></div>
            <el-avatar :size="35" :src="subreplyauthoravatar" />
            <div style="width: 10px;"></div>
            <el-text style="font-size: 0.9em;">{{subreplyauthorname}}</el-text>
        </div>
        <div class="SubReplyContentDiv">
            <div style="width: 55px;"></div>
            <el-text style="font-size: 1em; color: black;">{{p_subreplycontent}}</el-text>
        </div>
        <div style="height: 10px;"></div>
        <div class="SubReplySubInfoDiv">
            <el-text style="font-size: 10px;">{{p_subreplytime}} &nbsp;&nbsp;&nbsp;&nbsp;</el-text>
        </div>
        

    </div>
    <div v-else>
        <!-- 是对楼中楼回复的楼中楼回复 -->

        <div class="SubReplyAvatarAndNameDiv">
            <div style="width: 10px;"></div>
            <el-avatar :size="35" :src="subreplyauthoravatar" />
            <div style="width: 10px;"></div>
            <el-text style="font-size: 0.9em;">{{subreplyauthorname}}</el-text>
        </div>
        <div class="SubReplyContentDiv">
            <div style="width: 55px;"></div>
            <el-text style="font-size: 1em; color: black;">
                回复
                <el-text type="primary" style="font-size: 1em;">{{targetSubreplyAuthorName}}</el-text>
                : 
                {{p_subreplycontent}}
            </el-text>
        </div>
        <div style="height: 10px;"></div>
        <div class="SubReplySubInfoDiv">
            <el-text style="font-size: 10px;">{{p_subreplytime}} &nbsp;&nbsp;&nbsp;&nbsp;</el-text>
        </div>

    </div>

    

    <van-divider :style="{borderColor: '#555555', padding: '0 7px'}"/>

</template>

<script>
import axios from 'axios'

export default {
    created(){

        this.getSubReplyAuthorInfo()

        if(this.p_subreplysubreplyid != 0){
            this.getTargetSubReplyAuthorInfo()
            this.getTargetSubreplyInfo()
        }
        
    },
    props:{
        p_subreplyid: Number,
        p_subreplycontent: String,
        p_subreplyreplyid: Number,
        p_subreplyauthorid: Number,
        p_subreplytargetauthorid: Number,
        p_subreplytime: String,
        p_subreplysubreplyid: Number,
    },
    data(){
        return{
            subreplyauthorname: "",
            subreplyauthoravatar: "",

            targetSubreplyAuthorName: "",
            targetSubreplyAuthorAvatar: "",
            targetSubreplyContent: "",
        }
    },
    methods:{
        //获取当前这个楼中楼回复的作者信息
        getSubReplyAuthorInfo(){
            var queryAuthorParam = new URLSearchParams

            queryAuthorParam.append("userid", this.p_subreplyauthorid)

            var _this = this

            //查询用户信息请求
            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {

                _this.subreplyauthorname = response.data.username
                _this.subreplyauthoravatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //获取 目标楼中楼回复 的 作者信息（仅当 p_subreplysubreplyid != 0 时）
        getTargetSubReplyAuthorInfo(){
            var queryAuthorParam = new URLSearchParams

            queryAuthorParam.append("userid", this.p_subreplytargetauthorid)

            var _this = this

            //查询用户信息请求
            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {

                _this.targetSubreplyAuthorName = response.data.username
                _this.targetSubreplyAuthorAvatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //获取目标楼中楼的内容（仅当 p_subreplysubreplyid != 0 时）
        getTargetSubreplyInfo(){
            var queryTargetSubreplyParam = new URLSearchParams

            queryTargetSubreplyParam.append("subreplyid", this.p_subreplysubreplyid)
            var _this = this

            //查询用户信息请求
            axios.post('/post/query/targetsubreply', 
                queryTargetSubreplyParam
            )
            .then(function (response) {
                if(response.data != null){
                    
                    _this.targetSubreplyContent = response.data.subreplycontent

                }
                else{
                    _this.targetSubreplyContent = "内容已删除"
                }
                
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
    }
}
</script>

<style>
.SubReplyAvatarAndNameDiv{
    display: flex;
    flex-direction: row;
}
.SubReplyContentDiv{
    display: flex;
    flex-direction: row;
}
.SubReplySubInfoDiv{
    align-self: flex-end;
    text-align: right;
}
</style>