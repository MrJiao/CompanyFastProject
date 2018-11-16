package com.bjhy.fast.build.core.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by: Jackson
 */
public class FileVo {

    public static final String TYPE_DIRECTORY = "directory";
    public static final String TYPE_FILE = "file";

    private String name;
    private String path;
    private String type;

    private String parentName;
    private String parentPath;
    private String parentType;

    private List<FileVo> childsFileVo = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FileVo> getChildsFileVo() {
        return childsFileVo;
    }

    public void setChildsFileVo(List<FileVo> childsFileVo) {
        this.childsFileVo = childsFileVo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
