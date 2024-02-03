package com.qfy.modules.appauth.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 权限参数
 * @author: jeecg-boot
 */
@Data
public class AppAuthBean implements Serializable {

	private String projectId = "0"; //数据id
	private String username = ""; //用户账号
	private boolean isOwner = false; //是否为创建者
	private boolean isExecutor = false; //是否为执行者(负责人)
	private boolean isMember = false; //是否成员
	private String orgCode = null; //数据所在部门
	private boolean isAdmin =false; //是否为管理员 查询所有数据

	public AppAuthBean(String projectId,String username,String orgCode){
		this.projectId = projectId;
		this.username = username;
		this.orgCode = orgCode;
	}

}
