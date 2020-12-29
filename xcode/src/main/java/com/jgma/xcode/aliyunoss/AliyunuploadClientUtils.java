//package com.jgma.xcode.aliyunoss;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.profile.DefaultProfile;
//
///**
// * 要获取视频的播放地址，需要videoId，同时也需要后台的秘钥认证，所以得到的视频播放地址，也要走后台的流程。
// * @Author: admin
// */
//public class AliyunuploadClientUtils {
//
//    /**
//     * 初始化客户端
//     * @param accessKeyId
//     * @param accessKeySecret
//     * @return
//     */
//    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
//        //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
//        String regionId = "cn-shanghai";
//        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
//        DefaultAcsClient client = new DefaultAcsClient(profile);
//        return client;
//    }
//
//    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,String videoId) throws Exception {
//        GetPlayInfoRequest request = new GetPlayInfoRequest();
//        request.setVideoId(videoId);
//        return client.getAcsResponse(request);
//    }
//
//    public static String getUrl(String videoId){
//        DefaultAcsClient client = AliyunuploadClientUtils.initVodClient("你的秘钥", "你的秘钥");
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
//        try {
//            response = getPlayInfo(client,videoId);
//            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//            // 播放地址
//            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//            }
//            // Base信息
//            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
//            return playInfoList.get(1).getPlayURL();//会返回两个url，第一个是m3u8格式的，我们用第二个就好
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//            return null;
//        }
//    }
//
//}
