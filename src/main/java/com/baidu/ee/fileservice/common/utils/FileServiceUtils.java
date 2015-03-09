package com.baidu.ee.fileservice.common.utils;

import com.baidu.ee.fileservice.common.configure.FileServiceConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by xuchen04 on 2015/3/5.
 */

@Component
public class FileServiceUtils {

    public static final String PATH_SEP = File.separator;

    public static String getFullPathFromTemplateBase(String relativePath) {
        return FileServiceConfiguration.getTemplateFileBasePath() + relativePath;
    }

    public static String getFullPathFromPubBase(String relativePath) {
        return FileServiceConfiguration.getPubProjectBasePath() + relativePath;
    }

    public static String getFullPathFromProjectBase(String relativePath) {
        return FileServiceConfiguration.getUserProjectBasePath() + relativePath;
    }

    /**
     * concated result based on UNIX seperator "/"
     * @param basePath
     * @param relativePath
     * @return
     */
    public static String concatBasePathAndRelativePath(String basePath, String relativePath) {
        String rawFullPath = basePath + PATH_SEP + relativePath;
        return rawFullPath.replaceAll("\\\\", "///").replace("//+", "/");
    }

}
