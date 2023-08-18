<template>
    <!-- 这个是用于跳转的空白页 -->
    <div style="height: 50px;"></div>
    <text>ERROR</text>
</template>
<script>


export default {
    created(){
        this.postid = this.$route.query.postid
        this.authorid = this.$route.query.authorid


        //this.toTop()
        console.log("postdetail_blank")
        setTimeout(() => {
		    this.$router.push({ 
                path: '/zsbbs/forum/postdetail', 
                query: { 

                    postid: this.postid,
                    authorid: this.authorid,
                    
                }
            })
	    }, 1);
        
    },
    mounted(){
        
    },
    data(){
        return{
            postid: 0,
            authorid: 0,
        }
    },
    methods:{
        toTop() {
            document.documentElement.scrollTop = 0;
        },
    }

}
</script>