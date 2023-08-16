<template>
    <!-- <div>
        <h1>My_subReply</h1>
    </div> -->

    <div style="height: 15px;"/>

    <Comp_show_me_reply_subreply
        :p_subreplytype=0
    >
    </Comp_show_me_reply_subreply>
    
</template>

<script>
import Comp_show_me_reply_subreply from "./me_reply_components/show_me_reply_subreply.vue"

export default {

    components:{
        Comp_show_me_reply_subreply
    },
    data(){
        return{
            
        }
    },
    methods:{

    }
}
</script>

<style>

</style>