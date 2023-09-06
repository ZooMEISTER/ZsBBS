import {createRouter, createWebHistory} from 'vue-router'

//PC 路由组件
import Page_MAIN from "../components/subpages/main_sub.vue"
import Page_Forum from "../components/subpages/forum/forum.vue"
import Page_Section from "../components/subpages/forum/section/forum_section.vue"
import Page_Forum_Sub from "../components/subpages/forum/forum_sub.vue"
import Page_AddNewPost from "../components/subpages/forum/addnewpost.vue"
import Page_PostDetail from "../components/subpages/forum/post/postdetail.vue"
import Page_PostDetail_Blank from "../components/blank_pages/postdetail_blank.vue"
import Page_News from "../components/subpages/news.vue"
import Page_News_Blank from "../components/blank_pages/news_blank.vue"
import Page_Login from "../components/subpages/me/login.vue"
import Page_Register from "../components/subpages/me/register.vue"
import Page_ME from "../components/subpages/me/me.vue"
import Page_MYProfile from "../components/subpages/me/me_profile.vue"
import Page_MYFollow from "../components/subpages/me/me_follow/me_follow.vue"
import Page_MYPost from "../components/subpages/me/me_post.vue"
import Page_MYBookmark from "../components/subpages/me/me_bookmark.vue"

import Page_MYReply from "../components/subpages/me/me-reply/me_reply.vue"
import Page_MYReply_MyReply from "../components/subpages/me/me-reply/me_reply_myreply.vue"
import Page_MYReply_MysubReply from "../components/subpages/me/me-reply/me_reply_mysubreply.vue"
import Page_MYReply_ReplyMe from "../components/subpages/me/me-reply/me_reply_replyme.vue"
import Page_MYReply_subReplyMe from "../components/subpages/me/me-reply/me_reply_subreplyme.vue"


//移动端 路由组件
import Mobile_Page_MAIN from "../components/mobile/sub_page/mobile_main_sub.vue"
import Mobile_Page_ZOOMEISTER from "../components/mobile/sub_page/mobile_main_zoomeister.vue"




const routerHistory = createWebHistory()

const router = createRouter({
    history: routerHistory,
     /*
      *   在这里和vue2一样写路由配置
     */
    routes: [
        //PC 路由
        {path:'/', redirect:() => {
                const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
                if(flag){
                    //跳转到移动端主页
                    console.log("跳转到移动端主页")
                    return "/zsbbs/mobile/main"
                }
                else{
                    //跳转到pc端主页
                    console.log("跳转到pc端主页")
                    return "/zsbbs/main"
                }
            }
        },
        {path:'/zsbbs/me/logout', redirect: '/zsbbs/main'},
        {path:'/zsbbs/main', component: Page_MAIN},
        {path:'/zsbbs/forum', 
            component: Page_Forum,
            children:[
                {path:'section', component:  Page_Section},
                {path:'forum_sub', component:  Page_Forum_Sub},
                {path:'addnewpost', component: Page_AddNewPost},
                {path:'postdetail', component: Page_PostDetail},
            ]
        },
        
        {path:'/zsbbs/postdetail_blank', component: Page_PostDetail_Blank},
        {path:'/zsbbs/news', component: Page_News},
        {path:'/zsbbs/news_blank', component: Page_News_Blank},
        {path:'/zsbbs/me/login', component: Page_Login},
        {path:'/zsbbs/me/register', component: Page_Register},
        {path:'/zsbbs/me', 
            component: Page_ME,
            children:[
                {path:'profile', component: Page_MYProfile},
                {path:'follow', component: Page_MYFollow},
                {path:'post', component: Page_MYPost},
                {path:'bookmark', component: Page_MYBookmark},
                {path:'reply', 
                    component: Page_MYReply,
                    children:[
                        {path:'myreply', name:"myreply", component: Page_MYReply_MyReply},
                        {path:'mysubreply', name:"mysubreply", component: Page_MYReply_MysubReply},
                        {path:'replyme', name:"replyme", component: Page_MYReply_ReplyMe},
                        {path:'subreplyme', name:"subreply", component: Page_MYReply_subReplyMe},
                    ]
                }
            ]
        },
        // {path:'/zsbbs/me/profile', component: Page_MYProfile},
        // {path:'/zsbbs/me/post', component: Page_MYPost},
        // {path:'/zsbbs/me/reply', component: Page_MYReply},


        
        //移动端 路由
        {path:'/zsbbs/mobile/main', component: Mobile_Page_MAIN},
        {path:'/zsbbs/mobile/zoomeister', component: Mobile_Page_ZOOMEISTER},
    ]
})
	
export default router