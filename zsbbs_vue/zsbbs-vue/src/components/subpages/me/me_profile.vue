<template>
    <div style="height: 100px;"/>
    <el-card style="width: 60%; margin: auto;">
        <template #header>
            <div>
                <h1>个人资料</h1>
            </div>
        </template>
        <div>
            <div style="height:50px;"/>
            <el-form :model="form" label-width="120px" style="width:650px;margin:auto">
                <el-form-item label="头像 ">
                    <el-upload
                        class="avatar-uploader"
                        action=""
                        :auto-upload="false"
                        :show-file-list="false"

                        :on-change="handleChange"
                        >
                        <img :src="avatar" class="avatar" />
                    </el-upload>
                    <div style="width: 30px;"/>
                    <img :src="avatar" style="height:100px;width:100px;" />
                    <div style="width: 30px;"/>
                    <img :src="avatar" style="height:50px;width:50px;" />
                    <div style="width: 30px;"/>
                    <el-text v-if="userChangeAvatar">&nbsp;&nbsp;(已更改)</el-text>
                </el-form-item>
                <el-form-item label="用户名 ">

                    <el-text type="primary" size="large" style="width:300px;text-align:left;">{{username}}</el-text>
                    <div style="width: 50px;"/>
                    <el-button type="primary" @click="dialog_changeUsername_v = true">更改</el-button>
                    <el-text v-if="userChangeUsername">&nbsp;&nbsp;(已更改)</el-text>

                </el-form-item>
                <el-form-item label="密码 ">

                    <el-text type="primary" size="large" style="width:150px;text-align:left;">************</el-text>
                    <div style="width: 200px;"/>
                    <el-button type="primary" @click="dialog_changePassword_v = true">更改</el-button>
                    <el-text v-if="userChangePassword">&nbsp;&nbsp;(已更改)</el-text>

                </el-form-item>
                <el-form-item>
                    
                    <el-button type="primary" @click="submitProfileChange" style="width:100px;">保存</el-button>

                </el-form-item>
            </el-form>
            <div style="height:50px;"/>
        </div>
    
    </el-card>
    

    <!-- 更改用户名对话框 -->
    <el-dialog
        v-model="dialog_changeUsername_v"
        title="更改用户名"
        width="30%"
        align-center>
        <span>
            <el-input v-model="tmp_username" placeholder="新用户名" style="width:80%;"/>
        </span>
        <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialog_changeUsername_v = false, tmp_username = ''">取消</el-button>
            <el-button type="primary" @click="changeUsername">
                确认
            </el-button>
        </span>
        </template>
    </el-dialog>

    <!-- 更改密码对话框 -->
    <el-dialog
        v-model="dialog_changePassword_v"
        title="更改密码"
        width="30%"
        align-center>
        <span>
            <el-input v-model="tmp_password" placeholder="新的密码" style="width:80%;"/>
            <div style="height: 10px;"></div>
            <el-input v-model="tmp_password_2" placeholder="确认密码" style="width:80%;"/>
        </span>
        <template #footer>
        <span class="dialog-footer">
            <el-button @click=" dialog_changePassword_v = false, tmp_password = '', tmp_password_2 = ''">取消</el-button>
            <el-button type="primary" @click="changePassword">
                确认
            </el-button>
        </span>
        </template>
    </el-dialog>

</template>

