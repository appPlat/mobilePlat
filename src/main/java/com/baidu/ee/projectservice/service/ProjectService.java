package com.baidu.ee.projectservice.service;

import com.baidu.ee.fileservice.common.utils.FileServiceUtils;
import com.baidu.ee.fileservice.service.FileService;
import com.baidu.ee.projectservice.common.emun.ProjectType;
import com.baidu.ee.projectservice.common.utils.BosUtils;
import com.baidu.ee.projectservice.common.utils.ShellUtils;
import com.baidubce.services.bos.BosClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * Created by xuchen04 on 2015/3/4.
 */

@Service
public class ProjectService {
    private static String OS = System.getProperty("os.name").toLowerCase();

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

    public String buildProject(String projectName) throws Exception{
        //1.执行脚本进行构建
        String projectPath = FileServiceUtils.getFullPathFromPubBase(projectName);
        String apkPath = buildToApk(projectPath);

        //2.上传apk到开放云
        BosClient client= BosUtils.createClient();
        File file=new File(apkPath);
        String appName= projectName+".apk";
        BosUtils.putObject(client,appName,file);
        String VRName= BosUtils.getFileUrl(client,appName);

        //3.返回二维码url
        return VRName;
    }

    private String buildToApk(String projectPath) throws Exception{
        String apkPath = null;
        if(OS.indexOf("windows")>=0){
            apkPath = projectPath+"\\platforms\\android\\ant-build\\CordovaApp-debug.apk";

            String shellPath = FileServiceUtils.getFullPathFromShellBase("windows");
            String shell = "cmd.exe /C  start  cmd /c "+ shellPath +"//buildapk.bat " + projectPath;
            ShellUtils.runCmd(shell);
        }else if(OS.indexOf("linux")>=0){
            apkPath = projectPath+"/platforms/android/ant-build/CordovaApp-debug.apk";

            String shellPath = FileServiceUtils.getFullPathFromShellBase("linux");
            String command1 = "chmod 777 " + shellPath;
            ShellUtils.runShell(command1);

            String command2 = "/bin/sh /" + shellPath + "/buildapk.sh " + projectPath;
            ShellUtils.runShell(command2);
        }
        return apkPath;
    }
}
