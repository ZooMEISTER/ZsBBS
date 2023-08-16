<template>
    <!-- <div>
        <h1>My_subReply</h1>
    </div> -->

    <el-card shadow="hover" @click="goToPost">
        <div v-if="p_subreplytype==0" style="width: 100%">
            <div v-if="p_subreplysubreplyid==0" style="width: 100%">
                <!-- 显示我回复的 目标为reply -->
                
                <div style="display: flex; flex-direction: row; align-items: center;">
                    回复给&nbsp;
                    <el-card style="display: flex; align-items: cneter;">
                        <el-avatar :size="20" :src="targetAuthorAvatar"/>
                        &nbsp;
                        <el-text size="large" type="primary">
                            {{targetAuthorName}}
                        </el-text>
                        :
                        {{targetReplyContent}}
                    </el-card>
                    &nbsp;
                    在&nbsp;
                    {{targetReplyFloor}}
                    &nbsp;楼
                    在&nbsp;
                    <el-text style="font-size: 20px;" type="primary">
                        {{targetPostTitle}}
                    </el-text>
                </div>
                <div style="height: 10px;"/>
                <el-card shadow="nevar" style="display:flex; align-items: flex-start; font-size: 25px;">
                    {{p_subreplycontent}}
                </el-card>
                <div style="height: 10px;"/>
                <div style="width: 100%; text-align: right;">
                    在 {{p_subreplytime}}
                </div>
            </div>
            <div v-else style="width: 100%">  
                <!-- 显示我回复的 目标为subreply -->
                <div style="display: flex; flex-direction: row; align-items: center;">
                    回复给&nbsp;
                    <el-card style="display: flex; align-items: cneter;">
                        <el-avatar :size="20" :src="targetAuthorAvatar"/>
                        &nbsp;
                        <el-text size="large" type="primary">
                            {{targetAuthorName}}
                        </el-text>
                        :
                        {{targetSubReplyContent}}
                    </el-card>
                    &nbsp;
                    在&nbsp;
                    {{targetReplyFloor}}
                    &nbsp;楼
                    在&nbsp;
                    <el-text style="font-size: 20px;" type="primary">
                        {{targetPostTitle}}
                    </el-text>
                </div>
                <div style="height: 10px;"/>
                <el-card shadow="nevar" style="display:flex; align-items: flex-start; font-size: 25px;">
                    {{p_subreplycontent}}
                </el-card>
                <div style="height: 10px;"/>
                <div style="width: 100%; text-align: right;">
                    在 {{p_subreplytime}}
                </div>
            </div>

        </div>
        <div v-else-if="p_subreplytype==1">
            <div v-if="p_subreplysubreplyid==0" style="width: 100%">
                <!-- 显示回复我的 目标为reply -->
                
                <div style="display: flex; flex-direction: row; align-items: center;">
                    <el-avatar :size="50" :src="targetAuthorAvatar"/>
                    &nbsp;
                    <el-text type="primary" style="font-size: 20px;">
                        {{targetAuthorName}}
                    </el-text>

                    &nbsp;回复给&nbsp;

                    <el-card style="display: flex; align-items: cneter;">
                        <el-text size="large" type="success">
                            我
                        </el-text>
                        :
                        {{targetReplyContent}}
                    </el-card>
                    &nbsp;
                    在&nbsp;
                    {{targetReplyFloor}}
                    &nbsp;楼
                    在&nbsp;
                    <el-text style="font-size: 20px;" type="primary">
                        {{targetPostTitle}}
                    </el-text>
                </div>
                <div style="height: 10px;"/>
                <el-card shadow="nevar" style="display:flex; align-items: flex-start; font-size: 25px;">
                    {{p_subreplycontent}}
                </el-card>
                <div style="height: 10px;"/>
                <div style="width: 100%; text-align: right;">
                    在 {{p_subreplytime}}
                </div>
            </div>
            <div v-else style="width: 100%">  
                <!-- 显示回复我的 目标为subreply -->
                <div style="display: flex; flex-direction: row; align-items: center;">
                    <el-avatar :size="50" :src="targetAuthorAvatar"/>
                    &nbsp;
                    <el-text type="primary" style="font-size: 20px;">
                        {{targetAuthorName}}
                    </el-text>

                    &nbsp;回复给&nbsp;

                    <el-card style="display: flex; align-items: cneter;">
                        <el-text size="large" type="success">
                            我
                        </el-text>
                        :
                        {{targetSubReplyContent}}
                    </el-card>
                    &nbsp;
                    在&nbsp;
                    {{targetReplyFloor}}
                    &nbsp;楼
                    在&nbsp;
                    <el-text style="font-size: 20px;" type="primary">
                        {{targetPostTitle}}
                    </el-text>
                </div>
                <div style="height: 10px;"/>
                <el-card shadow="nevar" style="display:flex; align-items: flex-start; font-size: 25px;">
                    {{p_subreplycontent}}
                </el-card>
                <div style="height: 10px;"/>
                <div style="width: 100%; text-align: right;">
                    在 {{p_subreplytime}}
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
        if(this.p_subreplysubreplyid==0){
            //回复的目标是reply
            this.getTargetReplyInfo(this.p_subreplyreplyid)
        }
        else{
            //回复的目标是subreply
            this.getTargetSubReplyInfo()

        }

        this.getTargetAuthorInfo(0)
        
    },
    props:{
        p_subreplytype: Number,

        p_subreplyid: Number,
        p_subreplycontent: String,
        p_subreplyreplyid: Number,
        p_subreplyauthorid: Number,
        p_subreplytime: String,
        p_subreplysubreplyid: Number,
        p_subreplytargetauthorid: Number,
    },
    components:{
        
    },
    data(){
        return{
            //当回复的是 reply 时，用这三个数据
            targetReplyContent: "",
            targetReplyPostid: 0,
            

            //当回复的是 subreply 时
            targetSubReplyContent: "",

            //目标的作者信息
            targetAuthorName: "",
            targetAuthorAvatar: "",

            //subreply所属的reply的楼数
            targetReplyFloor: 0,

            
            

            //目标帖子的信息
            targetPostTitle: "",
            targetPostContent: "",
            targetPostReplyCount: 0,
            targetPostAuthorid: 0,
            targetPostPublishTime: "",

            //目标帖子的作者信息
            targetPostAuthorName: "",
            targetPostAuthorAvatar: "",
        }
    },
    methods:{
        //当 p_subreplysubreplyid==0 说明该 subreply 是对 reply 的
        //则获取目标reply信息
        getTargetReplyInfo(srrid){
            var queryTargetReplyParam = new URLSearchParams

            queryTargetReplyParam.append("replyid", srrid)
            
            var _this = this

            axios.post('/post/query/sreply',
                queryTargetReplyParam
            )
            .then(function (response) {
                
                _this.targetReplyContent = response.data.replycontent,
                _this.targetReplyPostid = response.data.replypostid,
                _this.targetReplyFloor = response.data.floor,
                
                _this.getTargetPostInfo()

                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //当 p_subreplysubreplyid==1 说明该 subreply 是对 subreply 的
        //则获取目标subreply信息
        getTargetSubReplyInfo(){
            var queryTargetSubReplyParam = new URLSearchParams

            queryTargetSubReplyParam.append("subreplyid", this.p_subreplysubreplyid)
            
            var _this = this

            axios.post('/post/query/targetsubreply',
                queryTargetSubReplyParam
            )
            .then(function (response) {
                
                if(response.data == ""){
                    _this.targetSubReplyContent = "[Content Delete]"
                }
                else{
                    _this.targetSubReplyContent = response.data.subreplycontent
                }
                
                
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });

            this.getTargetReplyInfo(this.p_subreplyreplyid)

        },
        getTargetAuthorInfo(ut){
            var queryTargetAuthorParam = new URLSearchParams

            if(ut == 0){
                if(this.p_subreplytype == 0){
                    //查询我回复的那个的作者信息
                    queryTargetAuthorParam.append("userid", this.p_subreplytargetauthorid)
                }
                else if(this.p_subreplytype == 1){
                    //查询回复我的用户的信息
                    queryTargetAuthorParam.append("userid", this.p_subreplyauthorid)
                }
            }
            else if(ut == 1){
                //查询目标帖子的作者信息
                queryTargetAuthorParam.append("userid", this.targetPostAuthorid)
            }


            var _this = this

            axios.post('/user/querybyuserid',
                queryTargetAuthorParam
            )
            .then(function (response) {
                
                if(ut == 0){
                    _this.targetAuthorName = response.data.username
                    _this.targetAuthorAvatar = response.data.avatar
                }
                else if(ut == 1){
                    _this.targetPostAuthorName = response.data.username
                    _this.targetPostAuthorAvatar = response.data.avatar
                }
                
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //获取目标帖子
        getTargetPostInfo(){
            var queryTargetPostParam = new URLSearchParams

            queryTargetPostParam.append("postid", this.targetReplyPostid)

            console.log(this.targetReplyPostid);

            var _this = this

            axios.post('/post/query/post',
                queryTargetPostParam
            )
            .then(function (response) {
                _this.targetPostTitle = response.data.title,
                _this.targetPostContent = response.data.content,
                _this.targetPostReplyCount = response.data.replycount,
                _this.targetPostAuthorid = response.data.authorid
                _this.targetPostPublishTime = response.data.publishtime,

                console.log(response.data);

                _this.getTargetAuthorInfo(1)
                
                
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //跳转到帖子详情页面
        goToPost(){
            
            this.$router.push({ 
                path: '/zsbbs/forum/postdetail', 
                query: { 
                    postid: this.targetReplyPostid,
                    authorname: this.targetPostAuthorName,
                    authoravatar: this.targetPostAuthorAvatar,
                    title: this.targetPostTitle,
                    content: this.targetPostContent,
                    replycount: this.targetPostReplyCount,
                    authorid: this.targetPostAuthorid,
                    publishtime: this.targetPostPublishTime
                }
            })
            
            
        }
    }
}
</script>

<style>

</style>