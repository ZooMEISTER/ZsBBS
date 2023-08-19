<template>
    <!-- <text>bookmark</text> -->
    <div style="height: 5px;"/>

    <div style="display: flex; flex-direction: row; width: 70%; margin: auto;">
        <div>
            <h2>我的收藏</h2>
        </div>
    </div>

    <div>
        <Comp_ShowAllPost 
            :p_postType=3
            
            ref="ref_Comp_ShowAllPost"
        ></Comp_ShowAllPost>
    </div>
    
</template>

<script>
import Comp_ShowAllPost from "../forum/post/showpost.vue"

export default {
    components:{
        Comp_ShowAllPost
    },
    created(){
        //this.$refs.ref_Comp_ShowAllPost.getAllPosts(this.value_sortby, this.value_sequence)
    },
    data(){
        return{
            value_sortby: "rt",
            value_sequence: true,
        }
    },
    methods:{

    }
}
</script>

<style>

</style>