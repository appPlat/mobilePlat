package com.baidu.ee.projectservice.common.emun;

import java.util.HashMap;

/**
 * Created by xuchen04 on 2015/3/4.
 */

public enum ProjectType {
    HELLOWORLD("helloWorld"),
    PHONE("phone"),
    TABLET("tablet");

    private String name;
    private static final HashMap<String, ProjectType> NAME_TO_ENUM = new HashMap<>();

    static{
        for(ProjectType projectType : ProjectType.values()) {
            NAME_TO_ENUM.put(projectType.name, projectType);
        }
    }

    ProjectType(String name) {
        this.name = name;
    }

    public static ProjectType getFromName(String name) {
        return NAME_TO_ENUM.get(name);
    }
}
