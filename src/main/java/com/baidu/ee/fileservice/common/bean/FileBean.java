package com.baidu.ee.fileservice.common.bean;

import com.baidu.ee.fileservice.common.utils.FileServiceUtils;

import java.io.File;

/**
 * Created by xuchen04 on 2015/3/5.
 */
public class FileBean {

    private String fileName;
    private String relativePath;
    private Boolean isFolder;

    public FileBean() {}

    public FileBean(String fileName, String relativePath, Boolean isFolder) {
        this.fileName = fileName;
        this.relativePath = relativePath;
        this.isFolder = isFolder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
