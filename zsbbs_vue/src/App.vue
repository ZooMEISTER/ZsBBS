<template>
    <div v-if="device_type=='pc'">
        <Main></Main>
    </div>
    <div v-else-if="device_type=='mobile'">
        <Main_Mobile></Main_Mobile>
    </div>

</template>

<script>
import axios from 'axios'

import Main from "./main.vue";
import Main_Mobile from "./components/mobile/mobile_main.vue"

export default {
    name: "App",
    created(){

        //获取设备类型
        this.isMobile()

    },
    components: {
        Main,
        Main_Mobile,
    },
    data(){
        device_type: "pc"
    },
    methods: {
        // 判断设备类型
        isMobile() {
            const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
            if (flag) {
                console.log('移动端打开')
                this.device_type = "mobile"
            } else {
                console.log('PC端打开')
                this.device_type = "pc"
            }
        }
    }
};
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 0px;
    }
</style>
