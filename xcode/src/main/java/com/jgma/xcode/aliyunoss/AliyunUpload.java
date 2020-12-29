//package com.jgma.xcode.aliyunoss;
//
//import com.aliyun.oss.model.UploadFileRequest;
//import com.aliyuncs.AcsRequest;
//import com.aliyuncs.AcsResponse;
//import com.aliyuncs.DefaultAcsClient;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 后台获取上传的凭证
// * https://blog.csdn.net/chihang6/article/details/83241142
// * @Author: admin
// */
//public class AliyunUpload {
//
//    private static String accessKeyId = "你的秘钥";
//    private static String accessKeySecret = "你的秘钥";
//
//    public static Map getAuth(String title) throws Exception{
//        Map map = new HashMap();
//        DefaultAcsClient client = AliyunuploadClientUtils.initVodClient(accessKeyId, accessKeySecret);
//
//        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
//        //将以下信息替换成前端动态传入的文件信息即可
//        //上传的文件标题
//        request.setTitle(title);
//        //上传文件的相关藐视
//        //request.setDescription("linux");
//        //上传文件名
//        request.setFileName(title+".mp4");
//
////        CreateUploadVideoResponse response = new CreateUploadVideoResponse();
//        try {
//            AcsResponse response = client.getAcsResponse(request);
//            //将下面是三个参数封装成json串返回给前端，这个json传就相当于一个凭证，该凭证默认的生命周期是3600秒
//            //VideoId ： + response.getVideoId()
//            //UploadAddress： response.getUploadAddress()
//            //UploadAuth ： + response.getUploadAuth()
////            System.out.print("VideoId = " + response.getVideoId() + "\n");
////            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
////            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
//            map.put("code","0");
//            map.put("VideoId",response.getVideoId());
//            map.put("UploadAddress",response.getUploadAddress());
//            map.put("UploadAuth",response.getUploadAuth());
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//            map.put("code","-1");
//        }
//        return map;
//    }
//
//}
