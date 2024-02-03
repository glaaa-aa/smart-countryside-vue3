package com.qfy.modules.files.model;

import com.qfy.modules.files.entity.FileFolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Title: 文件夹树形管理
 * @Author: 马强
 * @Date: 2022/2/20 15:11
 **/
public class FolderTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应FileFolder中的id字段,前端数据树中的key*/
    private String key;

    /** 对应FileFolder中的id字段,前端数据树中的value*/
    private String value;

    /** 对应folder_name字段,前端数据树中的title*/
    private String title;

    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同
    private String id;
    private String pId;
    private String pCode;
    private String folderName;
    private String folderCode;
    private String folderUrl;
    private String refCode;
    private String refType;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    private List<FolderTreeModel> children = new ArrayList<>();


    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     * @param fileFolder
     */
	public FolderTreeModel(FileFolder fileFolder) {
		this.key = fileFolder.getId();
        this.value = fileFolder.getId();
        this.title = fileFolder.getFolderName();
        this.id = fileFolder.getId();
        this.pId = fileFolder.getId();
        this.pCode = fileFolder.getPCode();
        this.folderCode = fileFolder.getFolderCode();
        this.folderName = fileFolder.getFolderName();
        this.folderUrl = fileFolder.getFolderUrl();
        this.refCode = fileFolder.getRefCode();
        this.refType = fileFolder.getRefType();
        this.createBy = fileFolder.getCreateBy();
        this.createTime = fileFolder.getCreateTime();
        this.updateBy = fileFolder.getUpdateBy();
        this.updateTime = fileFolder.getUpdateTime();
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isleaf) {
         this.isLeaf = isleaf;
    }

    public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FolderTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<FolderTreeModel> children) {
        if (children == null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderCode() {
        return folderCode;
    }

    public void setFolderCode(String folderCode) {
        this.folderCode = folderCode;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public FolderTreeModel() { }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        FolderTreeModel model = (FolderTreeModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(folderCode, model.folderCode) &&
                Objects.equals(folderName, model.folderName) &&
                Objects.equals(folderUrl, model.folderUrl) &&
                Objects.equals(pId, model.pId) &&
                Objects.equals(refCode, model.refCode) &&
                Objects.equals(refType, model.refType) &&
                Objects.equals(pCode, model.pCode ) &&
                Objects.equals(createBy, model.createBy) &&
                Objects.equals(createTime, model.createTime) &&
                Objects.equals(updateBy, model.updateBy) &&
                Objects.equals(updateTime, model.updateTime) &&
                Objects.equals(children, model.children);
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, pCode, pId, folderCode, folderName,
        		folderUrl, refCode, refType, createBy, createTime, updateBy, updateTime,
        		children);
    }

}
