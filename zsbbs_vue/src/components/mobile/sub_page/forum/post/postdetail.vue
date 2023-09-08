<template>

    <!-- 板块信息 -->
    <el-tag size="large" effect="plain" class="SectionTag" @click="backToSection">
        <text>
            {{sectionname}}
        </text>
    </el-tag>

    <div style="height: 10px;"/>

    <!-- 帖子信息 -->
    <div class="PostDetailDiv">

        <!-- 帖子作者头像和姓名 -->
        <div class="AuthorAvatarandNameDiv">
            <div style="width: 10px;"></div>
            <el-avatar :size="35" :src="authoravatar" />
            <div style="width: 10px;"></div>
            <el-text style="font-size: 1em;">{{authorname}}</el-text>
        </div>
        <div style="height: 10px;"></div>

        <!-- 帖子标题 -->
        <div class="AuthorAvatarandNameDiv">
            <div style="width: 15px;"></div>
            <el-text class="PostTitle">{{title}}</el-text>
        </div>
        <div style="height: 10px;"></div>

        <!-- 帖子内容 -->
        <div class="AuthorAvatarandNameDiv">
            <div style="width: 15px;"></div>
            <el-text class="PostContent">{{content}}</el-text>
        </div>
        <div style="height: 50px;"></div>

        <!-- 帖子图片 -->
        <div class="PostImageDiv">
            <el-image v-for="(simage, index) in allImage"
                :key="simage.postimageid"
                :index="index"

                :src="simage.postimagepath"

                class="SPostImage"

                v-lazy="simage"
            >
            </el-image>
        </div>
        <div style="height: 20px;"></div>

        <!-- 帖子次要信息 -->
        <div class="PostSubInfo">
            <el-text style="font-size: 10px; text-align:left; margin:left; align-self:flex-end;">
                回复数:
                &nbsp;
                {{replycount}} 
                &nbsp;&nbsp;&nbsp;&nbsp;
                发布于:
                &nbsp;
                {{publishtime}}
                &nbsp;&nbsp;&nbsp;&nbsp;
            </el-text>
        </div>
        <van-divider :style="{borderColor: '#555555', padding: '0 7px'}"/>
        <!-- <div style="height: 10px;"></div> -->

        <!-- 回复和收藏ActionBar -->
        <van-action-bar style="z-index: 1;">
            <van-action-bar-icon v-if="isPostBookmarked==false" icon="star-o" text="收藏" color="#888888" @click="bookmarkPost"/>
            <van-action-bar-icon v-else icon="star" text="已收藏" color="#ff5000" @click="bookmarkPost"/>
            <van-action-bar-button type="primary" text="回复" @click="addNewReply"/>
            <van-action-bar-icon v-if="authorid == this.$store.state.s_userid" icon="delete-o" text="删除" color="#ff5000" @click="deletePost"/>
        </van-action-bar>

        

        <!-- 帖子回复 -->
        <div class="PostAllReplyDiv" v-infinite-scroll="getAllReply" infinite-scroll-immediate="false" infinite-scroll-distance="200">
            <Comp_Mobile_SingleReply
                v-for="(sreply, index) in allReply"
                    :p_index="index"
                    :key="sreply.replyid"

                    :p_replyid="sreply.replyid"
                    :p_containimage="sreply.containimage"
                    :p_floor="sreply.floor"
                    :p_replyauthorid="sreply.replyauthorid"
                    :p_replycontent="sreply.replycontent"
                    :p_replypostauthorid="sreply.replypostauthorid"
                    :p_replypostid="sreply.replypostid"
                    :p_replytime="sreply.replytime"
            ></Comp_Mobile_SingleReply>
        </div>

    </div>

    <el-text>下面没有了 :)</el-text>

    <div style="height: 80px;"></div>


    <!-- 添加新回复的对话框 -->
    <van-overlay :show="showAddNewReplyDialog" :lock-scroll="true">
        <div style="height: 15%;"></div>
        <div class="AddNewReplyDialogDiv">
            <div style="height: 15px;"></div>
            <el-text>添加新的回复</el-text>
            <div style="height: 15px;"></div>
            <span>
                <el-input v-model="newReplyContent" placeholder="回复内容" style="width:95%;" type="textarea" rows="10"/>
            </span>

            <div style="height: 10px;"/>

            <van-uploader 
                v-model="imageList" 
                :before-read="beforeReadAvatar"
                :after-read="afterReadAvatar" 
                :max-count="3"
                :max-size="2 * 1024 * 1024" 
                @oversize="onOversize"
                preview-size="6rem"

                style="margin: auto;"
            />

            <!-- 发布新回复的 取消 和 确认 -->
            <div class="AddNewReplyButtonDiv">
                <van-button class="AddNewReplyButton" type="warning" plain @click="cancelAddNewReply">取消</van-button>
                <div style="width: 10px;"/>
                <van-button class="AddNewReplyButton" type="primary" @click="confirmAddNewReply">确认</van-button>
            </div>

            <div style="height: 15px;"></div>
        </div>
    </van-overlay>

    <!-- 回到顶部 -->
    <van-back-top class="BackTopButton"/>
