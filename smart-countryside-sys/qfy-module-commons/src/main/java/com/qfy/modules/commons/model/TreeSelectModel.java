package com.qfy.modules.commons.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * 树形下拉框
  * @author: jeecg-boot
 */
@Data
public class TreeSelectModel implements Serializable {

	private static final long serialVersionUID = 9016390975325574747L;

	private String key;

	private String title;

	private boolean isLeaf;

	private String icon;

	private String parentId;

	private String value;

	private String code;

	private Integer ruleFlag;

	private String slotTitle;

	private Map<String,String> scopedSlots;

	private List<TreeSelectModel> children;

	public List<TreeSelectModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeSelectModel> children) {
		this.children = children;
	}


	public TreeSelectModel(String key,String parentId,String slotTitle,Integer ruleFlag,boolean isLeaf) {
		this.key = key;
		this.parentId = parentId;
		this.ruleFlag = ruleFlag;
		this.slotTitle =  slotTitle;
		Map<String,String> map = new HashMap(5);
		map.put("title", "hasDatarule");
		this.scopedSlots = map;
		this.isLeaf = isLeaf;
		this.value = key;
		this.children = new ArrayList<TreeSelectModel>();
	}
}
