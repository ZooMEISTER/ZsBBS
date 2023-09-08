<template>
    <div v-if="allsubreply.length > 0" style="width: 100%">
        <div style="height: 10px;"/>

        <el-card shadow="never">

            <el-collapse class="CollapseBoard">
                <el-collapse-item class="CollapseBoardItem">
                    <template #title>
                        <text style="font-size: 20px;">
                            &nbsp;
                            查看回复
                            ({{totalCount}})
                        </text>
                    </template>
                
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

                </el-collapse-item>
            </el-collapse>
        <!-- <el-divider /> -->
        </el-card>
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
.CollapseBoard{
    width: 100%;
    margin: auto;
    background: blue;
}
.CollapseBoardItem{
    background: green;
}
</style>