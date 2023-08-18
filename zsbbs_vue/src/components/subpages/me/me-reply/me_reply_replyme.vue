<template>
    <!-- <div>
        <h1>Reply_Me</h1>
    </div> -->
    <div style="height: 15px;"/>

    <Comp_show_me_reply_reply
        :p_replytype=1
    ></Comp_show_me_reply_reply>
    
</template>

<script>
import Comp_show_me_reply_reply from "./me_reply_components/show_me_reply_reply.vue"

export default {
    
    components:{
        Comp_show_me_reply_reply
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