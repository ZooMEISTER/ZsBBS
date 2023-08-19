<template>
    <div style="height: 100%; width: 100%;">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    mounted(){
        console.log("push to forum_sub")
        //this.$router.push('/zsbbs/forum/forum_sub')
    }
}
</script>

<style>

</style>
