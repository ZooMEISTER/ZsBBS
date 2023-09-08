<template>
    <div class="ReplyDiv">
        <!-- <text>{{p_replyid}}</text> <br>
        <text>{{replycontent}}</text> <br>
        <text>{{p_replytime}}</text> <br> -->

        <!-- 回复的发布者的头像和用户名 -->
        <div class="AuthorAvatarandNameDiv">
            <div style="width: 10px;"></div>
            <el-avatar :size="35" :src="replayAuthorAvatar" />
            <div style="width: 10px;"></div>
            <el-text>{{replyAuthorName}}</el-text>
        </div>

        <!-- 回复的内容 -->
        <div class="ReplyContentDiv">
            <div style="width: 55px;"></div>
            <div class="ReplyContentInnerDiv">
                <el-text class="ReplyContent">
                    {{replyContent}}
                </el-text>
            </div>
        </div>
        <div style="height: 50px;"></div>

        <!-- 回复的图片 -->
        <div class="ReplyImageDiv">
            <el-image v-for="(simage, index) in allImage"
                :key="simage.replyimageid"
                :index="index"

                :src="simage.replyimagepath"

                class="SReplyImage"

                v-lazy="simage"
            >
            </el-image>
        </div>
        <div style="height: 10px;"></div>

        <!-- 回复的次要信息 -->
        <div class="ReplySubInfo">
            <el-text style="font-size: 10px; text-align:left; margin:left; align-self:flex-end;">
                {{p_floor}}
                &nbsp;楼
                &nbsp;&nbsp;&nbsp;&nbsp;
                回复于:
                &nbsp;
                {{p_replytime}}
                &nbsp;&nbsp;&nbsp;&nbsp;
            </el-text>
        </div>

        <div v-if="allSubReplyCount > 0" style="height: 10px;"></div>
        <!-- 楼中楼回复 -->
        <van-button plain v-if="allSubReplyCount > 0" style="width: 95%; margin: auto;" @click="seeSubReply">
            查看回复（{{allSubReplyCount}}）
        </van-button>

    </div>

    <van-divider :style="{borderColor: '#555555', padding: '0 7px'}"/>


    <!-- 显示楼中楼回复时的遮罩层 -->
    <van-overlay :show="showSubReply" @click="showSubReply = false" />
    <!-- 显示楼中楼回复的浮动面板 -->
    <van-floating-panel v-if="showSubReply==true" v-model:height="floatPanelHeight" :anchors="anchors" :content-draggable="false" :lock-scroll="true">

        <!-- <div style="width: 100%;">
            <p>面板显示高度 {{ floatPanelHeight }} px</p>
        </div> -->

        <div class="AllSubReplyDiv" v-infinite-scroll="getAllSubReply" infinite-scroll-immediate="false" infinite-scroll-distance="200">
            <Comp_Mobile_SingleSubreply
                v-for="(ssubreply, index) in allSubReply"
                    :p_index="index"
                    :key="ssubreply.subreplyid"

                    :p_subreplyid="ssubreply.subreplyid"
                    :p_subreplycontent="ssubreply.subreplycontent"
                    :p_subreplyreplyid="ssubreply.subreplyreplyid"
                    :p_subreplyauthorid="ssubreply.subreplyauthorid"
                    :p_subreplytargetauthorid="ssubreply.subreplytargetauthorid"
                    :p_subreplytime="ssubreply.subreplytime"
                    :p_subreplysubreplyid="ssubreply.subreplysubreplyid"
            >
            </Comp_Mobile_SingleSubreply>
        </div>

        <el-text>下面没有了 :)</el-text>
        <div style="height: 30px;"/>

    </van-floating-panel>

</template>

<script>
import axios from 'axios'

import Comp_Mobile_SingleSubreply from "./subreply.vue"

export default {
    created(){
        this.getAuthorInfo()
        this.replyContent = this.p_replycontent.replace(/\\n/gm,"\n");

        this.getContainImage()
        this.getAllSubReply()
    },
    components:{
        Comp_Mobile_SingleSubreply
    },
    props:{
        p_replyid: Number,
        p_containimage: Number,
        p_floor: Number,
        p_replyauthorid: Number,
        p_replycontent: String,
        p_replypostauthorid: Number,
        p_replypostid: Number,
        p_replytime: String,
    },
    data(){
        return{
            replyAuthorName: "",
            replayAuthorAvatar: "",
            replyContent: "",

            allImage: [],

            pagenum: 0,
            pagesize: 10,

            //该回复的所有楼中楼回复 和 楼中楼回复的条数
            allSubReply: [],
            allSubReplyCount: 0,

            //显示楼中楼回复的浮动面板的高度 和 锚点
            floatPanelHeight: 0,
            anchors:[
                100,
                Math.round(0.8 * window.innerHeight),
            ],
            //是否显示楼中楼回复
            showSubReply: false,

        }
    },
    methods:{
        //获取当前回复发布者的信息
        getAuthorInfo(){
            var queryAutherParam = new URLSearchParams

            queryAutherParam.append("userid", this.p_replyauthorid)
            var _this = this

            //查询用户信息请求
            axios.post('/post/query/author', 
                queryAutherParam
            )
            .then(function (response) {
                _this.replyAuthorName = response.data.username
                _this.replayAuthorAvatar = response.data.avatar

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //获取回复的图片数据
        getContainImage(){
            if(this.p_containimage > 0){
                //console.log("containimage " + this.p_containimage)
                
                var queryReplyImageParam = new URLSearchParams

                queryReplyImageParam.append("replyimagereplyid", this.p_replyid)
                var _this = this

                axios.post('/upload/queryreplyimage', 
                    queryReplyImageParam
                )
                .then(function (response) {
                    _this.allImage = response.data

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });


            }
        },
        //获取该回复的所有楼中楼回复
        getAllSubReply(){
            console.log("new new new")

            this.pagenum = this.pagenum + 1

            var querysubReplyParam = new URLSearchParams

            querysubReplyParam.append("subreplyreplyid", this.p_replyid)
            querysubReplyParam.append("pagenum", this.pagenum)
            querysubReplyParam.append("pagesize", this.pagesize)
            
            var _this = this

            //获取所有楼中楼回复
            axios.post('/post/query/subeplybypagenum', 
               querysubReplyParam
            )
            .then(function (response) {

                _this.allSubReplyCount = response.data.subReplyCount
                _this.allSubReply = _this.allSubReply.concat(response.data.subReplyList)

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //查看楼中楼回复
        seeSubReply(){
            this.showSubReply = true
        }
    }
}
</script>

<style>
.ReplyDiv{
    display: flex;
    flex-direction: column;

    width: 100%;
}
.AuthorAvatarDiv{
    display: flex;
    flex-direction: row;
}
.ReplyContentDiv{
    display: flex;
    flex-direction: row;
}
.ReplyContentInnerDiv{
    display: flex;
    flex-direction: column;
}
.ReplyContent{
    font-size: 1.1em;
    color: black;
}
.ReplyImageDiv{
    width: 100%;
    display: flex;
    flex-direction: column;
}
.SReplyImage{
    width: 95%;
    margin: auto;
    margin-top: 5px;
}
.ReplySubInfo{
    align-self: flex-end;
}

.AllSubReplyDiv{

}
</style>