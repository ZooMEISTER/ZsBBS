<template>
    <!-- 这个是用于跳转的空白页 -->
    <div style="height: 50px;"></div>
    <text>ERROR</text>
</template>
<script>


export default {
    created(){
        //this.toTop()
        console.log("news_blank")
        setTimeout(() => {
		    this.$router.push('/zsbbs/news')
	    }, 1);
        
    },
    mounted(){
        
    },
    methods:{
        toTop() {
            document.documentElement.scrollTop = 0;
        },
    }

}
</script>