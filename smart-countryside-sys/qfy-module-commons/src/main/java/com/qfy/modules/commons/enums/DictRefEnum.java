package com.qfy.modules.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 马强
 * @title: 自定义字典类型ID
 * @projectName qfy-project-task-system
 * @description: TODO
 * @date 2022/11/15 10:03
 */
@Getter
@AllArgsConstructor
public enum DictRefEnum {

    ProjectState("project_state","项目进度类型"),
    TaskState("task_state","任务进度类型"),
    userIdentity("userIdentity","人员身份");

    private String code;
    private String alias;

    public static String getText(String code) {
        for (DictRefEnum state : DictRefEnum.values()) {
            if (state.getCode().equals(code)) {
                return state.alias;
            }
        }
        return null;
    }


}
