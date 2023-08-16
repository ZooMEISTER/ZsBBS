<template>
    <!-- <div>
        <h1>News</h1>
    </div> -->

    <!-- <div style="height: 100px"></div> -->
    <div style="position: relative;">
        <img class="ZWallPaper" src="../../assets/img/wallpaper/ZWallPaper-hp.png">
        
        <div class="ZNameBackground">
            <div class="ZName">
                <div class="ZAvatar">
                    <img class="ZAvatarImg" src="../../assets/img/Z/newZ-hp.png">
                </div>
                
                <div class="myNameCover_O_1">
                    O
                </div>
                <div class="myNameCover_O_2">
                    O
                </div>
                <div class="myNameCover_M">
                    M
                </div>
            </div>
        </div>
        
        <div class="ZDetailBackground">
            <div class="ZDetail">
                <div style="height: 50px"></div>

                <!-- 走马灯 -->
                <el-carousel :interval="5000" type="card" style="margin: auto; width: 90%;" arrow="never">
                    <el-carousel-item v-for="item in carouselImgwrap" :key="item" style="height: 270px; width: 480px;">
                        <img :src="item.url" style="height: 270px; width: 480px;" alt=""/>
                    </el-carousel-item>
                </el-carousel>

                <!-- 链接 -->
                <div>
                    <text class="ZDetailText">
                        Steam: 
                        <el-link class="ZLink" href="https://steamcommunity.com/id/zoomeister64/"> 
                            ZooMEISTER64 的 Steam 主页
                        </el-link>
                    </text>
                    <div></div>
                    <text class="ZDetailText">
                        Github: 
                        <el-link class="ZLink" href="https://github.com/ZooMEISTER"> 
                            ZooMEISTER 的 Github 主页
                        </el-link>
                    </text>
                    <div></div>
                    <text class="ZDetailText"> 
                        Tieba: 
                        <el-link class="ZLink" href="https://tieba.baidu.com/home/main?un=%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C%E7%9A%84%E5%B0%8F%E7%99%BD&fr=home&id=tb.1.9072bae9.uCjBNh6EhzwMszcIu-8qxg"> 
                            ZooMEISTER 的 贴吧 主页
                        </el-link>
                    </text>
                </div>

                <div style="height: 200px;"></div>

                <!-- 最近在玩 -->
                <div>
                    <text style="color: white; font-size: 30px;">ZooMEISTER : 一名玩家</text>
                    <div></div>
                    <text style="color: white; font-size: 30px;">最近在玩 :</text>
                    <div style="height: 30px;"></div>
                    <div style="">
                        <iframe style="width: 96%; height: 500px;" src="//player.bilibili.com/player.html?aid=559040773&bvid=BV17e4y1S7on&cid=859789581&page=1&high_quality=1&autoplay=0" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"></iframe>
                    </div>
                </div>

                <div style="height: 100px;"></div>

                <text style="color: white; font-size: 30px;">ZooMEISTER : 一个偶尔学习的人</text>
                <div></div>
                <text style="color: white; font-size: 30px; width: 90%;"> 部分学习实践历程 : </text>
                <div style="height: 30px;"></div>
                <!-- Github时间线 -->
                <el-timeline style="width: 100%; text-align: left;">
                    <el-timeline-item timestamp="2018/4" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">Image-Text-Format-Switch</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">图片字符画转换器</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="https://tieba.baidu.com/p/5848172662?pid=121562156153&cid=0#121562156153">
                                    <text style="font-size: 20px; color: white;">Image-Text-Format-Switch 介绍 (Tieba)</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2018/6" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">GDIP_Cube</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">VB.NET GDI+ 绘制的立方体</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="https://tieba.baidu.com/p/5848656664?pid=121568978899&cid=0#121568978899">
                                    <text style="font-size: 20px; color: white;">GDIP_Cube 介绍 (Tieba)</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2021/9" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">mePacman</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">C++ 命令行显示的贪吃蛇</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="https://tieba.baidu.com/p/7557338328?pid=141471834712&cid=0#141471834712">
                                    <text style="font-size: 20px; color: white;">mePacman 介绍 (Tieba)</text>
                                </el-link>
                            </div>
                        </div>
                        
                    </el-timeline-item>
                    <el-timeline-item timestamp="2022/10" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">UPBBS</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">面向高校玩家的游戏论坛微信小程序</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="https://www.bilibili.com/video/BV1VD4y1t78G/?spm_id_from=333.999.0.0&vd_source=681c49a24cab8a219f103a5d3d24cecb">
                                    <text style="font-size: 20px; color: white;">UPBBS 演示 (Bilibili)</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2022/12" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">mySeckill</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">银行产品秒杀管理系统</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="">
                                    <text style="font-size: 20px; color: white;">mySeckill 介绍</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2023/5" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">myCalendar</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">日历 APP</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="">
                                    <text style="font-size: 20px; color: white;">myCalendar 介绍</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                    <el-timeline-item timestamp="2023/6" placement="top">
                        <div style="display: flex; flex-direction: row;">
                            <el-card class="ZGithubTimelineCard">
                                <text class="ZGithubTimelineTitle">ZsBBS</text>
                                <div></div>
                                <text class="ZGithubTimelineDescription">个人网站</text>
                            </el-card>
                            <div style="margin: auto;">
                                <text style="font-size: 30px; color: white;"></text>
                                <el-link href="">
                                    <text style="font-size: 20px; color: white;">源码见 Github</text>
                                </el-link>
                            </div>
                        </div>
                    </el-timeline-item>
                </el-timeline>
            </div>
        </div>

        <div class="ZFill">

        </div>
    </div>

    
