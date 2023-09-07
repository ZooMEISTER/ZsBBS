<template>
    <!-- <text>sectionid: {{sectionid}}</text><br>
    <text>sectionname: {{sectionname}}</text><br> -->

    <text class="Title">{{sectionname}}</text><br>
    <text class="Description">{{sectiondescription}}</text><br>

    <div style="height: 10px;"></div>

    <!-- 这里放帖子 -->
    <div v-infinite-scroll="loadNewPostPage">
        <Comp_Mobile_POST
            v-for="(spost, index) in postlist"
            :p_index="index"
            :key="spost.postid"

            :p_posttype=0
            :p_postid="spost.postid"
        >
        </Comp_Mobile_POST>
    </div>

    <van-back-top />
</template>

<script>
import axios from 'axios'
import Comp_Mobile_POST from "./post.vue"

export default {
    created(){
        this.sectionid = this.$route.query.sectionid
        this.getSectionInfo()
        this.loadNewPostPage()
    },
    data(){
        return{
            //要显示的板块id
            sectionid: 0,
            sectionname: "",
            sectiondescription: "",

            //采用划到底加载
            pagenum: 0,
            pagesize: 5,
            postlist: [],
        }
    },
    components:{
        Comp_Mobile_POST
    },
    methods:{
        getSectionInfo(){
            var querySingleSectionParam = new URLSearchParams
            var _this = this

            querySingleSectionParam.append("sectionid", this.sectionid)

            //获取该section的信息
            axios.post('/post/query/singlesection', 
                    querySingleSectionParam
                )
                .then(function (response) {

                    _this.sectionname = response.data.sectionname
                    _this.sectiondescription = response.data.sectiondescription

                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        //刚打开页面时，加载第一个页面的帖子数据  还包括当页面划到底时，加载新的帖子
        loadNewPostPage(){
            this.pagenum = this.pagenum + 1

            var querySectionPostParam = new URLSearchParams
            var _this = this

            querySectionPostParam.append("sectionid", this.sectionid)
            querySectionPostParam.append("pagenum", this.pagenum)
            querySectionPostParam.append("pagesize", this.pagesize)

            //获取该section的信息
            axios.post('/post/getsectionpost_mobile', 
                    querySectionPostParam
                )
                .then(function (response) {

                    _this.postlist = _this.postlist.concat(response.data)

                    console.log(_this.postlist);
                    // console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

    }
}
</script>

<style>
.Title{
    font-size: 20px;
    color: black;
}
.Description{
    font-size: 15px;
    color: black;
}
</style>