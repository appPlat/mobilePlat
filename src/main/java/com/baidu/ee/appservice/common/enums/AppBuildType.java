package com.baidu.ee.appservice.common.enums;

import java.util.HashMap;

/**
 * Created by xuchen04 on 2015/3/6.
 */
public enum AppBuildType {

    BETA("beta"), RELEASE("release");

    private String name;

    private static final HashMap<String, AppBuildType> NAME_TO_ENUM = new HashMap<>();

    static {
        for(AppBuildType type : AppBuildType.values()) {
            NAME_TO_ENUM.put(type.name, type);
        }
    }

    AppBuildType(String name) {
        this.name = name;
    }

    public Boolean isBeta() {
        if("beta".equals(name)) {
            return true;
        }else {
            return false;
        }
    }

    public static AppBuildType getBuildTypeFromName(String name) {
        return NAME_TO_ENUM.get(name);
    }
}
