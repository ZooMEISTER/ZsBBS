<template>

    <div v-if="$store.state.s_userid == -1">
        <!-- 未登录 -->
        <Comp_Me_Login></Comp_Me_Login>
    </div>
    <div v-else>
        <!-- 已登录 -->
        <Comp_Me_Logged></Comp_Me_Logged>
    </div>
    
</template>

<script>
import Comp_Me_Login from "./me/me_login.vue";
import Comp_Me_Logged from "./me/me_logged";


export default {
    components:{
        Comp_Me_Login,
        Comp_Me_Logged
    }
}
</script>

<style>

</style>