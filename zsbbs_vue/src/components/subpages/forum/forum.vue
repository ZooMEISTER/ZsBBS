<template>
    <div style="height: 10px;"/>
    <div>
        <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
            <!-- 面包屑 -->
            <el-breadcrumb class="breadscrumb_1">
                <el-breadcrumb-item :to="{ path: '/zsbbs/forum' }">
                    <h2>论坛</h2>
                </el-breadcrumb-item>
            </el-breadcrumb>

            <!-- <div style="width: 50%;"/> -->

            <div style="display: flex; flex-direction: row; margin: auto;">
                <el-text type="primary">浏览排序依据:&nbsp;&nbsp;</el-text>
                <el-select v-model="name_sortby" placeholder="Select" size="large" @change="changeSelectSortby($event)" >
                    <el-option
                        v-for="item in sortby"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
                <el-text type="primary">&nbsp;&nbsp;BY:&nbsp;&nbsp;</el-text>
                <el-select v-model="name_sequence" placeholder="Select" size="large" @change="changeSelectSequence($event)">
                    <el-option
                        v-for="item in sequence"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
            </div>

            <!-- 帖子搜索 -->
            <div style="display: flex; flex-direction: row;">
                <el-input style="width: 300px; height: 70%; margin: auto;" v-model="searchStr" placeholder="搜索" />
                <div style="width: 10px;"></div>
                <el-button style="width: 70px; height: 70%; margin: auto;" @click="SearchPost">搜索</el-button>
            </div>

        </div>
        <!-- 显示帖子的组件 0 为广场上的帖子 -->
        <Comp_ShowAllPost 
            :p_postType=0
            :p_postDesc="value_sequence"
            :p_sortby="value_sortby"
            ref="ref_Comp_ShowAllPost"
        ></Comp_ShowAllPost>
        
    </div>
    
    <!-- 添加新的post的按钮 -->
    <el-button class="btn_addPost" type="primary" size="large" @click="addNewPost">发帖</el-button>

    <!-- 回到顶部按钮 -->
    <el-backtop/>



    <!-- 帖子搜索 -->
    <el-dialog
        v-model="dialog_searchPostResult_v"
        title=""
        width="70%"
        align-center>

        <text style="font-size: 30px;">"{{searchStr}}"</text>
        <text style="font-size: 30px;">&nbsp;&nbsp; 的搜索结果</text>
        
        <div style="height: 20px;"></div>

        <Comp_ShowAllPost 
            :p_postType=2
            :p_searchby="searchStr"
            ref="ref_Comp_ShowAllPost"
        ></Comp_ShowAllPost>

    </el-dialog>
</template>

<script>
import axios from 'axios'
import Comp_ShowAllPost from "./showpost.vue"

export default {
    components:{
        Comp_ShowAllPost
    },
    data(){
        return{
            sortby:[
                {
                    value: 'rt',
                    label: '回复时间',
                },
                {
                    value: 'pt',
                    label: '发布时间',
                },
            ],
            sequence:[
                {
                    value: true,
                    label: '最新',
                },
                {
                    value: false,
                    label: '最旧',
                },
            ],
            name_sortby: "回复时间",
            name_sequence: "最新",
            value_sortby: "rt",
            value_sequence: true,

            searchStr: "",
            dialog_searchPostResult_v: false,
        }
    },
    created(){
        
    },
    mounted(){

    },
    methods:{
        //添加新的post
        addNewPost(){
            console.log("add new post")
            //先判断用户有没有登录
            if(this.$store.state.s_userid == -1){
                this.$message.error('Please Login first')
                return
            }
            this.$router.push('/zsbbs/forum/addnewpost')
        },
        changeSelectSortby(e){
            //console.log("e --- " + e)
            this.value_sortby = e
            //console.log("e.value --- " + e.value)
            this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)

        },
        changeSelectSequence(e){
            this.value_sequence = e
            //console.log("e.value --- " + e.value)
            this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)
        },
        //搜索帖子 搜索依据为searchStr
        SearchPost(){
            if(this.searchStr == ""){
                this.$message.info('搜索内容不能为空')
            }
            else{
                //将搜索内容发送给后端
                //后端查询后返回相关的帖子列表
                this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)

                //前端显示
                //显示搜索结果对话框
                this.dialog_searchPostResult_v = true

            }
        }
        
    }
}
</script>


<style>
.btn_addPost {
  width: 75px;
  height: 75px;

  position: fixed;
  top: 125px;
  right: 10vw;
}
</style>