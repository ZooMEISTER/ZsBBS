<template>
    <div style="height: 10px;"/>
    <div style="display: flex; flex-direction: column;">
        <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
            <!-- 面包屑 -->
            <el-breadcrumb class="breadscrumb_1">
                <el-breadcrumb-item :to="{ path: '/zsbbs/forum/section'}">
                    <h2>论坛</h2>
                </el-breadcrumb-item>
            </el-breadcrumb>

            <!-- <div style="width: 50%;"/> -->

        </div>

        <div class="SectionContent">
            <div class="SectionContentInfo">
                <text style="font-size: 30px;">
                    欢迎来到论坛 <br>
                    请在右边选择您想访问的板块 <br>
                </text>
            </div>

            <div class="SectionCardOuter">
                <div style="margin-left: 0px;">
                    <Comp_SingleSection
                        class="SectionCard"

                        v-for="(ssection, index) in allSectionList"
                        :index="index"
                        :key="ssection.sectionid"

                        :p_sectionid="ssection.sectionid"
                        :p_sectionavatar="ssection.sectionavatr"
                        :p_sectionname="ssection.sectionname"
                        :p_sectiondscription="ssection.sectiondescription"
                        :p_sectioncreatetime="ssection.sectioncreatetime"
                        >
                    </Comp_SingleSection>
                </div>
            </div>
        </div>
    </div>

    <!-- 回到顶部按钮 -->
    <el-backtop/>

</template>

<script>
import axios from 'axios'

import Comp_SingleSection from "./section.vue"

export default {
    components:{
        Comp_SingleSection
    },
    created(){
        this.getAllSection()
    },
    data(){
        return{
            allSectionList: []
        }
    },
    methods:{
        //获取目前有的所有板块
        getAllSection(){
            var queryAllSectionParam = new URLSearchParams
            var _this = this

            axios.post('/post/query/allsection', queryAllSectionParam)
            .then(function (response) {
                
                _this.allSectionList = response.data
                console.log(response.data)
                
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    }
}
</script>

<style>
.SectionCardOuter{
    /* margin: auto; */
    width: 50%;

    display: flex;
    flex-direction: column;
}
.SectionCard{
    /* width: 100%; */
    height: 150px;
}
.SectionContent{
    margin: auto;
    width: 70%;

    display: flex;
    flex-direction: row;
}
.SectionContentInfo{
    /* margin: auto; */
    width: 50%;
    height: 100%;

    margin-right: 0px;

    text-align: left;

    /* background: green; */
}
</style>