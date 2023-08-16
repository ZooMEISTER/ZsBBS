<template>
    <div v-if="p_postType==0">
        <el-card shadow="hover" style="width:70%; height:200px; margin:auto; background-color:rgb(250,250,250);" @click="seePostDetail">
            <div style="display:flex; flex-direction:row; height:100%;">
                <div style="width:100px;"/>
                <!-- 帖子的标题和内容摘要 -->
                <div style="display:flex; flex-direction:column; width:60%;">
                    <div style="height: 12px;"></div>
                    <el-text class="title" type="primary"><h1>标题: {{p_title}}</h1></el-text>
                    <el-text class="content" truncated="true"><h3>内容: {{content}}</h3></el-text>
                </div>
                <!-- 帖子的次要信息 -->
                <div style="display:flex; flex-direction:column; width:30%; height: 100%; margin:auto;">
                    <div style="height: 20px;"></div>
                    <el-text class="replycount" type="info">回复数: {{p_replycount}}</el-text>
                    <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">
                            by:
                            &nbsp;&nbsp;
                            <el-avatar :size="40" :src="authoravatar" />
                            &nbsp;&nbsp;
                            {{authorname}}
                    </el-text>
                    <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">发布于:&nbsp;&nbsp;{{p_publishtime}}</el-text>
                    <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">回复于:&nbsp;&nbsp;{{p_latestreplytime}}</el-text>
                    <!-- <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">Max Floor:&nbsp;&nbsp;{{p_maxfloor}}</el-text> -->
                </div>
            </div>
                
        </el-card>
        <div style="height:10px"></div>
    </div>
    <div v-else-if="p_postType==1">
        <el-card shadow="hover" style="width:70%; height:170px; margin:auto; background-color:rgb(250,250,250);" @click="seePostDetail">
            <div style="display:flex; flex-direction:row; height:100%;">
                <div style="width:100px;"/>
                <!-- 帖子的标题和内容摘要 -->
                <div style="display:flex; flex-direction:column; width:60%;">
                    <!-- <div style="height: 12px;"></div> -->
                    <el-text class="title" type="primary"><h1>标题: {{p_title}}</h1></el-text>
                    <el-text class="content" truncated="true"><h3>内容: {{content}}</h3></el-text>
                </div>
                <!-- 帖子的次要信息 -->
                <div style="display:flex; flex-direction:column; width:30%; height: 100%; margin:auto;">
                    <!-- <div style="height: 20px;"></div> -->
                    <el-text class="replycount" type="info">回复数: {{p_replycount}}</el-text>
                    <!-- <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">
                            by:
                            &nbsp;&nbsp;
                            <el-avatar :size="40" :src="authoravatar" />
                            &nbsp;&nbsp;
                            {{authorname}}
                    </el-text> -->
                    <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">发布于:&nbsp;&nbsp;{{p_publishtime}}</el-text>
                    <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">回复于:&nbsp;&nbsp;{{p_latestreplytime}}</el-text>
                    <!-- <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">Max Floor:&nbsp;&nbsp;{{p_maxfloor}}</el-text> -->
                </div>
            </div>
                
        </el-card>
        <div style="height:10px"></div>
    </div>
    <div v-if="p_postType==2">
        <el-card shadow="hover" style="width:90%; height:175px; margin:auto; background-color:rgb(250,250,250);" @click="seePostDetail">
            <div style="display:flex; flex-direction:row; height:100%;">
                <div style="width:100px;"/>
                <!-- 帖子的标题和内容摘要 -->
                <div style="display:flex; flex-direction:column; width:60%;">
                    <div style="height: 0px;"></div>
                    <el-text class="title" type="primary"><h1>标题: {{p_title}}</h1></el-text>
                    <el-text class="content" truncated="true"><h3>内容: {{content}}</h3></el-text>
                </div>
                <!-- 帖子的次要信息 -->
                <div style="display:flex; flex-direction:column; width:30%; height: 100%; margin:auto;">
                    <div style="height: 0px;"></div>
                    <el-text class="replycount" type="info">回复数: {{p_replycount}}</el-text>
                    <div style="height: 5px;"></div>
                    <el-text class="replycount" type="info">
                            by:
                            &nbsp;&nbsp;
                            <el-avatar :size="40" :src="authoravatar" />
                            &nbsp;&nbsp;
                            {{authorname}}
                    </el-text>
                    <div style="height: 5px;"></div>
                    <el-text class="replycount" type="info">发布于:&nbsp;&nbsp;{{p_publishtime}}</el-text>
                    <div style="height: 5px;"></div>
                    <el-text class="replycount" type="info">回复于:&nbsp;&nbsp;{{p_latestreplytime}}</el-text>
                    <!-- <div style="height: 10px;"></div>
                    <el-text class="replycount" type="info">Max Floor:&nbsp;&nbsp;{{p_maxfloor}}</el-text> -->
                </div>
            </div>
                
        </el-card>
        <div style="height:10px"></div>
    </div>
    
</template>

<script>
import axios from 'axios'
export default {
    created(){
        this.getAuthorInfo()
        this.content = this.p_content.replace(/\\n/gm,"\n");
    },
    props:{
        //0 是显示广场上的所有帖子  1 表示是用户发的帖子  2表示是搜索出来的帖子
        p_postType: Number, 

        p_index: Number,
        p_postid: Number,
        p_title: String,
        p_content: String,
        p_replycount: Number,
        p_authorid: Number,
        p_publishtime: String,
        p_maxfloor: Number,
        p_latestreplytime: Number,
        p_containimage: Number,
    },
    data(){
        return{
            authorname: "",
            authoravatar: "" ,
            content: "",
        }
    },
    methods:{
        //进入帖子
        seePostDetail(){
            console.log(this.p_postid + "  " + this.p_title)
            //this.$message.success(this.p_postid + "  " + this.p_title)

            this.$router.push({ 
                path: '/zsbbs/forum/postdetail', 
                query: { 
                    postid: this.p_postid,
                    // authorname: this.authorname,
                    // authoravatar: this.authoravatar,
                    // title: this.p_title,
                    // content: this.p_content,
                    // replycount: this.p_replycount,
                    authorid: this.p_authorid,
                    // publishtime: this.p_publishtime,
                    // containimage: this.p_containimage
                }
            })
        },
        //获取发布者的信息
        getAuthorInfo(){
            var queryAutherParam = new URLSearchParams

            queryAutherParam.append("userid", this.p_authorid)
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
        }
    }
}
</script>

<style>

.title{
    display: inherit;
    text-align: left;
    align-self: flex-start;
}
.content{
    display: inherit;
    text-align: left;
    align-self: flex-start;
    white-space: pre-wrap;
}
.replycount{
    display: flex;
    text-align: left;
    align-items: center;
}
</style>