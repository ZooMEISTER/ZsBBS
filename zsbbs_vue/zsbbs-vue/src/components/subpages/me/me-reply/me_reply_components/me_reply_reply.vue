<template>
    <el-card shadow="hover" @click="goToPost">
        <!-- 显示我给别人的回复 -->
        <div v-if="p_replytype == 0">
            <div style="display: flex; flex-direction: column;">
                <div style="display: flex; flex-direction: row; align-items: center;">
                    回复给&nbsp;&nbsp;
                    <el-card style="align-items: center;">
                        <el-avatar :size="20" :src="targetPostAuthorAvatar" />
                        &nbsp;
                        <el-text type="primary" style="font-size: 20px;">
                            {{targetPostAuthorName}} 
                            &nbsp;
                        </el-text>
                        发布的 &nbsp;
                        <el-text type="primary" style="font-size: 20px;">
                            {{targetPostTitle}}
                        </el-text>
                    </el-card>
                </div>
                
                <div style="display: flex; flex-direction: column; align-self: flex-start; width: 100%;">
                    <div style="height: 10px;"/>

                    <el-card shadow="never" style="display: flex; width: 100%;">
                        <div style="align-self: flex-start; font-size: 25px;">
                            {{p_replycontent}}
                        </div>
                    </el-card>

                    <div style="height: 10px;"/>

                    <div style="align-self: flex-end;">
                        于&nbsp;&nbsp;
                        {{p_replytime}}&nbsp;&nbsp;&nbsp;&nbsp;
                        在&nbsp;
                        {{p_floor}}
                        &nbsp;楼
                    </div>
                
                </div>

            </div>
        </div>

        <!-- 显示别人给我的回复 -->
        <div v-if="p_replytype == 1">
            <div style="display: flex; flex-direction: column;">
                <div style="display: flex; flex-direction: row; align-items: center;">
                    &nbsp;&nbsp;
                    <el-avatar :size="50" :src="targetPostAuthorAvatar" />
                    &nbsp;&nbsp;
                    <el-text type="primary" style="font-size: 20px;">
                        {{targetPostAuthorName}}
                    </el-text>
                    &nbsp;&nbsp;回复于&nbsp;&nbsp;
                    <el-card>
                        <el-text type="primary" style="font-size: 20px;">
                            {{targetPostTitle}}
                        </el-text>
                    </el-card>
                </div>
                
                <div style="display: flex; flex-direction: column; align-self: flex-start; width: 100%;">
                    <div style="height: 10px;"/>

                    <el-card shadow="never" style="display: flex; width: 100%;">
                        <div style="align-self: flex-start; font-size: 25px;">
                            {{p_replycontent}}
                        </div>
                    </el-card>

                    <div style="height: 10px;"/>

                    <div style="align-self: flex-end;">
                        回复于&nbsp;&nbsp;
                        {{p_replytime}}&nbsp;&nbsp;&nbsp;&nbsp;
                        在&nbsp;
                        {{p_floor}}
                        &nbsp;楼
                    </div>
                
                </div>

            </div>

        </div>
    </el-card>

    <div style="height: 5px;"/>

</template>

<script>
import axios from 'axios'

export default {
    created(){
        this.getTargetPostInfo()
        this.getTargetPostAuthorInfo()
    },
    props:{
        p_replytype: Number,

        p_replyid: Number,
        p_replycontent: String,
        p_replypostid: Number,
        p_replyauthorid: Number,
        p_replytime: String,
        p_floor: Number,
        p_replypostauthorid: Number,
    },
    components:{
        
    },
    data(){
        return{
            //目标帖子的信息
            targetPostTitle: "",
            targetPostContent: "",
            targetPostReplyCount: 0,
            targetPostPublishTime: "",


            //目标帖子作者的信息
            targetPostAuthorName: "",
            targetPostAuthorAvatar: "",
        }
    },
    methods:{
        //获取目标帖子的信息
        getTargetPostInfo(){
            var queryTargetPostParam = new URLSearchParams

                queryTargetPostParam.append("postid", this.p_replypostid)

            var _this = this

            axios.post('/post/query/post',
                queryTargetPostParam
            )
            .then(function (response) {
                _this.targetPostTitle = response.data.title,
                _this.targetPostContent = response.data.content,
                _this.targetPostReplyCount = response.data.replycount,
                _this.targetPostPublishTime = response.data.publishtime,
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //获取目标帖子作者的信息
        getTargetPostAuthorInfo(){
            var queryTargetPostAuthorParam = new URLSearchParams

            if(this.p_replytype == 0){
                //查询我回复的那个帖子的作者信息
                queryTargetPostAuthorParam.append("userid", this.p_replypostauthorid)
            }
            else if(this.p_replytype == 1){
                //查询回复我的用户的信息
                queryTargetPostAuthorParam.append("userid", this.p_replyauthorid)
            }


            var _this = this

            axios.post('/user/querybyuserid',
                queryTargetPostAuthorParam
            )
            .then(function (response) {

                _this.targetPostAuthorName = response.data.username,
                _this.targetPostAuthorAvatar = response.data.avatar,
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //跳转到帖子详情页面
        goToPost(){
            if(this.p_replytype == 0){
                this.$router.push({ 
                    path: '/zsbbs/forum/postdetail', 
                    query: { 
                        postid: this.p_replypostid,
                        authorname: this.targetPostAuthorName,
                        authoravatar: this.targetPostAuthorAvatar,
                        title: this.targetPostTitle,
                        content: this.targetPostContent,
                        replycount: this.targetPostReplyCount,
                        authorid: this.p_replypostauthorid,
                        publishtime: this.targetPostPublishTime
                    }
                })
            }
            else if(this.p_replytype == 1){
                this.$router.push({ 
                    path: '/zsbbs/forum/postdetail', 
                    query: { 
                        postid: this.p_replypostid,
                        authorname: this.$store.state.s_username,
                        authoravatar: this.$store.state.s_avatar,
                        title: this.targetPostTitle,
                        content: this.targetPostContent,
                        replycount: this.targetPostReplyCount,
                        authorid: this.$store.state.s_userid,
                        publishtime: this.targetPostPublishTime
                    }
                })
            }
            
        }
    }
}
</script>

<style>

</style>