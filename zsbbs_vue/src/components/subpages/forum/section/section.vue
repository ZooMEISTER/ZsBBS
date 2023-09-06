<template>
    <el-card class="SectionCard" shadow="hover" @click="seeSection">
        <div class="SectionCardInner">
            <text class="SectionName">{{p_sectionname}}</text>
            <text class="SectionDescription">{{p_sectiondscription}}</text>
            <div style="width: 100%; display: flex; flex-direction: row;">
                <text class="SectionPostCount" style="width: 100px;">
                    贴子数
                    :&nbsp;
                    {{postCount}}
                </text>
                <text class="SectionCreateTime">
                    创建时间
                    :&nbsp;
                    {{p_sectioncreatetime}}
                </text>
            </div>
            
        </div>
    </el-card>
    <div style="height: 10px;"></div>
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
                path: '/zsbbs/forum/forum_sub', 
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
.SectionCard{
    height: 100px;
    width: 100%;
}
.SectionCardInner{
    height: 100%;
    width: 100%;

    display: flex;
    flex-direction: column;

    text-align: left;
}

.SectionName{
    font-size: 30px;
    color: rgb(22, 172, 231);

    align-items: flex-start;
}
.SectionDescription{

}
.SectionPostCount{
    position: relative;
    color: gray;

    /* width: 100px; */

    bottom: -35px;

    /* font-size: 15px; */
}
.SectionCreateTime{
    position: relative;
    color: gray;

    bottom: -35px;;
    width: 100%;
    text-align: right;
}
</style>