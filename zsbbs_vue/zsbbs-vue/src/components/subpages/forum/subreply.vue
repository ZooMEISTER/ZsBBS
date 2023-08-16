<template>
    <div style="height: 5px"/>
    <div style="display: flex; flex-direction: row; width: 100%;">
        <div  style="width: 7%;">
            <el-avatar :size="50" :src="subreplyauthoravatar" style="align-self: center;"/>
            <div style="height: 5px"/>
            <el-text type="primary">
                {{subreplyauthorname}}
            </el-text>
            <div style="height: 5px"/>
            <el-tag v-if="this.$store.state.s_userid == p_subreplyauthorid" type="success" style="width: 50%; margin: auto;"> 我 </el-tag>

        </div>
        <div style="width: 20px;"/>
        <div style="display: flex; flex-direction: column; width: 90%;">
            <div style="height: 10px;"/>
            <!-- 对楼中楼回复的目标楼中楼 -->
            <el-text v-if="p_subreplysubreplyid != 0" style="align-self: flex-start; font-size: 17px; display: flex; flex-direction: row; align-items: center;">
                回复给
                &nbsp;&nbsp;
                <el-card shadow="always">
                    <div v-if="targetSubreplyAuthorName != ''">
                        <el-text type="primary" size="large">{{targetSubreplyAuthorName}}</el-text> : {{targetSubreplyContent}}
                    </div>
                    <div style="color: rgb(255,0,0);" v-else>
                        [内容已删除]
                    </div>
                </el-card>
            </el-text>
            <div style="height: 10px;"/>
            <!-- 当前楼中楼回复的内容 -->
            <el-text style="align-self: flex-start; font-size: 17px; width: 100%;">
                <el-card shadow="never" style="width: 100%; text-align: left;">
                    {{p_subreplycontent}}
                </el-card>
            </el-text>
            <div style="height: 30px;"/>
            <div style="display: flex; flex-diretion: row; align-self: flex-end;">
                <el-button v-if="p_subreplyauthorid == this.$store.state.s_userid" type="danger" style="font-size: 5px; height: 25px;" @click="deleteReply" plain>删除</el-button>
                <el-button v-if="this.$store.state.s_usertype != -1" type="primary" style="font-size: 5px; height: 25px;" @click="addSubReply" plain>回复</el-button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <el-text style="align-self: flex-end; font-size: 10px;">
                    {{p_subreplytime}}
                </el-text>
            </div>
        </div>
    </div>
    <div style="height: 5px"/>


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

