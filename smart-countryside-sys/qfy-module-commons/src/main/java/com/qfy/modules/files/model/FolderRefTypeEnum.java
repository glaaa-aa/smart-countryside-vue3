package com.qfy.modules.files.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 马强
 * @title: FolderRefTypeEnum
 * @projectName prj-task
 * @description: TODO
 * @date 2022/2/20 16:50
 */
public enum FolderRefTypeEnum {
    project("project","项目管理",true),
    task("task","任务管理",true),
    activity("activity","活动管理",false),
    cms("activity","内容管理",false),
    other("upload","其他上传管理",false);
    private String code;
    private String alias;
    private boolean isXuniPath;//是否读取物理文件

    FolderRefTypeEnum(String code, String alias,boolean isXuniPath) {
        this.code = code;
        this.alias = alias;
        this.isXuniPath = isXuniPath;
    }

    public boolean getIsXuNiPath() {
        return isXuniPath;
    }

    public void setIsXuNiPath(boolean isXuNiPath) {
        this.isXuniPath = isXuNiPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static FolderRefTypeEnum valOfCode(String code){
        if(StringUtils.isEmpty(code)){
            return  other;
        }
        for (FolderRefTypeEnum item:values()){
            if(code.equals(item.code)){
                return item;
            }
        }
        return  other;
    }

}
