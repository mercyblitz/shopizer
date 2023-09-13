package com.salesmanager.shop.commons.configuration;

import com.salesmanager.shop.commons.util.CloudFilePathUtils;
import com.salesmanager.shop.commons.util.ImageFilePath;
import com.salesmanager.shop.commons.util.LocalImageFilePathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class LocationImageConfig {

    @Value("${config.cms.contentUrl}")
    private String contentUrl;

    @Value("${config.cms.method}")
    private String method;

    @Value("${config.cms.static.path}")
    private String staticPath;

    @Bean
    public ImageFilePath img() {
        if (!StringUtils.isEmpty(method) && !method.equals("default")) {
            CloudFilePathUtils cloudFilePathUtils = new CloudFilePathUtils();
            cloudFilePathUtils.setBasePath(contentUrl);
            cloudFilePathUtils.setContentUrlPath(contentUrl);
            return cloudFilePathUtils;
        } else {
            LocalImageFilePathUtils localImageFilePathUtils = new LocalImageFilePathUtils();
            localImageFilePathUtils.setBasePath(staticPath);
            localImageFilePathUtils.setContentUrlPath(contentUrl);
            return localImageFilePathUtils;
        }
    }
}
