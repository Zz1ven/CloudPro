package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by MDF on 2017-11-23
 */
@Service
public class OssService {
    /*@Value("${aliyun.oss.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    String bucketName;
    @Value("${aliyun.oss.url}")
    String ossurl;
    private static Logger logger = Logger.getLogger(OssService.class);*/


    /*public ObjectListing list(){

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // Object是否存在
        ObjectListing found = ossClient.listObjects(bucketName);
        // 关闭client
        ossClient.shutdown();
        return found;
    }

    public void delete(String url) {
        try {
            String key = url.replace(ossurl, "");
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 删除Object
            boolean is_true = ossClient.doesObjectExist(bucketName, key);
            if(is_true){
                ossClient.deleteObject(bucketName, key);
            }
            // 关闭client
            ossClient.shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    //从temp文件移动到正式文件

    public String copyFileTo(String url, String sourceKey, String destinationKey) throws ErrorCodeException {
        if (url == null) {
            return null;
        }
        String key = url.replace(ossurl,"");
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //截取文件名
        String fileName = key.substring(key.lastIndexOf("/"), key.length());
        boolean is_true;
        try {
            is_true = ossClient.doesObjectExist(bucketName, key);
            if (is_true) {
                // 拷贝Object
                CopyObjectResult result = ossClient.copyObject(bucketName, sourceKey + fileName, bucketName, destinationKey + fileName);
                // 删除之前的文件
                ossClient.deleteObject(bucketName, sourceKey + fileName);
            }
            // 关闭client
            ossClient.shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ErrorCodeException(ErrorCodeException.COPE_FILE_NO);
        }
        if(!is_true) {//OSS上未找到文件
            throw new ErrorCodeException(ErrorCodeException.FILE_NULL);
        }
        return ossurl + destinationKey + fileName;
    }*/

    public void delete(String url) {
    }

    //从temp文件移动到正式文件

    public String copyFileTo(String url, String sourceKey, String destinationKey) throws ErrorCodeException {
        return "";
    }

}
