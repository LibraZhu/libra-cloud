package com.libra.cloud.file.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.libra.cloud.file.config.properties.OssProperteis;
import com.libra.cloud.file.db.FileInitializer;
import com.libra.cloud.file.provider.FileinfoServiceProvider;
import com.libra.cloud.file.service.FileinfoService;
import com.libra.cloud.file.storage.FileOperator;
import com.libra.cloud.file.storage.oss.OssFileOperator;
import com.libra.core.constants.ConfigPrefixConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件自动配置
 */
@Configuration
@ComponentScan("com.libra.cloud.file")
public class FileAutoConfiguration {
    /**
     * 阿里云配置
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午2:21
     */
    @Bean
    @ConfigurationProperties(prefix = ConfigPrefixConstants.ALIYUN_OSS)
    public OssProperteis ossProperteis() {
        return new OssProperteis();
    }

    /**
     * oss客户端sdk
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午6:10
     */
    @Bean
    public OSSClient ossClient(OssProperteis ossProperteis) {
        DefaultCredentialProvider defaultCredentialProvider =
                new DefaultCredentialProvider(ossProperteis.getAccessKeyId(), ossProperteis.getAccessKeySecret());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        return new OSSClient(ossProperteis.getEndpoint(), defaultCredentialProvider, clientConfiguration);
    }

    /**
     * 文件操作工具
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午2:21
     */
    @Bean
    public FileOperator fileOperator(OSSClient ossClient, OssProperteis ossProperteis) {
        return new OssFileOperator(ossClient, ossProperteis);
    }

    /**
     * 数据库初始化
     */
    @Bean
    public FileInitializer fileInitializer() {
        return new FileInitializer();
    }

//    /**
//     * 控制器
//     */
//    @Bean
//    public FileController fileController() {
//        return new FileController();
//    }
//
//    @Bean
//    public FileManagerController fileManagerController() {
//        return new FileManagerController();
//    }

    /**
     * 服务
     */
    @Bean
    public FileinfoService fileinfoService() {
        return new FileinfoService();
    }

    /**
     * 服务提供者
     */
    @Bean
    public FileinfoServiceProvider fileinfoServiceProvider() {
        return new FileinfoServiceProvider();
    }
}
