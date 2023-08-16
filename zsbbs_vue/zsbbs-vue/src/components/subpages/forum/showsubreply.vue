<template>
    <div v-if="allsubreply.length > 0" style="width: 100%">
        <div style="height: 10px;"/>
        <el-collapse style="backround-color: rgb(250,250,250);">
            <el-collapse-item title="查看回复" name="1" >
                

                <el-card shadow="naver" style="width: 95%; margin: auto;">
                    <div style="display: flex; flex-direction: column;"
                        v-for="(ssubreply, index) in allsubreply" 
                        :key="ssubreply.subreplyid"
                        :index="index"
                        >
                        <Comp_SubReply
                            :p_subreplyid="ssubreply.subreplyid"
                            :p_subreplycontent="ssubreply.subreplycontent"
                            :p_subreplyreplyid="ssubreply.subreplyreplyid"
                            :p_subreplyauthorid="ssubreply.subreplyauthorid"
                            :p_subreplytime="ssubreply.subreplytime"
                            :p_subreplysubreplyid="ssubreply.subreplysubreplyid"

                            @updatesubreply="getSubReply"
                        >
                        </Comp_SubReply>

                        <div style="width: 100%; height: 1px; background-color: rgb(225,225,225);"/>
                    </div>

                    <div style="height: 5px;"/>

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
                </el-card>



            </el-collapse-item>
        </el-collapse>
        <!-- <el-divider /> -->
        
    </div>

</template>

<script>
import axios from 'axios'
import Comp_SubReply from "./subreply.vue"

export default {
    created(){
        this.getSubReply()
    },
    components:{
        Comp_SubReply
    },
    props:{
        p_subreplyreplyid: Number,

    },
    data(){
        return{
            pagesize: 5,
            currentPage: 1,

            allsubreply: [],
            totalCount: 50
        }
    },
    methods:{
        getSubReply(){
            console.log("get all sub reply------------")
            var querysubReplyParam = new URLSearchParams

            querysubReplyParam.append("pagenum", this.currentPage)
            querysubReplyParam.append("pagesize", this.pagesize)
            querysubReplyParam.append("subreplyreplyid", this.p_subreplyreplyid)
            var _this = this

            //获取所有楼中楼回复
            axios.post('/post/query/subeplybypagenum', 
               querysubReplyParam
            )
            .then(function (response) {
                _this.totalCount = response.data.subReplyCount
                _this.allsubreply = response.data.subReplyList

                //console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        handleCurrentChange(val){
            this.currentPage = val
            console.log(this.currentPage)

            this.getSubReply()
        }
    }
}
</script>

<style>

</style>