package com.baidu.ee.fileservice.common.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuchen04 on 2015/3/5.
 */
@Configuration
@ConfigurationProperties(locations = "classpath:fileservice.properties", prefix = "fileservice")
public class FileServiceConfiguration {

    private static String templateFileBasePath;
    private static String pubProjectBasePath;
    private static String userProjectBasePath;

    public static String getTemplateFileBasePath() {
        return templateFileBasePath;
    }

    public void setTemplateFileBasePath(String templateFileBasePath) {
        this.templateFileBasePath = templateFileBasePath;
    }

    public static String getPubProjectBasePath() {
        return pubProjectBasePath;
    }

    public void setPubProjectBasePath(String pubProjectBasePath) {
        this.pubProjectBasePath = pubProjectBasePath;
    }

    public static String getUserProjectBasePath() {
        return userProjectBasePath;
    }

    public void setUserProjectBasePath(String userProjectBasePath) {
        this.userProjectBasePath = userProjectBasePath;
    }
}
