package com.qfy.modules.city3d.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qfy.modules.city3d.mapper.AlertMapperC;
import com.qfy.modules.city3d.po.Alert;
import com.qfy.modules.city3d.service.AlertService;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertMapperC alertMapper;

	@Override
	public int insertAlert(Alert alert) {
		int result = alertMapper.insert(alert);
		return result;
	}

	@Override
	public List<Alert> getAlert() {
		List<Alert> result = alertMapper.selectByExample(null);
		return result;
	}

	@Override
	public int updateByPrimaryKey(Alert alert) {
		int result = alertMapper.updateByPrimaryKey(alert);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = alertMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int batchDelete(List<String> ids) {
		int result = alertMapper.batchDelete(ids);
		return result;
	}
}