</template>

<script>
import { gsap } from "gsap";
import { ScrollTrigger } from "gsap/ScrollTrigger"
import { getCurrentInstance } from 'vue'

gsap.registerPlugin(ScrollTrigger)

export default {
    data(){
        return{
            carouselImgwrap:[
                { url:require("../../assets/img/carousel/github.png") },
                { url:require("../../assets/img/carousel/steam.png") },
                { url:require("../../assets/img/carousel/tieba.png") },
            ],

        }
    },
    methods:{
        toTop() {
            document.documentElement.scrollTop = 0;
        },
    },
    created(){
        
    },
    mounted(){
        gsap.from(".ZAvatar", {y: 2000, duration: 1, ease: "power4.out", delay: 0.5});
        gsap.from(".myNameCover_O_1", {y: 2000, duration: 1, ease: "power4.out", delay: 0.6});
        gsap.from(".myNameCover_O_2", {y: 2000, duration: 1, ease: "power4.out", delay: 0.7});
        gsap.from(".myNameCover_M", {y: 2000, duration: 1, ease: "power4.out", delay: 0.8});

        ScrollTrigger.create({
            trigger: ".ZName",
            start: "-=50",
            end: "+=200",
            scrub: true,
            animation: 
                gsap.timeline()
                    .to(".ZName", {y: 0, scale: 0.4, ease: "none"})
                    .from(".ZDetail", {y: 500, ease: "none"}, "<")
                    
        })

        ScrollTrigger.create({
            trigger: ".ZWallPaper",
            start: "-=50",
            end: "+=2150",
            scrub: true,
            animation: 
                gsap.timeline()
                    .to(".ZWallPaper", {y: 1800, ease: "none"})
                    
        })
    }
}

</script>

<style>
.ZWallPaper{
    width: 100%;
    left: 0px;
    position: absolute;
    z-index: 2;
}
.ZNameBackground{
    width: 100%; 
    height: 800px; 
    display: flex; 
    flex-direction: row;
    position: absolute;
    z-index: 4;
}
.ZName{
    /* width: 100%; */
    height: 800px;
    display: flex;
    flex-direction: row;
    align-items: center;
    margin: auto;
}
.ZAvatar{
    width: 550px;
    height: 100%;
    left: 0px;
    position: relative;
}
.ZAvatarImg{
    width: 100%;
}
.myNameCover_O_1{
    height: 100%;
    
    background: rgba(0, 0, 0, 0);
    font-size: 500px;
    color: rgba(255, 255, 255, 0.534);
}
.myNameCover_O_2{
    height: 100%;
    
    background: rgba(0, 0, 0, 0);
    font-size: 500px;
    color: rgba(255, 255, 255, 0.534);
}
.myNameCover_M{
    height: 100%;
    
    background: rgba(0, 0, 0, 0);
    font-size: 500px;
    color: rgba(255, 255, 255, 0.534);
}
.ZDetailBackground{
    width: 100%;
    position: absolute;
    z-index: 3;
}
.ZDetail{
    height: 2500px;
    width: 50%;
    background: rgba(83, 83, 83, 0.527);
    margin: auto;
    margin-top: 500px;
    backdrop-filter: blur(6px);
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}

.ZDetailText{
    font-size: 30px;
    color: white;
}
.ZLink{
    color: white;
    font-size: 20px;
}
.ZGithubTimelineCard{
    width: 40%;
    background: rgba(255, 255, 255, 0);
}
.ZGithubTimelineTitle{
    font-size: 25px;
    color: white;
}   
.ZGithubTimelineDescription{
    font-size: 20px;
    color: white;
}

.ZFill{
    height: 3000px;
    width: 100%;
    background: rgb(0, 0, 0);
    position: absolute;
    z-index: 1;
}
</style>