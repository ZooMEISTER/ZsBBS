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

        <div class="SectionCardOuter">
                <Comp_SingleSection
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
    margin: auto;
    width: 50%;
}
</style>