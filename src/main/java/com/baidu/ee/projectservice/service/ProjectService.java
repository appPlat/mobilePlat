package com.baidu.ee.projectservice.service;

import com.baidu.ee.fileservice.service.FileService;
import com.baidu.ee.projectservice.common.emun.ProjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by xuchen04 on 2015/3/4.
 */

@Service
public class ProjectService {

    @Autowired
    private FileService fileService;


    public String createProject(String projectName, ProjectType typeForCreate) {
        UUID projectUid = UUID.randomUUID();
        Boolean cpResult = fileService.copyFileFromTemplateToPub(typeForCreate, projectUid.toString());
        if(false == cpResult) {
            return null;
        }
        return projectUid.toString();
    }
}
