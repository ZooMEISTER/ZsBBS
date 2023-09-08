<template>

    <el-text class="Title" type="primary">{{sectionname}}</el-text><br>
    <el-text class="Description">{{sectiondescription}}</el-text><br>

    <div style="height: 10px;"></div>

    <!-- 这里放帖子 -->
    <div v-infinite-scroll="loadNewPostPage" infinite-scroll-immediate="false" infinite-scroll-distance="200">
        <Comp_Mobile_POST
            v-for="(spost, index) in postlist"
            :p_index="index"
            :key="spost.postid"

            :p_posttype=0
            :p_postid="spost.postid"
        >
        </Comp_Mobile_POST>
    </div>

    <div style="height: 20px;"></div>
    <el-text>下面没有了 :)</el-text>

    <div style="height: 30px;"></div>

    <!-- 添加新的帖子的对话框 -->
    <van-overlay :show="showAddNewPostDialog" :lock-scroll="true">
        <div style="height: 15%;"></div>
        <div class="AddNewPostDialogDiv">
            <div style="height: 15px;"></div>
            <el-text>添加新的帖子</el-text>
            <div style="height: 15px;"></div>
            <span>
                <el-input v-model="newPostTitle" placeholder="帖子标题" style="width:95%;"/>
                <div style="height: 10px;"/>
                <el-input v-model="newPostContent" placeholder="帖子内容" style="width:95%;" type="textarea" rows="10"/>
            </span>

            <div style="height: 10px;"/>

            <van-uploader 
                v-model="imageList" 
                :before-read="beforeReadImage"
                :after-read="afterReadImage" 
                :max-count="6"
                :max-size="2 * 1024 * 1024" 
                @oversize="onOversize"
                preview-size="6rem"

                style="margin: auto;"
            />

            <!-- 发布新帖子的 取消 和 确认 -->
            <div class="AddNewPostButtonDiv">
                <van-button class="AddNewPostButton" type="warning" plain @click="cancelAddNewPost">取消</van-button>
                <div style="width: 10px;"/>
                <van-button class="AddNewPostButton" type="primary" @click="confirmAddNewPost">确认</van-button>
            </div>

            <div style="height: 15px;"></div>
        </div>
    </van-overlay>


    <!-- 添加新的帖子按钮 -->
    <van-floating-bubble class="StartAddNewPostButton" icon="chat" axis="lock" @click="addNewPost" />


    <!-- 回到顶部 -->
    <van-back-top class="BackTopButton"/>
</template>

<script>
import axios from 'axios'
import { ref } from 'vue';
import Comp_Mobile_POST from "./post.vue"

export default {
    created(){
        this.sectionid = this.$route.query.sectionid
        this.getSectionInfo()
        this.loadNewPostPage()
    },
    data(){
        return{
            //要显示的板块id
            sectionid: 0,
            sectionname: "",
            sectiondescription: "",

            //采用划到底加载
            pagenum: 0,
            pagesize: 10,
            postlist: [],

            // offset: ref({ x: 318.8, y: 780 })
            //是否显示添加新的帖子的界面
            showAddNewPostDialog: false,

            //新的帖子的标题，内容 和 图片列表 和 图片数量
            newPostTitle: "",
            newPostContent: "",
            imageList: [],
            newPostContainImage: 0,
        }
    },
    components:{
        Comp_Mobile_POST
    },
    methods:{
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

        //刚打开页面时，加载第一个页面的帖子数据  还包括当页面划到底时，加载新的帖子
        loadNewPostPage(){
            this.pagenum = this.pagenum + 1

            var querySectionPostParam = new URLSearchParams
            var _this = this

            querySectionPostParam.append("sectionid", this.sectionid)
            querySectionPostParam.append("pagenum", this.pagenum)
            querySectionPostParam.append("pagesize", this.pagesize)

            //获取该section的信息
            axios.post('/post/getsectionpost_mobile', 
                    querySectionPostParam
                )
                .then(function (response) {

                    _this.postlist = _this.postlist.concat(response.data)

                    console.log(_this.postlist);
                    // console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        //添加新帖子
        addNewPost(){
            if(this.$store.state.s_userid > 0){
                this.showAddNewPostDialog = true
            }
            else{
                this.$message.info("请先登录")
            }
        },
        //上传帖子图片前
        beforeReadImage(file){
            const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
            if (!isJPG) {
                this.$message.error('上传图片只能是 JPG 或 png 格式!');
                return false;
            }
            return true;
        },
        //上传帖子图片后
        afterReadImage(file){
            console.log(this.imageList.length)
            console.log(this.imageList)

            this.newPostContainImage = this.imageList.length
        },
        //图片过大
        onOversize(){
            this.$message.error('上传图片大小不能超过 2MB!');
        },
        //取消添加新的回复
        cancelAddNewPost(){
            this.imageList = []
            this.newPostTitle = ""
            this.newPostContent = ""
            this.showAddNewPostDialog = false
        },
        //确认添加新帖子
        confirmAddNewPost(){
            if(this.newPostTitle == "" || this.newPostContent == ""){
                this.$message.info("帖子标题和内容均不能为空")
                return
            }

            var _this = this
            //发送发帖请求
            var newPostParam = new URLSearchParams

            newPostParam.append("title", this.newPostTitle)
            newPostParam.append("content", this.newPostContent)
            newPostParam.append("authorid", this.$store.state.s_userid)
            newPostParam.append("replycount", 0)
            newPostParam.append("containimage", this.newPostContainImage)
            newPostParam.append("sectionid", this.sectionid)
            

            axios.post('/post/publishnewpost', 
                    newPostParam
                )
                .then(function (response) {
                    var newPostId = response.data
                    if(newPostId > 0){
                        _this.$message.success("发布成功")

                        //newPostId为刚插入的帖子的postid
                        _this.submitUpload(newPostId)

                    }
                    else{
                        _this.$message.success("发布失败")
                    }

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
            
            this.newPostTitle = ""
            this.newPostContent = ""
            this.showAddNewPostDialog = false
        },
        //帖子图片上传服务器
        submitUpload(newPostId) {
            // 下面的代码将创建一个空的FormData对象:
            const formData = new FormData()
            // 你可以使用FormData.append来添加键/值对到表单里面；
            this.imageList.forEach((file) => {
                formData.append('file', file.file)
                
            })
            // 添加自定义参数，不传可删除
            formData.append('postid', newPostId)
            

            //自定义的接口也可以用ajax或者自己封装的接口
            axios({
                method: 'POST',
                url: '/upload/postimage',   //填写自己的接口
                data: formData,        //填写包装好的formData对象
                headers: { "Content-Type": "multipart/form-data;charset=utf-8" }
            }).then(res => {
                console.log(res)

                //清空fileList
                this.imageList = []

                this.pagenum = 0
                this.postlist = []
                this.loadNewPostPage()
            })
        },

    }
}
</script>

<style>
.Title{
    font-size: 30px;
}
.Description{
    font-size: 15px;
    color: black;
}

.StartAddNewPostButton{
    --van-floating-bubble-size: 50px;
}
.BackTopButton{
    --van-back-top-size: 50px;
    --van-back-top-icon-size: 25px;
    --van-back-top-right: 24px;
    --van-back-top-bottom: 90px;
}

.AddNewPostDialogDiv{
    /* height: 80%; */
    width: 90%;
    background-color: #fff;
    margin: auto;

    display: flex;
    flex-direction: column;
}
.AddNewPostButtonDiv{
    display: flex;
    flex-direction: row;

    width: 90%;
    margin: auto;
}
.AddNewPostButton{
    width: 50%;
    margin: auto;
}
</style>