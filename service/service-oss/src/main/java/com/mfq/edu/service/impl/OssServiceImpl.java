package com.mfq.edu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mfq.edu.ConstantPropertiesUtils;
import com.mfq.edu.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/29 15:17
 * @description：service
 * @modified By：
 * @version: v1$
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upLoadFileAvatar(MultipartFile file) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String url = UUID.randomUUID().toString() + file.getOriginalFilename();
        String uploadUrl = "https://" + bucketName + "." + endpoint + "/" + url;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, url, new ByteArrayInputStream(file.getBytes()));
        // 关闭OSSClient。
        ossClient.shutdown();
        return  uploadUrl;
    }
}
