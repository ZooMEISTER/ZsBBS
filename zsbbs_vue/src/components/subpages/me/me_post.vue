<template>

    <div style="height: 5px;"/>

    <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
        <div>
            <h2>我的帖子</h2>
        </div>

        <!-- <div style="width: 45%;"/> -->

        <div style="display: flex; flex-direction: row; margin: auto;">
            <el-text type="primary">排序依据:&nbsp;&nbsp;</el-text>
            <el-select v-model="name_sortby" placeholder="Select" size="large" @change="changeSelectSortby($event)">
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

    </div>

    <!-- 显示帖子的组件 1 为用户发的帖子 -->
    <Comp_ShowAllPost 
        :p_postType=1
        :p_postDesc="value_sequence"
        :p_sortby="value_sortby"
        ref="ref_Comp_ShowAllPost"
    ></Comp_ShowAllPost>
</template>

<script>
import Comp_ShowAllPost from "../forum/post/showpost.vue"

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
            name_sortby: "发布时间",
            name_sequence: "最新",
            value_sortby: "pt",
            value_sequence: true,
        }
    },
    methods:{
        changeSelectSortby(e){
            this.value_sortby = e
            this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)

        },
        changeSelectSequence(e){
            this.value_sequence = e
            this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)
        }
    }
}
</script>

<style>

</style>