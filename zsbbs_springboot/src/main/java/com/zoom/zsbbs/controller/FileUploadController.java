package com.zoom.zsbbs.controller;

import com.zoom.zsbbs.entity.PostImage;
import com.zoom.zsbbs.entity.ReplyImage;
import com.zoom.zsbbs.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/*
 *    Created by ZooMEISTER
 *    on 2023/6/25 23:11
 *
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @Value("${zsbbs.address}")
    String zsbbsAddress;
    @Value("${file.dataFolder}")
    String fileDataFolderPath;
    @Value("${data.postImageFolder}")
    String dataPostImageFolderPath;
    @Value("${data.replyImageFolder}")
    String dataReplyImageFolderPath;



    //生成指定长度的随机字符串
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    //上传头像接口（已弃用）
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public String uploadAvatar(){
        return "ok";
    }


    //上传帖子的图片接口
    @RequestMapping(value = "/postimage", method = RequestMethod.POST)
    public String uploadPostImage(HttpServletRequest request, @RequestParam("postid") int postid) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("file");//获取附件

        System.out.println(multipartFiles);

        //获取项目当前路径
        File file = new File("");
        String path = file.getCanonicalPath();
        System.out.println(path);


        for(int i = 0;i < multipartFiles.size();++i){
            String name = getRandomString(32) + multipartFiles.get(i).getOriginalFilename();
            saveFile(multipartFiles.get(i),  fileDataFolderPath + dataPostImageFolderPath, name);
            //saveFile(multipartFiles.get(i), path + "localhost:8090", name);

            //在这里进行数据库插入操作
            //fileUploadService.addPostImage(new PostImage(postid, path + "-data\\tomcat\\webapps\\zsbbs_image\\postimage\\" + name));
            fileUploadService.addPostImage(new PostImage(postid, zsbbsAddress + dataPostImageFolderPath + name));
        }

        System.out.println("postid " + postid);

        return "ok";
    }

    //上传回复的图片接口
    @RequestMapping(value = "/replyimage", method = RequestMethod.POST)
    public String uploadReplyImage(HttpServletRequest request, @RequestParam("replyimagereplyid") int replyimagereplyid) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("file");//获取附件

        System.out.println(multipartFiles);

        //获取项目当前路径
        File file = new File("");
        String path = file.getCanonicalPath();
        System.out.println(path);


        for(int i = 0;i < multipartFiles.size();++i){
            String name = getRandomString(32) + multipartFiles.get(i).getOriginalFilename();
            saveFile(multipartFiles.get(i),  fileDataFolderPath + dataReplyImageFolderPath, name);
            //saveFile(multipartFiles.get(i), path + "localhost:8090", name);

            //在这里进行数据库插入操作
            //fileUploadService.addPostImage(new PostImage(postid, path + "-data\\tomcat\\webapps\\zsbbs_image\\postimage\\" + name));
            fileUploadService.addReplyImage(new ReplyImage(replyimagereplyid, zsbbsAddress + dataReplyImageFolderPath + name));
        }

        System.out.println("replyimagereplyid " + replyimagereplyid);

        return "ok";
    }

    //保存文件方法
    public void saveFile(MultipartFile file, String path, String name) throws IOException {
        File dir = new File(path);
        if(!dir.exists()) dir.mkdir();
        File _file = new File(path + name);
        file.transferTo(_file);
    }

    //通过postid获取该post的所有图片信息
    @RequestMapping(value = "/querypostimage", method = RequestMethod.POST)
    public List<PostImage> queryPostImageByPostid(@RequestParam("postid") int postid){
        return fileUploadService.queryPostImageByPostid(postid);
    }

    //通过replyid获取该reply的所有图片信息
    @RequestMapping(value = "/queryreplyimage", method = RequestMethod.POST)
    public List<ReplyImage> queryReplyImageByReplyid(@RequestParam("replyimagereplyid") int replyimagereplyid){
        return fileUploadService.queryReplyImageByReplyid(replyimagereplyid);
    }
}
