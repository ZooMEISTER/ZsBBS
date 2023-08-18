<template class="inner">
    <!-- 第一屏 -->
    <div class="Screen_1">
        <div class="ZNameBackground">
            <div class="ZName">
                <text class="ZNameLetterZ" style="">Z</text>
                <!-- <div style="width: 50px;"></div> -->
                <!-- <div style="display: flex; flex-direction: column; margin-top: 140px;"> -->
                <text class="ZNameLetter">O</text>
                <text class="ZNameLetter">O</text>
                <text class="ZNameLetter">M</text>
                <text class="ZNameLetter">E</text>
                <text class="ZNameLetter">I</text>
                <text class="ZNameLetter">S</text>
                <text class="ZNameLetter">T</text>
                <text class="ZNameLetter">E</text>
                <text class="ZNameLetter">R</text>
                <!-- </div> -->
                
            </div>
        </div>
        
    </div>


    <!-- 第二屏 -->
    <div class="Screen_2">
        <div class="ZCardBackground">
            <div class="ZCardRow">
                <div style="height: 30%; width: 100%;"></div>
                <div class="ZCardInnerRow">
                    <div style="width: 7.5%"></div>
                    <el-card class="ZCard" id="ZCard_1">
                        <div class="ZCardInner">
                            <text class="ZCardTitle">ZsBBS</text>
                            <text class="ZCardDescription">
                                ZsBBS 是我的个人网站<br>
                                是您现在正在访问的网站<br>
                                网站包括一个简易的论坛<br>
                                想使用论坛相关功能，请先右上角登录<br>
                            </text>
                            <el-link class="ZCardLink" href="https://github.com/ZooMEISTER/ZsBBS">(源码)前往 Github</el-link>
                        </div>
                    </el-card>
                    <div style="width: 5%"></div>
                    <el-card class="ZCard" id="ZCard_2">
                        <div class="ZCardInner">
                            <text class="ZCardTitle">UPBBS</text>
                            <text class="ZCardDescription">
                                UPBBS 是一个微信小程序<br>
                                是一个设计面向大学生的游戏交流平台<br>
                                平台包括游戏相关资讯<br>
                                以及一个用于交流的广场<br>
                            </text>
                            <el-link class="ZCardLink" href="https://www.bilibili.com/video/BV1VD4y1t78G/?spm_id_from=333.999.0.0&vd_source=681c49a24cab8a219f103a5d3d24cecb">(演示)前往 Bilibili</el-link>
                        </div>
                    </el-card>
                    <div style="width: 5%"></div>
                    <el-card class="ZCard" id="ZCard_3">
                        <div class="ZCardInner">
                            <text class="ZCardTitle">mePacman</text>
                            <text class="ZCardDescription">
                                mePacman 是一个贪吃蛇小游戏<br>
                                游戏使用 C++ 编写，命令行作为游戏界面<br>
                                加入了传送门，大力丸等经典要素<br>
                                使用 A* 寻路算法<br>
                            </text>
                            <el-link class="ZCardLink" href="https://tieba.baidu.com/p/7871823808?pid=144384725346&cid=0#144384725346">(介绍)前往 Tieba</el-link>
                        </div>
                    </el-card>
                    <div style="width: 7.5%"></div>
                </div>
                <div style="height: 0%; width: 100%;"></div>
            </div>
            

            <div class="ZCardRow">
                <div style="height: 10%; width: 100%;"></div>
                <div class="ZCardInnerRow">
                    <div style="width: 7.5%"></div>
                    <el-card class="ZCard" id="ZCard_4">
                        <div class="ZCardInner">
                            <text class="ZCardTitle">meSeckill</text>
                            <text class="ZCardDescription">
                                meSeckill 是一个银行产品秒杀系统<br>
                                前端使用Bootstrap + Jquery<br>
                                后端使用Springboot + Redis<br>
                            </text>
                            <el-link class="ZCardLink" href="https://github.com/ZooMEISTER/mySecKilll">(源码)前往 Github</el-link>
                        </div>
                    </el-card>
                    <div style="width: 5%"></div>
                    <el-card class="ZCard" id="ZCard_5">
                        
                    </el-card>
                    <div style="width: 5%"></div>
                    <el-card class="ZCard" id="ZCard_6">
                        
                    </el-card>
                    <div style="width: 7.5%"></div>
                </div>
                <div style="height: 20%; width: 100%;"></div>
            </div>
            

        </div>
    </div>


    <!-- 第三屏 -->
    <div class="Screen_3">
        <div class="ZDetailBackground">
            <el-card class="ZDetailCard">

            </el-card>
        </div>
    </div>

    
</template>

<script>
import { gsap } from "gsap";
import { ScrollTrigger } from "gsap/ScrollTrigger"

gsap.registerPlugin(ScrollTrigger)

