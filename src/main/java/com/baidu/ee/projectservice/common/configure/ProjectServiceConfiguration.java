package com.baidu.ee.projectservice.common.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuchen04 on 2015/3/5.
 */

@Configuration
@ConfigurationProperties(locations = "classpath:project.properties", prefix = "project")
public class ProjectServiceConfiguration {

    private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
