package com.baidu.ee.appservice.service;


import org.springframework.stereotype.Service;

/**
 * Created by xuchen04 on 2015/3/6.
 */

@Service
public class AppService {

    public void buildReleaseApp(String puid) {

        //Step1  add project to build queue;

        //Step2  download project Code and Signture

        //Step3  exec build command
    }

    public void buildBetaApp(String puid) {
        //Step1  add project to build queue;

        //Step2  download project Code

        //Step3  exec build command
    }
}
