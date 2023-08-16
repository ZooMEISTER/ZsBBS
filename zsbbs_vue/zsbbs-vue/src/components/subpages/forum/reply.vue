<template>
    <el-card shadow="never" style="width: 70%; margin: auto; background-color:rgb(250,250,250)">
        <div style="display: flex; flex-direction: row;">
            <!-- 回复的用户的信息 -->
            <div style="display: flex; flex-direction: column; width: 10%; height: 100%">
                <el-avatar :size="80" :src="replayAuthorAvatar" style="align-self: center;"/>
                <div style="height: 10px"/>
                <el-text type="primary">{{replyAuthorName}}</el-text>
                <div style="height: 10px"/>
                <el-tag v-if="p_replypostauthorid == p_replyauthorid" style="width: 50%; margin: auto;"> 楼主 </el-tag>
                <div style="height: 5px"/>
                <el-tag v-if="this.$store.state.s_userid == p_replyauthorid" type="success" style="width: 50%; margin: auto;"> 我 </el-tag>
            </div>
            <div style="width: 20px;"/>
            <!-- 回复的具体信息和次要内容 -->
            <div style="display: flex; flex-direction: column; width: 90%;">
                <!-- 内容 -->
                    <div style="font-size: 20px; text-align:left; align-self:flex-start; word-break: break-all; overflow:hidden;white-space:normal; white-space: pre-wrap;">
                        {{replyContent}}
                    </div>
                    <div style="height: 200px"/>
                    <div style="align-self: flex-start;"> 
                        <el-image v-for="(simage, index) in allImage"
                            :key="simage.replyimageid"
                            :index="index"

                            :src="simage.replyimagepath"
                        >
                        </el-image>
                    </div>
                    <el-divider />
                    <!-- 次要信息 -->
                    <el-text style="display: flex; flex-direction: row; align-self:flex-end;">
                        <el-button v-if="p_replyauthorid == this.$store.state.s_userid" type="danger" style="font-size: 5px; height: 25px;" @click="deleteReply" plain>删除</el-button>
                        <el-button v-if="this.$store.state.s_usertype != -1" type="primary" style="font-size: 5px; height: 25px;" @click="addSubReply" plain>回复</el-button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div style="font-size: 10px; text-align:left; margin: auto;">
                            {{p_floor}}&nbsp;楼
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            回复于&nbsp;
                            {{p_replytime}}
                        </div>
                    </el-text>
            </div>
        </div>

        <Comp_showSubReply :p_subreplyreplyid="p_replyid" ref="ref_Comp_showSubReply"></Comp_showSubReply>

    </el-card>
    <div style="height: 10px"/>


    <!-- 发送楼中楼回复对话框 -->
    <el-dialog
        v-model="dialog_addNewSubReply_v"
        title="发送新的回复"
        width="30%"
        align-center>
        <span>
            <el-input v-model="newSubReplyContent" placeholder="回复内容" style="width:95%;" type="textarea" rows="10"/>
        </span>
        <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialog_addNewSubReply_v = false, newSubReplyContent = ''">取消</el-button>
            <el-button type="primary" @click="submitNewSubReply">
                发布
            </el-button>
        </span>
        </template>
    </el-dialog>
</template>

<script>
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
import Comp_showSubReply from "./showsubreply.vue"

export default {
    created(){
        this.getAuthorInfo()
        this.replyContent = this.p_replycontent.replace(/\\n/gm,"\n");

        this.getContainImage()
    },
    components:{
        Comp_showSubReply
    },
    props:{
        p_index: Number,
        p_replyid: Number,
        p_replycontent: String,
        p_replypostid: Number,
        p_replypostauthorid: Number,
        p_replyauthorid: Number,
        p_replytime: String,
        p_floor: Number,
        p_containimage: Number,
    },
    data(){
        return{
            replyAuthorName: "",
            replayAuthorAvatar: "",
            replyContent: "",

            dialog_addNewSubReply_v: false,
            newSubReplyContent: "",

            allImage: [],
        }
    },
    methods:{
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
                console.log("containimage " + this.p_containimage)
                
                var queryReplyImageParam = new URLSearchParams

                queryReplyImageParam.append("replyimagereplyid", this.p_replyid)
                var _this = this

                axios.post('/upload/queryreplyimage', 
                    queryReplyImageParam
                )
                .then(function (response) {
                    _this.allImage = response.data

                    console.log("replyimage" + _this.p_containimage)
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });


            }
        },
        deleteReply(){
            //再次身份判断
            if(this.p_replyauthorid == this.$store.state.s_userid){
                ElMessageBox.confirm(
                    '您确定要删除这个回复吗 ?',
                    '删除',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        center: false,
                    })
                    .then(() => {
                        var deleteReplyParam = new URLSearchParams
                        deleteReplyParam.append("replyid", this.p_replyid)
                        var _this = this


                        axios.post('/post/deletereply',
                            deleteReplyParam
                        )
                        .then(function (response) {
                            if(response.data.success == true){
                                _this.$message.success("删除成功")
                            }
                            else{
                                _this.$message.error("删除失败")
                            }
                            
                            // _this.$router.push({ 
                            //     path: '/zsbbs/playground', 
                                
                            // })
                            _this.$router.push({ 
                                path: '/zsbbs/forum/postdetail', 
                                query: { 
                                    postid: _this.p_replypostid,
                                    authorid: _this.p_replypostauthorid,
                                    //publishtime: ""
                                }
                            })
                            _this.$emit("updateReply")
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
        addSubReply(){
            //添加回复
            if(this.$store.state.usertype != -1){
                this.dialog_addNewSubReply_v = true
            }
            else{
                this.$message.error("请先登录")
            }

        },
        submitNewSubReply(){
            //发送添加次要回复请求
            var addNewSubReplyParam = new URLSearchParams
            addNewSubReplyParam.append("subreplycontent", this.newSubReplyContent)
            addNewSubReplyParam.append("subreplyreplyid", this.p_replyid)
            addNewSubReplyParam.append("subreplyauthorid", this.$store.state.s_userid)
            addNewSubReplyParam.append("subreplytargetauthorid", this.p_replyauthorid)
            addNewSubReplyParam.append("subreplysubreplyid", 0)
            var _this = this

            axios.post('/post/addnewsubreply',
                addNewSubReplyParam
            )
            .then(function (response) {
                if(response.data == 11000){

                    _this.$message.success("回复成功")
                    _this.dialog_addNewSubReply_v = false
                    _this.newSubReplyContent = ""
                    _this.$refs.ref_Comp_showSubReply.getSubReply()

                }
                else if(response.data == 11001){
                    _this.$message.error("回复失败")
                }
                else{
                    _this.$message.error("未知错误")
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        },
    }
}
</script>