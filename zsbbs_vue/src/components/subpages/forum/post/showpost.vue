<template>
    <div v-if='p_postType != 3'>
        <Comp_SinglePost v-for="(spost, index) in allPosts" 
            :p_postType="p_postType"

            :p_index="index"
            :key="spost.postid" 

            :p_postid="spost.postid"
            :p_title="spost.title"
            :p_content="spost.content"
            :p_replycount="spost.replycount"
            :p_authorid="spost.authorid"
            :p_publishtime="spost.publishtime"
            :p_maxfloor="spost.maxfloor"
            :p_latestreplytime="spost.latestreplytime"
            :p_containimage="spost.containimage"
            :p_visitcount="spost.visitcount"
            :p_bookmarkcount="spost.bookmarkcount"
            :p_sectionid="spost.sectionid"
        />
    </div>
    <div v-else>
        <Comp_SinglePost v-for="(spost, index) in allPosts" 
            :p_postType="p_postType"

            :p_index="index"
            :key="spost.bookmarkid" 

            :p_postid="spost.bookmarkpostid"
            :p_title="spost.bookmarkposttitle"
            :p_content="spost.bookmarkpostcontent"
            :p_authorid="spost.bookmarkpostauthorid"
            :p_bookmarktime="spost.bookmarktime"
            :p_visitcount="spost.visitcount"
            :p_bookmarkcount="spost.bookmarkcount"
            :p_sectionid="spost.bookmarkpostsectionid"
        />
    </div>

    <div style="width: 100%; margin: auto;">
        <div style="width: 70%; margin: auto;">
            <el-pagination
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="prev, pager, next, jumper"
                :page-size="pageSize"
                :total="totalCount"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
            />
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import Comp_SinglePost from "./post.vue"


/*
    useparam: Boolean 是否用如下的筛选条件，false则无条件返回所有帖子
    p_postType: Number 表示显示那里的帖子，0表示显示广场上的帖子、
    p_postDesc: Boolean 表示帖子是否按降序排列
    currentPage: Number 表示当前显示的是第几页的帖子
    pageSize: Number 表示每一页显示多少帖子
*/

