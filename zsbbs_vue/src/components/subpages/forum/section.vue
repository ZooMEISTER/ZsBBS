<template>
    <el-card class="SectionCard" shadow="hover" @click="seeSection">
        <div class="SectionCardInner">
            <text>{{p_sectionname}}</text>
            <text>{{p_sectiondscription}}</text>
            <text>{{p_sectioncreatetime}}</text>
        </div>
    </el-card>
</template>

<script>
export default {
    props:{
        p_sectionid: Number,
        p_sectionavatar: String,
        p_sectionname: String,
        p_sectiondscription: String,
        p_sectioncreatetime: String
    },
    data(){
        return{

        }
    },
    methods:{
        //跳转到显示该板块所有帖子的页面
        seeSection(){
            this.$router.push({
                path: '/zsbbs/forum/forum_sub', 
                query: { 

                    sectionid: this.p_sectionid
                    
                }
            })
        }
    }
    
}
</script>

<style>
.SectionCard{
    height: 100px;
    width: 100%;
}
.SectionCardInner{
    height: 100%;
    width: 100%;

    display: flex;
    flex-direction: column;
}
</style>