<script>
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
    data(){
        return{
            userChangeAvatar: false,
            userChangeUsername: false,
            userChangePassword: false,

            dialog_changeUsername_v: false,
            dialog_changePassword_v: false,

            avatar: this.$store.state.s_avatar,
            username: this.$store.state.s_username,
            tmp_username: "",
            password: "",
            tmp_password: "",
            tmp_password_2: "",
        }
    },
    methods:{
        handleChange(file){
            console.log("ttttt")
            console.log(file)

            //头像 格式 大小 检查
            const isJPG = (file.raw.type === 'image/jpeg') || (file.raw.type === 'image/png');
            const isLt400k = file.raw.size / 1024 <= 400;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 或 png 格式!');
                return;
            }
            if (!isLt400k) {
                this.$message.error('上传头像图片大小不能超过 400KB!');
                return;
            }
            
            //将头像转为base64 并保存到avatar变量中
            //this.avatarfile = file
            var _this = this

            var reader = new FileReader();
            var blobFile = new Blob([file.raw])
            reader.readAsDataURL(blobFile)
            reader.onload = function () {
                console.log(this.result)
                _this.avatar = this.result
                _this.userChangeAvatar = true
            }

        },
        containSpace(str){
            if(str.indexOf(" ") == -1) return false
            else return true
        },
        //更改用户名
        changeUsername(){
            var zg_u =  /^[0-9a-zA-Z]*$/;
            if(this.tmp_username == ""){
                this.$message.error("用户名不能为空")
            }
            else if(this.containSpace(this.tmp_username)){
                this.$message.error("用户名不能包含空格")
            }
            else if(this.tmp_username.length < 4){
                this.$message.error("用户名至少需要4个字符")
            }
            else if(!zg_u.test(this.tmp_username)){
                this.$message.error("用户名智能包含字母或数字")
            }
            else{
                this.dialog_changeUsername_v = false
                this.userChangeUsername = true
                this.username = this.tmp_username
                this.tmp_username = ""
            }
        },
        //更改密码
        changePassword(){
            if(this.tmp_password == this.tmp_password_2){
                var zg_p = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]*$/;
                if(this.tmp_password == ""){
                    this.$message.error("密码不能为空")
                }
                else if(this.containSpace(this.tmp_password)){
                    this.$message.error("密码不能包含空格")
                }
                else if(this.tmp_password.length < 6){
                    this.$message.error("密码至少需要6位")
                }
                else if(!zg_p.test(this.tmp_password)){
                    this.$message.error("密码仅能且必须包含字母和数字")
                }
                else{
                    this.dialog_changePassword_v = false
                    this.userChangePassword = true
                    this.password = this.tmp_password
                    this.tmp_password = ""
                    this.tmp_password_2 = ""
                }
            }
            else{
                this.$message.error("两次输入密码不同")
            }
        },
        //提交个人信息更改
        //发送请求和更改vuex中的数据
        submitProfileChange(){
            if(this.userChangeAvatar || this.userChangeUsername || this.userChangePassword){
                ElMessageBox.confirm(
                    '您确定要更改个人资料吗 ?',
                    '更改个人资料',
                    {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        type: 'warning',
                        center: false,
                    })
                    .then(() => {
                        //用户更改了头像
                        if(this.userChangeAvatar){
                            console.log("updating avatar")
                            var changeUserProfileParam = new URLSearchParams

                            changeUserProfileParam.append("userid", this.$store.state.s_userid)
                            changeUserProfileParam.append("avatar", this.avatar)
                            var _this = this

                            //更改用户头像请求
                            axios.post('/user/update/avatar', 
                                changeUserProfileParam
                            )
                            .then(function (response) {
                                
                                _this.$store.commit('syncStoreAvatarData',{
                                    avatar: _this.avatar,
                                })
                                
                                console.log(response);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                        }
                        //用户更改了用户名
                        if(this.userChangeUsername){
                            console.log("updating username")
                            var changeUserProfileParam = new URLSearchParams

                            changeUserProfileParam.append("userid", this.$store.state.s_userid)
                            changeUserProfileParam.append("username", this.username)
                            var _this = this

                            //更改用户头像请求
                            axios.post('/user/update/username', 
                                changeUserProfileParam
                            )
                            .then(function (response) {
                                if(response.data == 10000){
                                    _this.$store.commit('syncStoreUsernameData',{
                                        username: _this.username,
                                    })
                                }
                                else if(response.data == 10004){
                                    _this.$message.error("This Username is occupied")
                                    _this.username = _this.$store.state.s_username
                                }
                                
                                console.log(response);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                        }
                        //用户更改了密码
                        if(this.userChangePassword){
                            console.log("updating password")
                            var changeUserProfileParam = new URLSearchParams

                            changeUserProfileParam.append("userid", this.$store.state.s_userid)
                            changeUserProfileParam.append("password", this.password)
                            var _this = this

                            //更改用户头像请求
                            axios.post('/user/update/password', 
                                changeUserProfileParam
                            )
                            .then(function (response) {
                                _this.$store.commit('syncStorePasswordData',{
                                    password: _this.password,
                                })
                                console.log(response);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                        }

                    this.userChangeAvatar = false
                    this.userChangeUsername = false
                    this.userChangePassword = false
                    
                    this.$message.success('个人资料更改成功')
                    })
                    .catch(() => {
                        this.$message.info('个人资料更改失败')
                    })
            }
        }
    }
}
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>