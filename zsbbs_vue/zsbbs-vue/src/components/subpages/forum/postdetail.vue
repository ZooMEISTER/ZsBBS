<template>
    <div style="height: 10px;"/>
    <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
        <el-breadcrumb class="breadscrumb_1">
            <el-breadcrumb-item :to="{ path: '/zsbbs/forum' }">
                <h2>论坛</h2>
            </el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/zsbbs/forum/postdetail' }">
                <h2>帖子详情</h2>
            </el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <!-- 帖子主题 -->
    <div style="background-color:rgb(255,255,255);">
        <el-card shadow="always" style="background-color:rgb(250,250,250); width:70%; margin:auto;">
            <div style="width:100%; height:100%; display:flex; flex-direction:row;">
                <!-- 发帖人信息 -->
                <div style="display: flex; flex-direction: column;width: 10%;">
                    <el-avatar :size="100" :src="authoravatar" style="align-self: center;"/>
                    <div style="height: 15px"/>
                    <el-link type="primary" style="width: 100%; font-size: 25px;" @click="showUserInfo">{{authorname}}</el-link>
                    <div style="height: 15px"/>
                    <el-tag style="width: 50%; align-self: center;"> 楼主 </el-tag>
                    <div style="height: 5px"/>
                    <el-tag v-if="this.$store.state.s_userid == authorid" type="success" style="width: 50%; align-self: center"> 我 </el-tag>
                </div>
                <div style="width:30px;"/>
                <!-- 帖子内容 -->
                <div style="display: flex; flex-direction: column;width:100%;">
                    <!-- 标题 -->
                    <el-text type="primary" style="font-size: 35px; text-align:left; align-self:flex-start;">{{title}}</el-text>
                    <div style="height:20px"/>
                    <!-- 内容 -->
                    <div style="font-size: 20px; text-align:left; align-self:flex-start; word-break: break-all; overflow:hidden;white-space:normal; white-space: pre-wrap;">{{content.toString()}}</div>
                    <div style="height:200px"/>
                    <!-- 这里放帖子的图片（如果有的话） -->
                    <div> 
                        <el-image v-for="(simage, index) in allImage"
                            :key="simage.postimageid"
                            :index="index"

                            :src="simage.postimagepath"
                        >
                        </el-image>
                    </div>
                    <div style="height:50px"/>
                    <!-- 次要信息 -->
                    <div style="align-self: flex-end;">
                        <el-button v-if="authorid == this.$store.state.s_userid" type="danger" @click="deletePost" plain>删除</el-button>
                        <el-button v-else-if="!this.isPostBookmarked" type="primary" @click="bookmarkPost" plain>收藏</el-button>
                        <el-button v-else type="primary" @click="bookmarkPost" plain>取消收藏</el-button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <el-text style="font-size: 10px; text-align:left; margin:left; align-self:flex-end;">
                            回复数:
                            &nbsp;
                            {{replycount}} 
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            发布于:
                            &nbsp;
                            {{publishtime}}
                        </el-text>
                    </div>
                </div>
            </div>
        </el-card>
    </div>

    <div style="height: 20px;"/>

    <!-- 帖子回复 -->
    <div>
        <Comp_SingleReply v-for="(sreply, index) in allReply"
            :p_index="index"
            :key="sreply.replyid"
            :p_replyid="sreply.replyid"
            :p_replycontent="sreply.replycontent"
            :p_replypostid="sreply.replypostid"
            :p_replypostauthorid="authorid"
            :p_replyauthorid="sreply.replyauthorid"
            :p_replytime="sreply.replytime"
            :p_floor="sreply.floor"
            :p_containimage="sreply.containimage"
            @updateReply="updateReply"
        />
    </div>

    <div style="display: flex; flx-direction: row; width: 100%;">
        <div style="width: 300px;"/>
        <el-pagination
            :small="small"
            :disabled="disabled"
            :background="background"
            layout="prev, pager, next, jumper"
            :page-size="pagesize"
            :total="totalCount"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
        />
    </div>



    <!-- 添加新的post的按钮 -->
    <el-button class="btn_addReply" type="primary" size="large" @click="addNewReply">回帖</el-button>

    <!-- 发送回复对话框 -->
    <el-dialog
        v-model="dialog_addNewReply_v"
        title="发送新的回复"
        width="30%"
        align-center>
        <span>
            <el-input v-model="newReplyContent" placeholder="回复内容" style="width:95%;" type="textarea" rows="10"/>
        </span>

        <div style="height: 10px;"/>

        <div >
            <!-- 回复图片 -->
            <el-upload
                action=""
                list-type="picture-card"
                v-model:file-list="imageList"
                :auto-upload="false"
                :multiple="true"

                :before-upload="beforeImageUpload"

                :on-preview="handlePictureCardPreview"
                :on-change="handleChange"
                :on-remove="handleRemove"

                :class="{hide:hideUpload}"
            >
                <el-button size="small" type="primary">添加图片</el-button>
            </el-upload>
        </div>

        <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialog_addNewReply_v = false, newReplyContent = ''">取消</el-button>
            <el-button type="primary" @click="submitNewReply">
                发布
            </el-button>


        </span>
        </template>
    </el-dialog>

    <!-- 展示图片框 -->
    <el-dialog v-model="dialogVisible">
        <img style="height: 450px; width: 800px; object-fit: contain;" :src="dialogImageUrl"/>
    </el-dialog>

    <!-- 回到顶部按钮 -->
    <el-backtop :right="200" :bottom="200" />

    <!-- 用户信息弹窗 -->
    <Comp_UserInfoForm v-if="showUserInfoForm" ref="uIF" :p_targetuserid="authorid"></Comp_UserInfoForm>