export default {
    created(){
        //获取当前subreply的作者信息
        this.getSubreplyAuthorInfo()

        
        if(this.p_subreplysubreplyid != 0){
            //获取楼中楼回复的目标楼中楼的内容
            this.getTargetSubreplyInfo()
        }
        // else{
        //     this.getTargetReplyAuthorInfo()
        // }
    },
    props:{
        p_subreplyid: Number,
        p_subreplycontent: Number,
        p_subreplyreplyid: Number,
        p_subreplyauthorid: Number,
        p_subreplytime: String,
        p_subreplysubreplyid: Number
    },
    data(){
        return{
            subreplyauthorname: "",
            subreplyauthoravatar: "",

            targetReplyAuthorid: 0,

            targetSubreplyAuthorid: 0,
            targetSubreplyAuthorName: "",
            targetSubreplyAuthorAvatar: "",
            targetSubreplyContent: "",

            dialog_addNewSubReply_v: false,
            newSubReplyContent: "",
        }
    },
    methods:{
        getSubreplyAuthorInfo(){
            var queryAuthorParam = new URLSearchParams

            queryAuthorParam.append("userid", this.p_subreplyauthorid)

            var _this = this

            //查询用户信息请求
            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {

                _this.subreplyauthorname = response.data.username
                _this.subreplyauthoravatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        getTargeSubreplyAuthorInfo(){
            var queryAuthorParam = new URLSearchParams

            // if(this.targetSubreplyAuthorid == 0){
            //     queryAuthorParam.append("userid", this.p_subreplyauthorid)
            // }
            // else{
            //     queryAuthorParam.append("userid", this.targetSubreplyAuthorid)
            // }

            // if(this.p_subreplysubreplyid == 0){
            //     queryAuthorParam.append("userid", this.p_subreplyauthorid)
            // }
            // else{
            //     queryAuthorParam.append("userid", this.targetSubreplyAuthorid)
            // }
            queryAuthorParam.append("userid", this.targetSubreplyAuthorid)

            var _this = this

            //查询用户信息请求
            axios.post('/post/query/author', 
                queryAuthorParam
            )
            .then(function (response) {
                // if(_this.targetSubreplyAuthorid == 0){
                //     _this.subreplyauthorname = response.data.username
                //     _this.subreplyauthoravatar = response.data.avatar
                // }
                // else{
                //     _this.targetSubreplyAuthorName = response.data.username
                //     _this.targetSubreplyAuthorAvatar = response.data.avatar
                // }

                _this.targetSubreplyAuthorName = response.data.username
                _this.targetSubreplyAuthorAvatar = response.data.avatar

                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        getTargetSubreplyInfo(){
            var queryTargetSubreplyParam = new URLSearchParams

            queryTargetSubreplyParam.append("subreplyid", this.p_subreplysubreplyid)
            var _this = this

            //查询用户信息请求
            axios.post('/post/query/targetsubreply', 
                queryTargetSubreplyParam
            )
            .then(function (response) {
                if(response.data != null){
                    
                    _this.targetSubreplyAuthorid = response.data.subreplyauthorid
                    _this.targetSubreplyContent = response.data.subreplycontent

                    //获取目标楼中楼发布者作者信息
                    _this.getTargeSubreplyAuthorInfo()
                }
                else{
                    _this.targetSubreplyAuthorName = "",
                    _this.targetSubreplyContent = "内容已删除"
                }
                
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        // //获取飞楼中楼回复目标的作者信息
        // getTargetReplyAuthorInfo(){
        //     var queryTargetReplyParam = new URLSearchParams

        //     queryTargetReplyParam.append("replyid", this.p_subreplyreplyid)
        //     var _this = this

        //     //查询用户信息请求
        //     axios.post('/post/query/sreply', 
        //         queryTargetReplyParam
        //     )
        //     .then(function (response) {
        //         _this.targetReplyAuthorid = response.data.replyauthorid
                
                
        //         //console.log(response);
        //     })
        //     .catch(function (error) {
        //         console.log(error);
        //     });
        // },
        addSubReply(){
            //对楼中楼添加回复
            if(this.$store.state.usertype != -1){
                this.dialog_addNewSubReply_v = true
            }
            else{
                this.$message.error("Please Login First")
            }

        },
        submitNewSubReply(){
            //发送添加次要回复请求
            var addNewSubReplyParam = new URLSearchParams
            addNewSubReplyParam.append("subreplycontent", this.newSubReplyContent)
            addNewSubReplyParam.append("subreplyreplyid", this.p_subreplyreplyid)
            addNewSubReplyParam.append("subreplyauthorid", this.$store.state.s_userid)

            // if(this.p_subreplysubreplyid == 0){
            //     addNewSubReplyParam.append("subreplytargetauthorid", this.targetReplyAuthorid)
            // }
            // else{
            //     addNewSubReplyParam.append("subreplytargetauthorid", this.p_subreplyauthorid)
            // }
            addNewSubReplyParam.append("subreplytargetauthorid", this.p_subreplyauthorid)
            
            addNewSubReplyParam.append("subreplysubreplyid", this.p_subreplyid)
            var _this = this

            axios.post('/post/addnewsubreply',
                addNewSubReplyParam
            )
            .then(function (response) {
                if(response.data == 11000){

                    _this.$message.success("Reply Success")
                    _this.dialog_addNewSubReply_v = false
                    _this.newSubReplyContent = ""
                    _this.$emit("updatesubreply")

                }
                else if(response.data == 11001){
                    _this.$message.error("Reply Fail")
                }
                else{
                    _this.$message.error("Unknown ERROR")
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        deleteReply(){
            //再次身份判断
            if(this.p_subreplyauthorid == this.$store.state.s_userid){
                ElMessageBox.confirm(
                    'Are you sure to Delete this SubReply ?',
                    'Delete',
                    {
                        confirmButtonText: 'Confirm',
                        cancelButtonText: 'Cancel',
                        type: 'warning',
                        center: false,
                    })
                    .then(() => {
                        var deleteSubReplyParam = new URLSearchParams
                        deleteSubReplyParam.append("subreplyid", this.p_subreplyid)
                        var _this = this


                        axios.post('/post/deletesubreply',
                            deleteSubReplyParam
                        )
                        .then(function (response) {
                            if(response.data == 11002){
                                _this.$message.success("Delete Success")
                            }
                            else if(response.data == 11003){
                                _this.$message.error("Delete Fail")
                            }
                            else{
                                _this.$message.error("Unknow ERROR")
                            }
                            
                            
                            _this.$emit("updatesubreply")
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    })
                    .catch(() => {

                        this.$message.info('cancel delete')
                    })
            }
        }
    }
}
</script>

<style>

</style>