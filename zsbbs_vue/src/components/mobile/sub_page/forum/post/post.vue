<template>
    <div style="height: 5px;"></div>
    <div  v-if="p_posttype==0">
        <el-card shadow="hover" class="PostCard" @click="GoToSeePostDetail">
            <div class="PostCardInner">
                <el-text class="PostTitle" type="primary">{{title}}</el-text>
                <div style="height: 5px;"></div>
                <el-text class="PostContent">{{content}}</el-text>

                <div style="height: 10px;"></div>


                <el-text class="PostData" type="info">
                    By: {{authorname}}
                </el-text>
                <div style="height: 5px;"></div>
                <el-text class="PostData" type="info">
                    <el-icon><ChatDotSquare /></el-icon>: {{replycount}}
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-icon><View /></el-icon>:&nbsp;&nbsp;{{visitcount}}
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-icon><Star /></el-icon>:&nbsp;&nbsp;{{bookmarkcount}}
                </el-text>
                
            </div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    created(){
        this.getPostInfo()
    },
    props:{
        p_posttype: Number, // 0 为广场上的帖子
        p_postid: Number
    },
    data(){
        return{
            //帖子的信息
            title: "",
            content: "",
            authorid: 0,
            replycount: 0,
            publishtime: "",
            maxfloor: 0,
            latestreplytime: "",
            containimage: 0,
            visitcount: 0,
            bookmarkcount: 0,
            sectionid: 0,

            authorname: "",
            authoravatar: "" ,
        }
    },
    methods:{
        getPostInfo(){
            var queryPostInfoParam = new URLSearchParams
            var _this = this

            queryPostInfoParam.append("postid", this.p_postid)

            //获取该section的信息
            axios.post('/post/query/post', 
                    queryPostInfoParam
                )
                .then(function (response) {

                    _this.title = response.data.title
                    _this.content = response.data.content
                    _this.authorid = response.data.authorid
                    _this.replycount = response.data.replycount
                    _this.publishtime = response.data.publishtime
                    _this.maxfloor = response.data.maxfloor
                    _this.latestreplytime = response.data.latestreplytime
                    _this.containimage = response.data.containimage
                    _this.visitcount = response.data.visitcount
                    _this.bookmarkcount = response.data.bookmarkcount
                    _this.sectionid = response.data.sectionid

                    _this.getAuthorInfo()

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        //获取发布者的信息
        getAuthorInfo(){
            var queryAutherParam = new URLSearchParams

            queryAutherParam.append("userid", this.authorid)
            var _this = this

            //更改用户头像请求
            axios.post('/post/query/author', 
                queryAutherParam
            )
            .then(function (response) {
                _this.authorname = response.data.username
                _this.authoravatar = response.data.avatar

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },

        //进入帖子 查看帖子详情
        GoToSeePostDetail(){
            this.$message.info("查看帖子 postid: " + this.p_postid)
        }
    }
}
</script>

<style>
.PostCard{
    width: 97%;
    margin: auto;
}
.PostCardInner{
    display: flex;
    flex-direction: column;

    width: 100%;

    text-align: left;
}
.PostTitle{
    font-size: 23px;
    width: 100%;
    text-align: left;
}
.PostContent{
    width: 100%;
    text-align: left;
}
.PostData{
    width: 100%;
    text-align: right;
}
</style>