</template>

<script>
import axios from 'axios'
import Comp_SingleReply from "./reply.vue"
import Comp_UserInfoForm from "../../user_info_form.vue"
import { ElMessage, ElMessageBox } from 'element-plus'
import { faL } from '@fortawesome/free-solid-svg-icons'

export default {
    created(){
        //创建时获取数据(获取帖子1楼的图片也是在这里面)
        this.getRouterData()
        //this.getContainImage()
        this.toTop()
        this.getAllReply()
    },
    components:{
        Comp_SingleReply,
        Comp_UserInfoForm,
    },
    data(){
        return{
            postid: 0,
            authorname: "",
            authoravatar: "",
            title: "",
            content: "",
            authorid: 0,
            replycount: 0,
            publishtime: "",
            containimage: 0,
            allReply: [],
            allImage: [],

            //表示该帖子是否被当前登录的用户收藏
            isPostBookmarked: false,

            dialog_addNewReply_v: false,
            newReplyContent: "",

            pagesize: 20,
            currentPage: 1,
            totalCount: 50,

            //是否显示上传图片按钮 和 最大图片数量
            hideUpload: false,
            limitCount: 3,
            imageList: [],
            newReplyContainImage: 0,

            dialogVisible: false,
            dialogImageUrl: "",

            showUserInfoForm: false,
        }
    },
    methods:{
        getRouterData(){
            //从forum push过来
            this.postid = this.$route.query.postid
            // this.authorname = this.$route.query.authorname,
            // this.authoravatar = this.$route.query.authoravatar,
            // this.title = this.$route.query.title,
            // this.content = this.$route.query.content,
            // this.replycount = this.$route.query.replycount,
            this.authorid = this.$route.query.authorid
            // this.publishtime = this.$route.query.publishtime
            // this.containimage = this.$route.query.containimage

            //获取作者数据
            var queryAuthorParam = new URLSearchParams

            queryAuthorParam.append("userid", this.authorid)
            var _this = this

            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {
                _this.authorname = response.data.username
                _this.authoravatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

            //获取帖子数据
            var queryPostParam = new URLSearchParams

            queryPostParam.append("postid", this.postid)
            var _this = this

            axios.post('/post/query/post', 
                queryPostParam
            )
            .then(function (response) {
                _this.title = response.data.title,
                _this.content = response.data.content,
                _this.replycount = response.data.replycount,
                _this.publishtime = response.data.publishtime
                _this.containimage = response.data.containimage

                //获取帖子一楼的图片
                _this.getContainImage()

                _this.content = _this.content.replace(/\\n/gm,"\n");

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });


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
            




            // //把\\n换成\n
            // this.content = this.content.replace(/\\n/gm,"\n");
            // //this.$message.success(this.postid)
    
        },
        updateReply(){
            //更新用户回复
            this.postid = this.$route.query.postid
            this.authorid = this.$route.query.authorid

            //获取作者数据
            var queryAuthorParam = new URLSearchParams

            queryAuthorParam.append("userid", this.authorid)
            var _this = this

            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {
                _this.authorname = response.data.username
                _this.authoravatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

            //获取帖子数据
            var queryPostParam = new URLSearchParams

            queryPostParam.append("postid", this.postid)
            var _this = this

            axios.post('/post/query/post', 
                queryPostParam
            )
            .then(function (response) {
                _this.title = response.data.title,
                _this.content = response.data.content,
                _this.replycount = response.data.replycount,
                _this.publishtime = response.data.publishtime

                _this.content = _this.content.replace(/\\n/gm,"\n");

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

            //重新获取回复列表
            this.getAllReply()
        },
        //获取帖子的图片数据
        getContainImage(){
            console.log("img data222")
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
        //强制回到顶端
        toTop() {
            document.documentElement.scrollTop = 0;
        },
        //获取当前帖子的所有回复
        getAllReply(){
            var queryReplyParam = new URLSearchParams
            queryReplyParam.append("pagenum", this.currentPage)
            queryReplyParam.append("pagesize", this.pagesize)
            queryReplyParam.append("replypostid", this.postid)
            var _this = this

            axios.post('/post/query/replybypagenum',
                queryReplyParam
            )
            .then(function (response) {
                _this.totalCount = response.data.replyCount
                _this.allReply = response.data.replyList
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        //给当前帖子添加新的回复
        addNewReply(){
            if(this.$store.state.s_usertype != -1){
                console.log("add new reply")
                this.dialog_addNewReply_v = true
            }
            else{
                this.$message.error("请先登录")
            }
        },
        //发送新的回复请求
        submitNewReply(){
            //表单验证

            //发送回复请求
            this.dialog_addNewReply_v = false

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
        //删帖请求
        deletePost(){
            //再次身份判断
            if(this.authorid == this.$store.state.s_userid){
                ElMessageBox.confirm(
                    '您确定要删除这篇帖子吗 ?',
                    '删除',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        center: false,
                    })
                    .then(() => {
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
                            
                            _this.$router.push('/zsbbs/forum')
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    })
                    .catch(() => {

                        this.$message.info('取消删除')
                    })
            }
            else{
                this.$message.error("没有权限")
            }
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
        handleCurrentChange(val){
            this.currentPage = val
            console.log(this.currentPage)

            this.getAllReply()
        },

        handleChange(file, fileList) {
            console.log(file)

            const isJPG = (file.raw.type === 'image/jpeg') || (file.raw.type === 'image/png');
            const isLt2M = file.size / 1024 / 1024 < 4;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 或 png 格式!');
                fileList.splice(fileList.length - 1, 1)
            }
            else if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 4MB!');
                fileList.splice(fileList.length - 1, 1)
            }
            else{
                if(this.imageList.length < this.limitCount){
                    this.imageList = fileList
                    this.newReplyContainImage = this.imageList.length
                    this.hideUpload = this.newReplyContainImage >= this.limitCount
                }
                else{
                    this.$message.info("您最多只能选择 3 张图片")
                }
            }
        },
        // 删除文件之前的钩子，参数为上传的文件和文件列表，若返回 false 或者返回 Promise 且被 reject，则停止删除。function(file, fileList)
        handleRemove(file, fileList) {
            this.imageList = fileList

           this.newReplyContainImage = this.imageList.length
            this.hideUpload = this.newReplyContainImage >= this.limitCount;
        },
        //上传服务器
        submitUpload(newReplyId) {
            // 下面的代码将创建一个空的FormData对象:
            const formData = new FormData()
            // 你可以使用FormData.append来添加键/值对到表单里面；
            this.imageList.forEach((file) => {
                formData.append('file', file.raw)
                
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

                this.getAllReply()
            })
        },
        handlePictureCardPreview(file){
            this.dialogImageUrl = file.url
            this.dialogVisible = true
        },
        //弹出弹窗，展示用户基本信息
        showUserInfo(){
            console.log("show user info")
            this.showUserInfoForm = true
            this.$nextTick(() => {
                this.$refs.uIF.init();          
            });
        }

    },
    

}
</script>

<style>

.hide .el-upload--picture-card {
	display: none;
}

.btn_addReply {
  position: fixed;
  top: 120px;
  right: 100px;
  right: 200px;
  width: 75px;
  height: 75px;
}
</style>