export default {
    created(){
        console.log("show post created")
        // this.syncData()
        this.getAllPosts(this.p_sortby, this.p_postDesc)
    },
    components:{
        Comp_SinglePost
    },
    props:{
        //0 是显示广场上的所有帖子  1 表示是用户发的帖子  2 表示是搜索出来的帖子 3 表示是用户收藏的帖子  4 表示返回指定用户所发的帖子
        p_postType: Number,
        //帖子是否按降序排列
        p_postDesc: Boolean,
        //排序依据 pt为发布时间 rt为回复时间
        p_sortby: String,
        //若是搜索贴子请求，则需要传入这个搜索索引
        p_searchby: String,

        p_targetUserId: Number,

        //要显示的板块id
        p_sectionid: Number
    },
    data(){
        return{
            allPosts: [],
            currentPage: 1,
            pageSize: 10,
            totalCount: 0,
            useparam: true,

            postDesc: Boolean,
            sortby: String,
        }
    },
    methods:{
        //强制回到顶端
        toTop() {
            document.documentElement.scrollTop = 0;
        },
        // syncData(){
        //     this.postDesc = this.p_postDesc
        //     this.sortby = this.p_sortby
        // },
        //从后端获取posts信息
        getAllPosts(sb, pd){
            if(this.p_postType == 0 || this.p_postType == 1){
                //当请求帖子的请求是从 广场 或 我的 中发出
                var _this = this
                var queryURL = "/post/queryallpost"

                var queryPostParam = new URLSearchParams

                this.sortby = sb
                this.postDesc = pd

                console.log("sortby " + sb)
                console.log("postdesc " + pd)
                
                queryPostParam.append("useparam", this.useparam)
                queryPostParam.append("posttype", this.p_postType)
                queryPostParam.append("postdesc", pd)
                queryPostParam.append("sortby", sb)
                queryPostParam.append("pagenum", this.currentPage) //查询的页码 0 为全部 > 0 为对应页数
                queryPostParam.append("pagesize", this.pageSize)
                queryPostParam.append("userid", this.$store.state.s_userid)

                if(this.p_postType == 1){ 
                    queryPostParam.append("sectionid", 0)
                }
                else{
                    queryPostParam.append("sectionid", this.p_sectionid)
                }
                

                // console.log("this.p_sectionid" + this.p_sectionid)

                
                axios.post("/post/queryallpost", queryPostParam)
                .then(function (response) {
                    _this.totalCount = response.data.count
                    _this.allPosts = response.data.postList
                    _this.$store.commit('syncStorePostsData', { posts: _this.allPosts })
                    console.log(response.data)
                    // console.log(_this.allPosts);
                    // console.log(_this.allPosts[0].publishtime);
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
            else if(this.p_postType == 2){
                //当请求帖子的请求是从搜索帖子发出
                var _this = this
                var querySearchPostParam = new URLSearchParams

                console.log("searchby " + this.p_searchby + "    this.p_sectionid " + this.p_sectionid)
                console.log("pagenum " + this.currentPage + "    pagesize " + this.pageSize)

                querySearchPostParam.append("searchby", this.p_searchby)
                querySearchPostParam.append("pagenum", this.currentPage) //查询的页码 0 为全部 > 0 为对应页数
                querySearchPostParam.append("pagesize", this.pageSize)
                querySearchPostParam.append("sectionid", this.p_sectionid)

                axios.post("/post/searchpost", querySearchPostParam)
                .then(function (response) {
                    _this.totalCount = response.data.count
                    _this.allPosts = response.data.postList
                    
                    console.log(response.data)
                })
                .catch(function (error) {
                    console.log(error);
                });

            }
            else if(this.p_postType == 3){
                //当请求帖子的请求是从我的收藏发出
                var _this = this
                var queryMyBookmarkPostParam = new URLSearchParams

                queryMyBookmarkPostParam.append("userid", this.$store.state.s_userid)
                queryMyBookmarkPostParam.append("pagenum", this.currentPage) //查询的页码 0 为全部 > 0 为对应页数
                queryMyBookmarkPostParam.append("pagesize", this.pageSize)

                axios.post("/post/query/allbookmarkedpost", queryMyBookmarkPostParam)
                .then(function (response) {
                    _this.totalCount = response.data.bookmarkCount
                    _this.allPosts = response.data.bookmarkList
                    
                    console.log(response.data)
                })
                .catch(function (error) {
                    console.log(error);
                });

            }
            else if(this.p_postType == 4){
                //当请求某个用户所发的帖子
                var _this = this
                var queryURL = "/post/queryallpost"

                var queryPostParam = new URLSearchParams

                this.sortby = sb
                this.postDesc = pd

                console.log("sortby " + sb)
                console.log("postdesc " + pd)
                
                queryPostParam.append("useparam", this.useparam)
                queryPostParam.append("posttype", 1)
                queryPostParam.append("postdesc", true)
                queryPostParam.append("sortby", "pt")
                queryPostParam.append("pagenum", this.currentPage) //查询的页码 0 为全部 > 0 为对应页数
                queryPostParam.append("pagesize", this.pageSize)
                queryPostParam.append("userid", this.p_targetUserId)
                queryPostParam.append("sectionid", 0)


                
                axios.post("/post/queryallpost", queryPostParam)
                .then(function (response) {
                    _this.totalCount = response.data.count
                    _this.allPosts = response.data.postList
                    _this.$store.commit('syncStorePostsData', { posts: _this.allPosts })
                    console.log(response.data)
                    // console.log(_this.allPosts);
                    // console.log(_this.allPosts[0].publishtime);
                })
                .catch(function (error) {
                    console.log(error);
                });
            }
            
            
        },
        handleCurrentChange(val){
            this.currentPage = val
            console.log(this.currentPage)

            this.getAllPosts(this.sortby, this.postDesc)

            this.toTop()
        }
    }
}
</script>