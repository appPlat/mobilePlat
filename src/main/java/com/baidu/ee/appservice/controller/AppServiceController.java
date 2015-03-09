package com.baidu.ee.appservice.controller;

import com.baidu.ee.appservice.common.enums.AppBuildType;
import com.baidu.ee.appservice.service.AppService;
import com.baidu.ee.core.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuchen04 on 2015/3/4.
 */

@RestController
@RequestMapping("/api/app")
public class AppServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/{puid}/{buildtype}build")
    public RestResponse buildApp(@PathVariable(value = "puid")String puid,
                                 @PathVariable(value = "buildtype")String typeName) {

        AppBuildType buildType = AppBuildType.getBuildTypeFromName(typeName);
        if(buildType.isBeta()) {
            appService.buildBetaApp(puid);
        }else{
            appService.buildReleaseApp(puid);
        }

        return null;
    }

}