</template>

<script>
import axios from 'axios'
import { showConfirmDialog } from 'vant';

import Comp_Mobile_SingleReply from "./reply.vue"

export default {
    created(){
        this.postid = this.$route.query.postid

        this.getPostInfo()
        this.checkIfPostBookmarked()
        this.getAllReply()
    },
    mounted(){
        //console.log(this.$refs.favButton.text)
        // this.$refs.favButton.text = "444"
    },
    components:{
        Comp_Mobile_SingleReply
    },
    data(){
        return{
            sectionname: "",
            sectiondescription: "",

            postid: 0,

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

            //帖子的回复和图片
            allReply: [],
            allImage: [],

            //表示该帖子是否被当前登录的用户收藏
            isPostBookmarked: false,

            //加载回复的页码和页大小
            pagenum: 0,
            pagesize: 10,

            //是否显示添加新的回复的界面
            showAddNewReplyDialog: false,

            //新的回复的回复内容 和 图片列表 和 图片数量
            newReplyContent: "",
            imageList: [],
            newReplyContainImage: 0,
        }
    },
    methods:{
        //跳转到section页面
        backToSection(){
            //console.log("back to section")
            this.$router.push({
                path: '/zsbbs/mobile/forum/showallposts', 
                query: { 

                    sectionid: this.sectionid
                    
                }
            })
        },
        //获取post信息
        getPostInfo(){
            var queryPostInfoParam = new URLSearchParams
            var _this = this

            queryPostInfoParam.append("postid", this.postid)

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

                    _this.content = _this.content.replace(/\\n/gm,"\n");

                    //获取帖子所属section信息
                    _this.getSectionInfo()
                    //获取帖子作者信息
                    _this.getAuthorInfo()
                    //获取帖子图片
                    _this.getContainImage()

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        //获取section信息
        getSectionInfo(){
            var querySingleSectionParam = new URLSearchParams
            var _this = this

            querySingleSectionParam.append("sectionid", this.sectionid)

            //获取该section的信息
            axios.post('/post/query/singlesection', 
                    querySingleSectionParam
                )
                .then(function (response) {

                    _this.sectionname = response.data.sectionname
                    _this.sectiondescription = response.data.sectiondescription

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
        //获取帖子的图片数据
        getContainImage(){
            //console.log("img data222")
            if(this.containimage > 0){
                console.log("containimage " + this.containimage)
                
                var queryPostImageParam = new URLSearchParams

                queryPostImageParam.append("postid", this.postid)
                var _this = this

                axios.post('/upload/querypostimage', 
                    queryPostImageParam
                )
                .then(function (response) {
                    _this.allImage = response.data

                    console.log("img data")
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });


            }
        },
        //查看帖子是否已被收藏
        checkIfPostBookmarked(){
            //在这里判断帖子是否被当前登录的用户收藏
            //发送对帖子收藏表的查询请求
            //帖子id（postid） 当前登录用户id（this.$store.state.s_userid） 
            //先判断是否登录
            if(this.$store.state.s_userid > 0){
                var queryBookmarkParam = new URLSearchParams

                queryBookmarkParam.append("postid", this.postid)
                queryBookmarkParam.append("userid", this.$store.state.s_userid)
                var _this = this

                axios.post('/post/query/isthisbookmarked', 
                    queryBookmarkParam
                )
                .then(function (response) {
                    if(response.data == true){
                        _this.isPostBookmarked = true
                    }
                    else{
                        _this.isPostBookmarked = false
                    }

                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
        },
        //获取当前帖子的所有回复
        getAllReply(){
            this.pagenum = this.pagenum + 1

            var queryReplyParam = new URLSearchParams
            queryReplyParam.append("postid", this.postid)
            queryReplyParam.append("pagenum", this.pagenum)
            queryReplyParam.append("pagesize", this.pagesize)
            var _this = this

            axios.post('/post/getreplybypostid_mobile',
                queryReplyParam
            )
            .then(function (response) {
                
                _this.allReply = _this.allReply.concat(response.data)
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },

        //收藏或取消收藏帖子
        bookmarkPost(){
            if(this.$store.state.s_userid > 0){
                if(this.isPostBookmarked){
                    //console.log("取消收藏")
                    //发送取消收藏请求
                    var delBookmarkParam = new URLSearchParams
                    delBookmarkParam.append("bookmarkpostid", this.postid)
                    delBookmarkParam.append("bookmarkposttitle", this.title)
                    delBookmarkParam.append("bookmarkpostcontent", this.content)
                    delBookmarkParam.append("bookmarkpostauthorid", this.authorid)
                    delBookmarkParam.append("bookmarkby", this.$store.state.s_userid)
                    var _this = this


                    axios.post('/post/delbookmark',
                        delBookmarkParam
                    )
                    .then(function (response) {
                        if(response.data > 0){
                            _this.isPostBookmarked = false
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

                }
                else{
                    //console.log("加入收藏")
                    //发送添加收藏请求
                    var addBookmarkParam = new URLSearchParams
                    addBookmarkParam.append("bookmarkpostid", this.postid)
                    addBookmarkParam.append("bookmarkposttitle", this.title)
                    addBookmarkParam.append("bookmarkpostcontent", this.content)
                    addBookmarkParam.append("bookmarkpostauthorid", this.authorid)
                    addBookmarkParam.append("bookmarkby", this.$store.state.s_userid)
                    addBookmarkParam.append("bookmarkpostsectionid", this.sectionid)
                    var _this = this


                    axios.post('/post/addbookmark',
                        addBookmarkParam
                    )
                    .then(function (response) {
                        if(response.data > 0){
                            _this.isPostBookmarked = true
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

                }

                //this.isPostBookmarked = !this.isPostBookmarked
            }
            else{
                this.$message.info("请先登录")
            }
            
        },

        //给当前帖子添加新的回复 postid
        addNewReply(){
            // this.$message.info("添加新的回复")
            if(this.$store.state.s_userid > 0){
                this.showAddNewReplyDialog = true
            }
            else{
                this.$message.info("请先登录")
            }
            
        },

        //上传回复图片前
        beforeReadAvatar(file){
            const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
            if (!isJPG) {
                this.$message.error('上传图片只能是 JPG 或 png 格式!');
                return false;
            }
            return true;
        },
        //上传回复图片后
        afterReadAvatar(file){
            console.log(this.imageList.length)
            console.log(this.imageList)

            this.newReplyContainImage = this.imageList.length
        },
        //图片过大
        onOversize(){
            this.$message.error('上传图片大小不能超过 2MB!');
        },
        //取消添加新的回复
        cancelAddNewReply(){
            this.imageList = []
            this.newReplyContent = ""
            this.showAddNewReplyDialog = false
        },
        //确认添加新的回复
        confirmAddNewReply(){
            if(this.newReplyContent == ""){
                this.message.info("回复内容不能为空")
                return
            }

            //发送回复请求
            this.showAddNewReplyDialog = false

            var newReplyParam = new URLSearchParams
            newReplyParam.append("replycontent", this.newReplyContent)
            newReplyParam.append("replypostid", this.postid)
            newReplyParam.append("replyauthorid", this.$store.state.s_userid)
            newReplyParam.append("replypostauthorid", this.authorid)
            newReplyParam.append("containimage", this.newReplyContainImage)
            var _this = this


            axios.post('/post/addnewreply',
                newReplyParam
            )
            .then(function (response) {
                if(response.data > 0){
                    _this.$message.success("回复成功")

                }
                else{
                    _this.$message.error("回复失败")
                }
                
                
                _this.submitUpload(response.data)

               
            })
            .catch(function (error) {
                console.log(error);
            });

            this.newReplyContent = ""
        },
        //图片上传服务器
        submitUpload(newReplyId) {
            // 下面的代码将创建一个空的FormData对象:
            const formData = new FormData()
            // 你可以使用FormData.append来添加键/值对到表单里面；
            this.imageList.forEach((file) => {
                formData.append('file', file.file)
                
            })
            // 添加自定义参数，不传可删除
            formData.append('replyimagereplyid', newReplyId)
            

            //自定义的接口也可以用ajax或者自己封装的接口
            axios({
                method: 'POST',
                url: '/upload/replyimage',   //填写自己的接口
                data: formData,        //填写包装好的formData对象
                headers: { "Content-Type": "multipart/form-data;charset=utf-8" }
            }).then(res => {
                console.log(res)

                //清空fileList
                this.imageList = []

                this.pagenum = 0
                this.allReply = []
                this.getAllReply()
            })
        },

        //删帖请求
        deletePost(){
            //再次身份判断
            if(this.authorid == this.$store.state.s_userid){
                showConfirmDialog({
                    title: '删除',
                    message:
                        '您确定要删除此贴 ?',
                })
                .then(() => {
                    // on confirm
                    var deletePostParam = new URLSearchParams
                    deletePostParam.append("postid", this.postid)
                    var _this = this


                    axios.post('/post/deletepost',
                        deletePostParam
                    )
                    .then(function (response) {
                        if(response.data.success == true){
                            _this.$message.success("删除成功")
                        }
                        else{
                            _this.$message.error("删除失败")
                        }
                        
                        _this.$router.push({
                            path: '/zsbbs/mobile/forum/showallposts', 
                            query: { 

                                sectionid: _this.sectionid
                                
                            }
                        })
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                })
                .catch(() => {
                    // on cancel
                    this.$message.info('取消删除')
                });

            }
            else{
                this.$message.error("没有权限")
            }
        },

    }
}
</script>

<style>
.SectionTag{
    position: fixed;
    z-index: 4;

    width: 30%;
    /* height: 55px; */

    margin-left: -15vw;
    margin-top: -42px;
}
.PostDetailDiv{
    width: 100%;
    display: flex;
    flex-direction: column;
}
.AuthorAvatarandNameDiv{
    display: flex;
    flex-direction: row;

    text-align: left;
}
.PostTitle{
    font-size: 1.6em; 
    color: black;
    overflow: hidden;
}
.PostContent{
    font-size: 1.1em; 
    word-break: break-all; 
    overflow: hidden;
    white-space: pre-wrap;
}
.PostImageDiv{
    width: 100%;
    display: flex;
    flex-direction: column;
}
.SPostImage{
    width: 95%;
    margin: auto;
    margin-top: 5px;
}
.PostSubInfo{
    align-self: flex-end;
}
.PostAllReplyDiv{
    display: flex;
    flex-direction: column;

    width: 100%;
}
.AddNewReplyDialogDiv{
    /* height: 80%; */
    width: 90%;
    background-color: #fff;
    margin: auto;

    display: flex;
    flex-direction: column;
}
.AddNewReplyButtonDiv{
    display: flex;
    flex-direction: row;

    width: 90%;
    margin: auto;
}
.AddNewReplyButton{
    width: 50%;
    margin: auto;
}
.BackTopButton{
    --van-back-top-size: 50px;
    --van-back-top-icon-size: 25px;
    --van-back-top-right: 24px;
    --van-back-top-bottom: 90px;
}
</style>