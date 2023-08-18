import { createStore } from 'vuex'

// 创建一个新的 store 实例
const store = createStore({
    state () {
    return {
        s_userid: -1,
        s_avatar: "",
        s_usertype: -1,
        s_username: "",
        s_password: "",
        s_userpostcount: 0,
        s_userreplycount: 0,
        s_description: "",
        s_token: "token",
        s_posts: [],
    }
    },
    mutations: {
        //将用户数据从当前同步到store
        syncUserDataToStore(state, payload){
            state.s_userid = payload.userid
            state.s_avatar = payload.avatar
            state.s_usertype = payload.usertype
            state.s_username = payload.username
            state.s_password = payload.password
            state.s_description = payload.description
            console.log("s_state: " + state.s_userid + " " + state.s_avatar + " " + state.s_usertype + " " + state.s_username + " " + state.s_password)
        },
        syncStoreAvatarData(state, payload){
            state.s_avatar = payload.avatar
            console.log("s_avatar: " + state.s_avatar)
        },
        syncStoreUsernameData(state, payload){
            state.s_username = payload.username
            console.log("s_username: " + state.s_username)
        },
        syncStorePasswordData(state, payload){
            state.s_password = payload.password
            console.log("s_password: " + state.s_password)
        },
        syncStoreDescriptionData(state, payload){
            state.s_description = payload.description
            console.log("s_description: " + state.s_description)
        },
        syncStoreUserPostCountData(state, payload){
            state.s_userpostcount = payload.userpostcount
            console.log("s_userpostcount: " + state.s_userpostcount)
        },
        syncStoreUserReplyCountData(state, payload){
            state.s_userreplycount = payload.userreplycount
            console.log("s_userreplycount: " + state.s_userreplycount)
        },
        //更新store中的post数据
        syncStorePostsData(state, payload){
            state.s_posts = payload.posts
            console.log("s_posts: " + state.s_posts)
        },
        //更新store中的token
        syncStoreToken(state, payload){
            state.s_token = payload.token
            console.log("s_token: " + state.s_token)
        }
    }
})

export default store