package com.libra.cloud.file.config.properties;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/3
 * @description oss相关配置
 */
@Data
public class OssProperteis {
    /**
     * oss bucket 名称
     */
    private String bucketName;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * oss sdk配置的链接地址(例如：http://oss-cn-beijing.aliyuncs.com)
     */
    private String endpoint;

    /**
     * 阿里云文件访问地址（例如：https://xxx-xxx.oss-cn-beijing.aliyuncs.com/）
     */
    private String internetFileUrl;

    /**
     * 文件默认过期时间（分钟）
     */
    private Integer expiredMinutes;
}
