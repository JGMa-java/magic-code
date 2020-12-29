package com.jgma.xcode.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("/oss")
public class OssBucketApiDemo {

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucket}")
    private String bucket;

    @GetMapping("/putBucket")
    public void putBucket() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "<yourAccessKeyId>";
//        String accessKeySecret = "<yourAccessKeySecret>";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建CreateBucketRequest对象。
//        CreateBucketRequest createBucketRequest = new CreateBucketRequest("<yourBucketName>");

// 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
// 此处以设置存储空间的存储类型为标准存储为例。
// createBucketRequest.setStorageClass(StorageClass.Standard);
// 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
// createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)

// 创建存储空间。
//        ossClient.createBucket(createBucketRequest);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }


// 关闭OSSClient。
        ossClient.shutdown();
    }

    @GetMapping("/getListObj")
    public void getListObj(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 列举文件。如果不设置KeyPrefix，则列举存储空间下的所有文件。如果设置KeyPrefix，则列举包含指定前缀的文件。
//        ListObjectsV2Result result = ossClient.listObjectsV2(bucket);
//        List<OSSObjectSummary> ossObjectSummaries = result.getObjectSummaries();

        // 设置最大个数。
        final int maxKeys = 200;
// 列举文件。
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(bucket).withMaxKeys(maxKeys).withPrefix("video"));
        List<OSSObjectSummary> ossObjectSummaries = objectListing.getObjectSummaries();

        for (OSSObjectSummary obj : ossObjectSummaries) {
            System.out.println("\t" + obj.getKey());
        }
        ossClient.shutdown();
    }

}
