package com.qfy.modules.city3d.service;

import java.util.List;
import com.qfy.modules.city3d.po.Viewtag;

public interface TagService {
	public List<Viewtag> getTag();
	public int deleteByPrimaryKey(String tagId);
	public int insert(Viewtag tag);
}