export default {
    mounted(){

    },
    data(){
        return{
            

        }
    },
    methods:{
        toTop() {
            document.documentElement.scrollTop = 0;
        },
    },
    created(){
        // document.body.style.overflow = "hidden";
    },
    mounted(){
        //上屏动画
        gsap.timeline()
            .from('.ZNameLetterZ', {x: -1500, duration: 1, ease: "power4.out"})
            .from('.ZNameLetter', {y: 1000, duration: 1, ease: "poser4.out", stagger: 0.1}, "<")

        ScrollTrigger.create({
            trigger: '.Screen_1',
            start: 'top top',
            end: '+=900',
            markers: false,
            pin: false,
            scrub: true,
            animation: gsap.timeline()
                .to('.ZNameLetterZ', {y: 800, ease: "none"})
                .to('.ZNameLetter', {x: -1500, ease: "none", stagger: 0.05}, "<")
        })



        //多屏滚动 + 中屏动画
        ScrollTrigger.create({
            trigger: '.Screen_2',
            start: 'top top',
            end: '+=1000',
            markers: false,
            pin: true,
            scrub: true,
            animation: gsap.timeline()
                .from('#ZCard_1', {x: '29.75vw', y: '19.4vh', ease: "poser4.out"})
                .from('#ZCard_2', {x: 0, y: '19.4vh', ease: "poser4.out"}, "<")
                .from('#ZCard_3', {x: '-29.75vw', y: '19.4vh', ease: "poser4.out"}, "<")
                .from('#ZCard_4', {x: '29.75vw', y: '-23vh', ease: "poser4.out"}, "<")
                .from('#ZCard_5', {x: 0, y: '-23vh', ease: "poser4.out"}, "<")
                .from('#ZCard_6', {x: '-29.75vw', y: '-23vh', ease: "poser4.out"}, "<")
                // .from('#ZCard_1', {x: 612, y: 195, ease: "poser4.out"})
                // .from('#ZCard_2', {x: 0, y: 195, ease: "poser4.out"}, "<")
                // .from('#ZCard_3', {x: -612, y: 195, ease: "poser4.out"}, "<")
                // .from('#ZCard_4', {x: 612, y: -195, ease: "poser4.out"}, "<")
                // .from('#ZCard_5', {x: 0, y: -195, ease: "poser4.out"}, "<")
                // .from('#ZCard_6', {x: -612, y: -195, ease: "poser4.out"}, "<")
        })

        

        //下屏动画

    }
}

</script>

<style>
/* ::-webkit-scrollbar {
    width: 1 !important;
}
::-webkit-scrollbar {
    width: 1; 
    height: 0;
} */

/* 屏 */
.Screen_1{
    width: 100%;
    height: 100vh;
    background: salmon;
}
.Screen_2{
    width: 100%;
    height: 100vh;
    background: skyblue;
}
.Screen_3{
    width: 100%;
    height: 100vh;
    background: rgb(158, 255, 195);
    position: absolute;
}

/* 第一屏 */
.ZNameBackground{
    width: 100%;
    height: 100%;
    display: flex;
}
.ZName{
    margin: auto;
    display: flex;
    flex-direction: row;
}
.ZNameLetterZ{
    font-size: 700px; 
    color: white;
    margin-top: -30px;
}
.ZNameLetter{
    font-size: 100px;
    color: white;
    margin-top: 520px;
}

/* 第二屏 */
.ZCardBackground{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
}
.ZCardRow{
    width: 100%;
    height: 50%;
    display: flex;
    flex-direction: column;
}
.ZCardInnerRow{
    height: 100%; 
    width: 100%; 
    display: flex; 
    flex-direction: row;
}
.ZCard{
    width: 25%;
    height: 70%;
}
.ZCardInner{
    height: 100%;
    width: 100%;
    display: flex; 
    flex-direction: column;
    align-items: center;
}
.ZCardTitle{
    font-size: 30px;
    color: rgb(66, 178, 223);
    margin-top: 10px;
}
.ZCardDescription{
    font-size: 20px;
    color: black;
    margin-top: 20px;
}
.ZCardLink{
    position: absolute;
    font-size: 20px;
    color: skyblue;
    width:fit-content;
    bottom: 25px;
}
#ZCard_1{
    margin: auto;
    /* margin-left: 100px; */
    z-index: 6;
}
#ZCard_2{
    margin: auto;
    z-index: 5;
}
#ZCard_3{
    margin: auto;
    /* margin-right: 100px; */
    z-index: 4;
}
#ZCard_4{
    margin: auto;
    /* margin-left: 100px; */
    z-index: 3;
}
#ZCard_5{
    margin: auto;
    z-index: 2;
}
#ZCard_6{
    margin: auto;
    /* margin-right: 100px; */
    z-index: 1;
}

/* 第三屏 */
.ZDetailBackground{
    height: 100%;
    width: 100%;
    display: flex;
}
.ZDetailCard{
    height: 70%;
    width: 70%;
    margin: auto;
}


</style>