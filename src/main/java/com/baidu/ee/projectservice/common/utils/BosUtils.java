package com.baidu.ee.projectservice.common.utils;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.HttpMethodName;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.*;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Created by qinghai on 2015/3/10.
 */
public class BosUtils {

    public static String BUCKET_NAME = "app-plat";

    /**
     * 初始化client
     *
     * @return
     */
    public static BosClient createClient() {
        String ACCESS_KEY_ID = "591d5a5d3fc3431faf4354155cb795a7";
        String SECRET_ACCESS_KEY = "15bda9981ccb4702b7f795a8c267a02d";

        String ENDPOINT = "http://bos.bj.baidubce.com";
        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);
        return client;
    }

    /**
     * 根据key返回指定的文件
     *
     * @param bosClient
     * @param objectKey
     * @return
     */
    public static String getFileUrl(BosClient bosClient, String objectKey) {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(BUCKET_NAME, objectKey, HttpMethodName.GET);
        URL url = bosClient.generatePresignedUrl(request);
        return url.toString();
    }

    /**
     * 根据key返回指定的文件
     *
     * @param bosClient
     * @param objectKey
     * @return
     */
    public static String getFileUrl(BosClient bosClient, String objectKey, int expirationInSeconds) {
        URL url = bosClient.generatePresignedUrl(BUCKET_NAME, objectKey, expirationInSeconds, HttpMethodName.GET);
        return url.toString();
    }


    /**
     * 新建Bucket
     *
     * @param client
     * @param bucketName
     */
    public static void createBucket(BosClient client, String bucketName) {
        // 新建一个Bucket
        client.createBucket(bucketName);
    }

    /**
     * 列出全部Bucket
     *
     * @param client
     */
    public void listBuckets(BosClient client) {
        // 获取用户的Bucket列表
        List<BucketSummary> buckets = client.listBuckets().getBuckets();

        // 遍历Bucket
        for (BucketSummary bucket : buckets) {
            System.out.println(bucket.getName());
        }
    }

    /**
     * 判断Bucket是否存在
     *
     * @param client
     * @param bucketName
     */
    public static boolean doesBucketExist(BosClient client, String bucketName) {

        // 获取Bucket的存在信息
        boolean exists = client.doesBucketExist(bucketName);
        return exists;
    }

    /**
     * 删除Bucket
     *
     * @param client
     * @param bucketName
     */
    public void deleteBucket(BosClient client, String bucketName) {
        // 删除Bucket
        client.deleteBucket(bucketName);
    }


    public static String putObject(BosClient client, String objectKey, File file) {
        return putObject(client, BUCKET_NAME, objectKey, file);
    }

    /**
     * 以文件形式上传Object
     *
     * @param client
     * @param bucketName
     * @param objectKey
     * @param file
     */
    public static String putObject(BosClient client, String bucketName, String objectKey, File file) {
        PutObjectResponse putObjectFromFileResponse = null;
        try {
            // 初始化上传输入流
            ObjectMetadata meta = new ObjectMetadata();

            // 设置ContentType
            meta.setContentType("application/vnd.android.package-archive");

            // 以文件形式上传Object
            putObjectFromFileResponse = client.putObject(bucketName, objectKey, file, meta);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 打印ETag
        return putObjectFromFileResponse.getETag();
    }

    /**
     * 列粗指定bucket下的object
     *
     * @param client
     * @param bucketName
     * @return
     */
    public static ListObjectsResponse listObjects(BosClient client, String bucketName) {

        // 获取指定Bucket下的所有Object信息
        ListObjectsResponse listing = client.listObjects(bucketName);

        // 遍历所有Object
        for (BosObjectSummary objectSummary : listing.getContents()) {
            System.out.println("ObjectKey: " + objectSummary.getKey());
        }

        // 超过单次获取限制，继续获取剩余Object信息
        return client.listNextBatchOfObjects(listing);
    }
}