<template>
    <div style="height: 10px;"></div>
    <text class="Section_Page_Title">点击您想进入的板块</text><br>
    <div style="height: 10px;"></div>

    <div class="OuterSectionDiv">
        <Comp_Mobile_SingleSection
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
        </Comp_Mobile_SingleSection>

    </div>

</template>

<script>
import axios from 'axios'
import Comp_Mobile_SingleSection from "./section.vue"

export default {
    components:{
        Comp_Mobile_SingleSection
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
.Section_Page_Title{
    font-size: 2em;
    color: rgb(22, 172, 231);
}
.OuterSectionDiv{
    display: flex;
    flex-direction: column;

    width: 100%;
    height: 100%;
}
.SectionCard{
    width: 100%;
}
</style>