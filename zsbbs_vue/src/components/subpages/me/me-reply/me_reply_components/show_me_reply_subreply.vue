<template>
    <!-- <div>
        <h1>My_subReply</h1>
    </div> -->
    <div style="width: 70%; margin: auto;">
        <Comp_me_reply_subreply
            v-for="(ssubreply, index) in allSubReply" 
                :key="ssubreply.subreplyid"
                :index="index"

                :p_subreplytype="p_subreplytype"

                :p_subreplyid="ssubreply.subreplyid"
                :p_subreplycontent="ssubreply.subreplycontent"
                :p_subreplyreplyid="ssubreply.subreplyreplyid"
                :p_subreplyauthorid="ssubreply.subreplyauthorid"
                :p_subreplytime="ssubreply.subreplytime"
                :p_subreplysubreplyid="ssubreply.subreplysubreplyid"
                :p_subreplytargetauthorid="ssubreply.subreplytargetauthorid"

        >

        </Comp_me_reply_subreply>

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
import axios from 'axios'
import Comp_me_reply_subreply from "./me_reply_subreply.vue"

export default {
    created(){
        this.getRequiredSubReply()
    },
    props:{
        p_subreplytype: Number


    },
    components:{
        Comp_me_reply_subreply
    },
    data(){
        return{
            allSubReply: [],

            currentPage: 1,
            pageSize: 10,
            totalCount: 50,
        }
    },
    methods:{
        getRequiredSubReply(){
            var querySubReplyParam = new URLSearchParams
            

            //查询的回复类型 0为我回复别人的 1为别人回复我的
            querySubReplyParam.append("subreplytype", this.p_subreplytype)
            querySubReplyParam.append("pagenum", this.currentPage)
            querySubReplyParam.append("pagesize", this.pageSize)
            querySubReplyParam.append("userid", this.$store.state.s_userid)

        

            var _this = this

            axios.post('/post/query/usersubreplybypagenum',
                querySubReplyParam
            )
            .then(function (response) {
                _this.totalCount = response.data.subReplyCount
                _this.allSubReply = response.data.subReplyList
                
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        handleCurrentChange(val){
            this.currentPage = val
            this.getRequiredSubReply()
            //console.log(this.currentPage)

            //this.getAllPosts(this.sortby, this.postDesc)

            this.toTop()
        }
    }
}
</script>

<style>

</style>