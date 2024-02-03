package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.ViewtagMapper;
import com.qfy.modules.city3d.po.Viewtag;
import com.qfy.modules.city3d.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private ViewtagMapper viewtagMapper;

	@Override
	public List<Viewtag> getTag() {
		List<Viewtag> result = viewtagMapper.selectByExample(null);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String tagId) {
		int result = viewtagMapper.deleteByPrimaryKey(tagId);
		return result;
	}

	@Override
	public int insert(Viewtag tag) {
		int result = viewtagMapper.insert(tag);
		return result;
	}
}
