package com.baidu.ee.fileservice.service;


import com.baidu.ee.fileservice.common.bean.FileBean;
import com.baidu.ee.fileservice.common.utils.FileServiceUtils;
import com.baidu.ee.projectservice.common.emun.ProjectType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by xuchen04 on 2015/3/4.
 */

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    public Boolean updateFileInPubProject(String projectUid, String filePath, String newContent) {
        String relativeFilePath = projectUid + filePath;
        String updateFileFullPath = FileServiceUtils.getFullPathFromPubBase(relativeFilePath);

        try {
            FileUtils.writeStringToFile(new File(updateFileFullPath), newContent, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("");
            return false;
        }

        return true;
    }

    public Boolean copyFileFromTemplateToPub(ProjectType typeForCreate, String projectUid) {
        String from = FileServiceUtils.getFullPathFromTemplateBase(typeForCreate.name());
        String to = FileServiceUtils.getFullPathFromPubBase(projectUid);
        try {
            FileUtils.copyDirectory(new File(from), new File(to));
        } catch (IOException e) {
            LOGGER.error("@copyFileFromTemplateToPub from {} to {}", from, to);
            return false;
        }
        return true;
    }

    public List<FileBean> getSubFilesFromPub(String projectUid, String parentPath) {

        String parentRelativePath = projectUid + parentPath;
        String parentFullPath = FileServiceUtils.getFullPathFromPubBase(parentRelativePath);

        File parentFolder = new File(parentFullPath);
        File[] subFiles = parentFolder.listFiles();
        List<FileBean> result = new ArrayList<>();
        for (File file : subFiles) {
            FileBean fileBean = new FileBean(
                    file.getName(),
                    FileServiceUtils.concatBasePathAndRelativePath(parentPath, file.getName()),
                    file.isDirectory());
            result.add(fileBean);
        }

        return result;
    }

    public String readFileContentFromPub(String projectUid, String filePath) {

        String fileContent;
        String fileToReadFullPath = FileServiceUtils.getFullPathFromPubBase(projectUid + filePath);
        File file = new File(fileToReadFullPath);
        try {
            fileContent = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("@readFileContentFromPub IO exception when read file {}, exception: {}", file.getAbsolutePath(), e.getMessage());
            return null;
        }

        return fileContent;
    }

    public Boolean createFileInPub(String projectUid, String filePath) {

        String creatFilePath = FileServiceUtils.getFullPathFromPubBase(projectUid + filePath);

        File file = new File(creatFilePath);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("@createFileInPub create file {}, exception: {}", creatFilePath, e.getMessage());
            return false;
        }
    }
}
