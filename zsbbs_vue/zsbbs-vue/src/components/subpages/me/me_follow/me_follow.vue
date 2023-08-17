<template>
    <div class="BaseDiv">
        <!-- <text>my follow</text> -->
        <div style="height: 30px;"></div>
        <el-radio-group v-model="selectedFollow" size="large" @change="changeReplyDisplay">
            <el-radio-button label="我的关注"/>
            <el-radio-button label="关注我的"/>
        </el-radio-group>
        <div style="height: 10px;"></div>
        <div v-if="followType==0" class="FollowBackground">
            <Comp_MeFollowSingle v-for="(sfollow, index) in followList"
                :p_index="index"
                :key="sfollow.followid"
                :p_targetuserid="sfollow.followeduserid"
                :p_followtime="sfollow.followtime"
            >
            </Comp_MeFollowSingle>
        </div>
        <div v-else-if="followType==1" class="FollowBackground">
            <Comp_MeFollowSingle v-for="(sfollow, index) in followList"
                :p_index="index"
                :key="sfollow.followid"
                :p_targetuserid="sfollow.followeruserid"
                :p_followtime="sfollow.followtime"
            >
            </Comp_MeFollowSingle>
        </div>
        
        <div style="height: 10px;"></div>

        <div style="width: 800px;">
            <el-pagination
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="prev, pager, next, jumper"
                :page-size="pageSize"
                :total="totalCount"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
            />
        </div>
    </div>
</template>

<script>
import axios from 'axios'

import Comp_MeFollowSingle from "./me_follow_single.vue"

export default {
    components:{
        Comp_MeFollowSingle
    },
    data(){
        return{
            followList: [],

            //followType 为 0 则返回指定用户所关注的用户列表
            //           为 1 则返回关注了指定用户的用户列表
            followType: 0,

            selectedFollow: "我的关注",

            currentPage: 1,
            pageSize: 20,
            totalCount: 0,
        }
    },
    created(){
        this.getMyFollow(this.$store.state.s_userid, this.followType)
    },
    mounted(){

    },
    methods:{

        //获取指定用户的所有关注列表
        //userid为指定的用户 
        //type 为 0 则返回指定用户所关注的用户列表
        //     为 1 则返回关注了指定用户的用户列表
        //返回的用户列表均保存在 followList 中
        getMyFollow(userid, type){
            var getAllFollowParam = new URLSearchParams

            getAllFollowParam.append("userid", userid)
            getAllFollowParam.append("pagenum", this.currentPage)
            getAllFollowParam.append("pagesize", this.pageSize)


            var _this = this

            var getFollowURL
            if(type == 0){
                getFollowURL = '/user/getallfollowto'
            }
            else if(type == 1){
                getFollowURL = '/user/getallfollower'
            }

            axios.post(getFollowURL, getAllFollowParam)
                .then( function (response) {

                    _this.totalCount = response.data.followCount
                    _this.followList = response.data.followList

                })
                .catch(function (error) {
                    console.log(error)
                })
        },
        //切换选项卡
        changeReplyDisplay(){
            if(this.selectedFollow == "我的关注"){
                this.followType = 0
                this.getMyFollow(this.$store.state.s_userid, this.followType)

                this.currentPage = 1;
                this.getMyFollow(this.$store.state.s_userid, this.followType)
            }
            else if(this.selectedFollow == "关注我的"){
                this.followType = 1
                this.getMyFollow(this.$store.state.s_userid, this.followType)

                this.currentPage = 1;
                this.getMyFollow(this.$store.state.s_userid, this.followType)
            }
        },
        //更换分页
        handleCurrentChange(val){
            this.currentPage = val
            console.log(this.currentPage)

            this.getMyFollow(this.$store.state.s_userid, this.followType)

            this.toTop()
        },
        //强制回到顶端
        toTop() {
            document.documentElement.scrollTop = 0;
        },

    }
}
</script>

<style>
.BaseDiv{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.FollowBackground{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>