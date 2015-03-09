package com.baidu.ee.fileservice.controller;

import com.baidu.ee.core.RestResponse;
import com.baidu.ee.fileservice.common.bean.FileBean;
import com.baidu.ee.fileservice.service.FileService;
import com.baidu.ee.projectservice.common.configure.ProjectServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Created by xuchen04 on 2015/3/4.
 */

@RestController
@RequestMapping("/api/rest/file/source")
public class FileServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/{puid}/update", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse updateFileContent(@PathVariable("puid")String puid, @RequestBody Map<String, String> newContent) {

        if(!newContent.containsKey("filePath") || !newContent.containsKey("fileContent")) {
            return new RestResponse<>(RestResponse.RestResponseCode.ERR, "bad Request params");
        }

        Boolean updateResult = fileService.updateFileInPubProject(puid, newContent.get("filePath"), newContent.get("fileContent"));
        if(!updateResult) {
            return new RestResponse<>(RestResponse.RestResponseCode.ERR,"");
        }

        return new RestResponse<>(RestResponse.RestResponseCode.SUCC, "");
    }

    @RequestMapping(value = "{puid}/read",produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public RestResponse readFileContent(@PathVariable("puid")String projectUid, @RequestBody String filePath) {

        //TODO: login user read file from userproject, otherwise from pub
        String content = fileService.readFileContentFromPub(projectUid, filePath);
        if(null == content) {
            return new RestResponse<>(RestResponse.RestResponseCode.ERR, "");
        }
        return new RestResponse<>(RestResponse.RestResponseCode.SUCC, content);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{puid}/create", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse createFile(@PathVariable("puid")String projectUid,  @RequestBody Map<String, String> createInfo){

        if(!createInfo.containsKey("fileName")) {
            return new RestResponse(RestResponse.RestResponseCode.ERR, "bad Request params");
        }

        Boolean createResult = fileService.createFileInPub(projectUid, createInfo.get("fileName"));
        if(!createResult) {
            return new RestResponse(RestResponse.RestResponseCode.ERR, "create fail");
        }

        return new RestResponse<>(RestResponse.RestResponseCode.SUCC, "");
    }

    @RequestMapping(value = "{puid}/getsubfile",  produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse getSubFiles(@PathVariable("puid")String projectUid, @RequestBody Map<String, String> parentPathJsonParam){

        String parentRelativePath = parentPathJsonParam.get("path");

        //TODO: validate params
        List<FileBean> result = fileService.getSubFilesFromPub(projectUid, parentRelativePath);

        return new RestResponse<List>(RestResponse.RestResponseCode.SUCC, result);

    }
}
