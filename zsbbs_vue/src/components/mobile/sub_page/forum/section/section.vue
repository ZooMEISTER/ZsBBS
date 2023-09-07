<template>
    <el-card class="SectionCard_mobile" shadow="hover" @click="seeSection">
        <text class="SectionName_mobile">{{p_sectionname}}</text><br>
        <text class="SectionDescription_mobile">{{p_sectiondscription}}</text><br>
        <text class="SectionPostCount_mobile" style="width: 100px;">
            贴子数
            :&nbsp;
            {{postCount}}
        </text><br>
        <text class="SectionCreateTime_mobile">
            创建时间
            :&nbsp;
            {{p_sectioncreatetime}}
        </text>
    </el-card>
    <div style="height: 5px;"></div>
</template>

<script>
import axios from 'axios'

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
            postCount: 0,
        }
    },
    created(){
        this.getSectionPostCount()
    },
    methods:{
        //跳转到显示该板块所有帖子的页面
        seeSection(){
            this.$router.push({
                path: '/zsbbs/mobile/forum/showallposts', 
                query: { 

                    sectionid: this.p_sectionid
                    
                }
            })
        },

        //获取该section下面的帖子数量 this.p_sectionid
        getSectionPostCount(){
            var getSectionPostCountParam = new URLSearchParams
            var _this = this

            getSectionPostCountParam.append("sectionid", this.p_sectionid)

            axios.post('/post/getsectionpostcount', getSectionPostCountParam)
            .then(function (response) {
                
                _this.postCount = response.data
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

.SectionCard_mobile{
    height: 180px;
    width: 97%;
    margin: auto;
}
.SectionName_mobile{
    font-size: 30px;
    color: rgb(22, 172, 231);

    align-items: flex-start;
}
.SectionDescription_mobile{

}
.SectionPostCount_mobile{
    position: relative;
    color: gray;

    bottom: -35px;

}
.SectionCreateTime_mobile{
    position: relative;
    color: gray;

    bottom: -35px;;
    width: 100%;
    text-align: right;
}
</style>