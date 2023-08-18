<template>
    <div style="width: 70%; margin: auto;">
        <Comp_me_reply_reply
            v-for="(sreply, index) in allReply"
            :key="sreply.replyid"
            :index="index"

            :p_replytype="p_replytype"

            :p_replyid="sreply.replyid"
            :p_replycontent="sreply.replycontent"
            :p_replypostid="sreply.replypostid"
            :p_replyauthorid="sreply.replyauthorid"
            :p_replytime="sreply.replytime"
            :p_floor="sreply.floor"
            :p_replypostauthorid="sreply.replypostauthorid"
        
        ></Comp_me_reply_reply>

        <div style="height: 5px;"/>

        <div style="diaplay: flex; flex-direction: row;">
            <div style="width: 300px;"/>
            <el-pagination style="margin: auto;"
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="prev, pager, next, jumper"
                :page-size="pageSize"
                :total="totalCount"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
            />
        </div>
    </div>
</template>

<script>
import Comp_me_reply_reply from "./me_reply_reply.vue"

import axios from 'axios'

export default {
    created(){
        this.getRequiredReply()
    },
    props:{
        p_replytype: Number,  //显示的回复类型 0为我回复给别人 1为别人回复给我

    },
    components:{
        Comp_me_reply_reply
    },
    data(){
        return{
            allReply: [],

            currentPage: 1,
            pageSize: 10,
            totalCount: 50,


        }
    },
    methods:{
        //强制回到顶端
        toTop() {
            document.documentElement.scrollTop = 0;
        },
        getRequiredReply(){
            var queryReplyParam = new URLSearchParams
            // if(this.p_replytype == 0){
                
            //     //查询的回复类型 0为我回复别人的 1为别人回复我的
            //     queryReplyParam.append("replytype", this.p_replytype)
            //     queryReplyParam.append("pagenum", this.currentPage)
            //     queryReplyParam.append("pagesize", this.pageSize)
            //     queryReplyParam.append("userid", this.$store.state.s_userid)

            // }
            // else if(this.p_replytype == 1){

                //查询的回复类型 0为我回复别人的 1为别人回复我的
                queryReplyParam.append("replytype", this.p_replytype)
                queryReplyParam.append("pagenum", this.currentPage)
                queryReplyParam.append("pagesize", this.pageSize)
                queryReplyParam.append("userid", this.$store.state.s_userid)

            //}

            var _this = this

            axios.post('/post/query/userreplybypagenum',
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
        handleCurrentChange(val){
            this.currentPage = val
            this.getRequiredReply()
            //console.log(this.currentPage)

            //this.getAllPosts(this.sortby, this.postDesc)

            this.toTop()
        }
    }
}
</script>

<style>

</style>