package com.baidu.ee.projectservice.controller;

import com.baidu.ee.core.RestResponse;
import com.baidu.ee.projectservice.common.emun.ProjectType;
import com.baidu.ee.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by xuchen04 on 2015/3/2.
 */

@RequestMapping("/api/rest/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/create/{projectType}/{projectName}")
    public RestResponse createProject(@PathVariable("projectType")String projectType,
                                @PathVariable()String projectName) {

        ProjectType typeForCreate = ProjectType.getFromName(projectType);
        if(null == typeForCreate) {
            return new RestResponse(RestResponse.RestResponseCode.ERR,
                    "error, bad project type");
        }

        String projectUid = projectService.createProject(projectName, typeForCreate);
        if(null == projectUid) {
            return new RestResponse(RestResponse.RestResponseCode.ERR, "create project failed");
        }

        return new RestResponse(RestResponse.RestResponseCode.SUCC, projectUid);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/build/{projectName}")
    public RestResponse buildProject(@PathVariable()String projectName) {

        if(null == projectName) {
            return new RestResponse(RestResponse.RestResponseCode.ERR,
                    "error, bad project projectName");
        }

        String VRUrl = null;
        try {
            String VRName = projectService.buildProject(projectName);
            if (null == VRName) {
                return new RestResponse(RestResponse.RestResponseCode.ERR, "build project failed, VRName is Null");
            }
            //VRUrl = "<img src=\"http://qr.liantu.com/api.php?text="+VRName.substring(0,VRName.indexOf('?'))+"\"/>";
            VRUrl = "http://qr.liantu.com/api.php?text="+VRName.substring(0,VRName.indexOf('?'));
        }catch (Exception ex){
            return new RestResponse(RestResponse.RestResponseCode.ERR,
                    "error, build project failed, Exception");
        }

        if(StringUtils.isEmpty(VRUrl)){
            return new RestResponse(RestResponse.RestResponseCode.ERR, "build project failed, VRUrl is Null");
        }

        return new RestResponse(RestResponse.RestResponseCode.SUCC, VRUrl);
    }


    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/{number}")
    public Map index(@PathVariable("number") Long number) {
        HashMap<String, String> result = new HashMap<>();
        result.put("Name", "XuChen");
        result.put("Level", "The Lord");
        result.put("Number", String.valueOf(number));
        return result;
    }
}
