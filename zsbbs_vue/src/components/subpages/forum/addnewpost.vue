<template>
    <div style="height: 10px;"/>
    <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
        <div class="breadscrumbouterdiv_1">
            <el-breadcrumb class="breadscrumb_1">
                <el-breadcrumb-item :to="{ path: '/zsbbs/forum/section' }">
                    <h2>论坛</h2>
                </el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/zsbbs/forum/forum_sub', query: { sectionid: this.sectionid}}">
                    <h2>{{sectionname}}</h2>
                </el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/zsbbs/forum/addnewpost' }">
                    <h2>发布新贴</h2>
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
    </div>
    <el-form :model="form" label-width="120px" style="width: 75%; margin: auto">
        <el-form-item label="标题">
            <el-input v-model="title" maxlength="50"/>
        </el-form-item>
        <el-form-item label="内容">
            <el-input v-model="content" type="textarea" rows="10"/>
        </el-form-item>
        <el-form-item label="图片">
            
            <!-- 上传图片的部分 -->
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

        </el-form-item>


        <el-form-item>
            <el-button type="primary" @click="submitNewPost">发布</el-button>
            <el-button @click="clearNewPost">清空</el-button>
        </el-form-item>
    </el-form>
    
    <el-dialog v-model="dialogVisible">
        <img style="height: 450px; width: 800px; object-fit: contain;" :src="dialogImageUrl"/>
    </el-dialog>

</template>

<script>
import axios from 'axios'

export default {
    data(){
        return{
            //newPostJson:{
                title: "",
                content: "",
                authorid: this.$store.state.s_userid,
                replycount: 0,
                containimage: 0,

                //是否显示上传图片按钮 和 最大图片数量
                hideUpload: false,
                limitCount: 6,

                dialogVisible: false,
                dialogImageUrl: "",
            //},

            //图片列表
            imageList: [],

            sectionid: 0,
            sectionname: "",
        }
    },
    created(){
        this.sectionid = this.$route.query.sectionid

        var querySingleSectionParam = new URLSearchParams
        var _this = this

        querySingleSectionParam.append("sectionid", this.sectionid)

        //获取该section的信息
        axios.post('/post/query/singlesection', 
                querySingleSectionParam
            )
            .then(function (response) {

                _this.sectionname = response.data.sectionname

                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    },
    methods:{
        submitNewPost(){
            console.log('add new post_')
            //console.log(this.newPostJson)
            var _this = this
            //发送发帖请求
            var newPostParam = new URLSearchParams
            newPostParam.append("title", this.title)
            newPostParam.append("content", this.content)
            newPostParam.append("authorid", this.$store.state.s_userid)
            newPostParam.append("replycount", this.replycount)
            newPostParam.append("containimage", this.containimage)
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

                        _this.$router.push({
                            path: '/zsbbs/forum/forum_sub',
                            query: {
                                sectionid: _this.sectionid
                            }
                        })
                    }
                    else{
                        _this.$message.success("发布失败")
                    }

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        //清空
        clearNewPost(){
            console.log(this.imageList)
        },

        //图片上传相关
        // beforeImageUpload(file){
        //     console.log(file)
        //     const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
        //     const isLt2M = file.size / 1024 / 1024 < 2;

        //     if (!isJPG) {
        //         this.$message.error('上传头像图片只能是 JPG 或 png 格式!');
        //     }
        //     if (!isLt2M) {
        //         this.$message.error('上传图片大小不能超过 10MB!');
        //     }

        //     return isJPG && isLt2M;
        // },
        // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用,function(file, fileList)
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
                    this.containimage = this.imageList.length
                    this.hideUpload = this.containimage >= this.limitCount;
                }
                else{
                    this.$message.info("您最多只能选择 6 张图片")
                }
            }
        },
        // 删除文件之前的钩子，参数为上传的文件和文件列表，若返回 false 或者返回 Promise 且被 reject，则停止删除。function(file, fileList)
        handleRemove(file, fileList) {
            this.imageList = fileList

            this.containimage = this.imageList.length
            this.hideUpload = this.containimage >= this.limitCount;
        },
        //上传服务器
        submitUpload(newPostId) {
            // 下面的代码将创建一个空的FormData对象:
            const formData = new FormData()
            // 你可以使用FormData.append来添加键/值对到表单里面；
            this.imageList.forEach((file) => {
                formData.append('file', file.raw)
                
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
            })
        },
        handlePictureCardPreview(file){
            this.dialogImageUrl = file.url
            this.dialogVisible = true
        }
    }
}
</script>

<style>
.hide .el-upload--picture-card {
	display: none;
}
</style>

